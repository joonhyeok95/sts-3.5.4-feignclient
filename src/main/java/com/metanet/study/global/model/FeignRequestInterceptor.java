package com.metanet.study.global.model;

import org.springframework.stereotype.Component;
import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    // Content-Type 헤더는 필요에 따라 명시적으로 지정
    requestTemplate.header("Content-Type", "application/json");
  }
}
