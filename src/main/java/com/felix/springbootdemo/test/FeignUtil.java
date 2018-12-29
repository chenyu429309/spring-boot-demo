package com.felix.springbootdemo.test;

import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class FeignUtil {

    public static final String URL = "http://192.168.0.225:8707";

    public static Feign.Builder builder() {
        return Feign
                .builder()
                .retryer(new Retryer.Default())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(template -> template.query("username", "test@163.com")
                        .query("password", "111111"));
    }

    public static void main(String[] args) {

        FeignClient client = FeignUtil.builder().target(FeignClient.class, URL);
        Map login = client.login("chenyu429309@163.com", "111111");
        String o = ((Map) login.get("data")).get("jwtToken").toString();
        log.info("{},{}", login, o);
        Feign.Builder authorization = FeignUtil.builder().requestInterceptor(requestTemplate -> requestTemplate.header("Authorization", o));
        Object test = authorization.target(FeignClient.class, URL).test();
        log.info("{}", test);
    }

}

interface FeignClient {
    @RequestLine(value = "GET /api/v1/auth//resources")
    Object test();

    @RequestLine(value = "POST /api/v1/session/login")
    Map login(@Param("username") String username, @Param("password") String password);
}

