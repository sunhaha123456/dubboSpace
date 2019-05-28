package com.dubbo.common.exception;

import com.dubbo.common.data.response.ResponseResultCode;

/**
 * 功能：
 *      业务异常类
 * @author sunpeng
 * @date 2017
 */
public class BusinessException extends RuntimeException {

    public int code;

    public String msg;

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = 500;
        this.msg = msg;
    }

    public BusinessException(ResponseResultCode res) {
        super(res.getMsg());
        this.code = res.getCode();
        this.msg = res.getMsg();
    }
}