package com.dubbo.conf;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@RestController
//@ComponentScan(value = "com.dubbo", excludeFilters={@ComponentScan.Filter(type= FilterType.ANNOTATION, value=Service.class)})
@ComponentScan(value = "com.dubbo")
@DubboComponentScan(basePackages = {"com.dubbo.service.rpc.impl"})
@EntityScan({"com.dubbo.data.entity.table"})
@EnableJpaRepositories({"com.dubbo.dao.mysql.repository", "com.dubbo.common.dao.redis.impl"})
@MapperScan("com.dubbo.dao.mysql.mapper")
@SpringBootApplication
public class Application extends WebMvcConfigurationSupport {

    @RequestMapping("/home")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}