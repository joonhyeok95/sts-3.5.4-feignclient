package com.metanet.study.global.configuration;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

@Configuration
public class CustomFeignConfig {

  // 요청/응답 로그 레벨 (FULL: 개발, BASIC: 운영)
  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  // http 타임아웃 커스터마이즈
  @Bean
  public Request.Options options() {
    // deprecated
    // return new Request.Options(5000, 10000); // connectTimeout=5s, readTimeout=10s
    // springboot 3.x
    return new Request.Options(5, TimeUnit.SECONDS, // connectTimeout
        10, TimeUnit.SECONDS, // readTimeout
        true // followRedirects
    );
  }

  // 커스텀 ErrorDecoder (아래와 연결)
  @Bean
  public ErrorDecoder errorDecoder() {
    return new CustomFeignErrorDecoder();
  }

  // 공통 인터셉터 (아래와 연결)
  @Bean
  public RequestInterceptor requestInterceptor() {
    return new CustomFeignRequestInterceptor();
  }
}
