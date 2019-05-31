package com.dubbo.service.rpc.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.common.dao.redis.RedisRepositoryCustom;
import com.dubbo.common.data.response.ResponseResultCode;
import com.dubbo.common.exception.BusinessException;
import com.dubbo.common.util.IdUtil;
import com.dubbo.common.util.Md5Util;
import com.dubbo.common.util.StringUtil;
import com.dubbo.dao.mysql.repository.TbUserRepository;
import com.dubbo.data.constant.ProviderConstant;
import com.dubbo.data.entity.table.TbUser;
import com.dubbo.data.vo.UserRedisVo;
import com.dubbo.rpc.data.constant.RpcConstant;
import com.dubbo.rpc.data.dto.TokenValidateDto;
import com.dubbo.rpc.data.dto.UserInfoDto;
import com.dubbo.rpc.data.dto.UserLoginDto;
import com.dubbo.rpc.service.LoginService;
import com.dubbo.service.local.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Inject
    private TbUserRepository tbUserRepository;
    @Inject
    private RedisRepositoryCustom redisRepositoryCustom;
    @Inject
    private UserService userService;

    @Override
    public String toSuccess(TokenValidateDto dto) throws Exception {
        if (tokenValidate(dto)) {
            Optional<TbUser> tbUserOptional = tbUserRepository.findById(Long.valueOf(dto.getUserIdValidate()));
            if (tbUserOptional.isPresent()) {
                TbUser user = tbUserOptional.get();
                return user.getUname();
            }
        }
        return null;
    }

    @Override
    public void out(TokenValidateDto dto) throws Exception {
        if (tokenValidate(dto)) {
            redisRepositoryCustom.delete(ProviderConstant.LOGIN_USER_PREFIX + dto.getUserIdValidate());
        }
    }

    @Override
    public UserInfoDto verify(UserLoginDto param) throws Exception {
        // 1、校验验证码
        String codeRedis = redisRepositoryCustom.getString(RpcConstant.LOGIN_CODE_PREFIX + param.getKey());
        String codeFront = Md5Util.MD5Encode(param.getCode());
        if (StringUtil.isEmpty(codeRedis) || StringUtil.isEmpty(codeFront) || !codeRedis.equals(codeFront)) {
            throw new BusinessException(ResponseResultCode.CODE_ERROR);
        }
        // 2、校验用户名和密码，并且用户状态正常
        List<TbUser> userList = tbUserRepository.listByUnameAndPwd(param.getUname(), Md5Util.MD5Encode(param.getUpwd()));
        if (userList == null || userList.size() == 0) {
            throw new BusinessException(ResponseResultCode.LOGIN_ERROR);
        }
        TbUser user = userList.get(0);
        // 3、更新redis用户信息，更新用户token、用户状态
        String userRedisKey = ProviderConstant.LOGIN_USER_PREFIX + user.getId();
        UserRedisVo userRedis = new UserRedisVo(IdUtil.getID() + IdUtil.getID());
        userService.userRedisInfoSave(userRedisKey, userRedis);
        // 4、删除redis验证码
        redisRepositoryCustom.delete(RpcConstant.LOGIN_CODE_PREFIX + param.getKey());
        return new UserInfoDto(userRedis.getToken(), user.getId());
    }

    @Override
    public boolean tokenValidate(TokenValidateDto dto) {
        String token = dto.getTokenValidate();
        String userId = dto.getUserIdValidate();
        String method = dto.getMethod();
        String url = dto.getUrl();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        if (!Arrays.asList("POST", "GET").contains(method)) {
            return false;
        }
        if (StringUtil.isEmpty(token) || token.length() != 64 || StringUtil.isEmpty(userId)) {
            log.error("Request url：{}，method：{}，userId：{}，token：{}，拦截此请求：001-请求不合法！", url, method, userId, token);
            return false;
        }
        String userRedisKey = ProviderConstant.LOGIN_USER_PREFIX + userId;
        UserRedisVo userRedis = redisRepositoryCustom.getClassObj(userRedisKey, UserRedisVo.class);
        if (userRedis == null) {
            log.error("Request url：{}，method：{}，userId：{}，token：{}，拦截此请求：002-redis中userId对应键值已超时！", url, method, userId, token);
            return false;
        }
        if (!token.equals(userRedis.getToken())) {
            log.error("Request url：{}，method：{}，userId：{}，token：{}，拦截此请求：003-redis中userId对应redis中用户信息的token，与前端传入token，不一致！", url, method, userId, token);
            return false;
        }
        userService.userRedisInfoSave(userRedisKey, userRedis);
        return true;
    }
}