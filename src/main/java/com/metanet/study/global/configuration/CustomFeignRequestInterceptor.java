package com.metanet.study.global.configuration;

import java.util.UUID;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CustomFeignRequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate template) {
    // 인증 토큰 등 필요 시 추가
    template.header("Authorization", "Bearer " + getAccessToken());
    // 공통 트레이싱 값 등도 가능
    template.header("X-Request-Id", UUID.randomUUID().toString());
    // 필요하다면 커스텀 헤더(JSON 등)
    // String customJson = "{\"custom\":\"value\"}";
    // template.header("X-Custom", customJson);
  }

  private String getAccessToken() {
    // 예시: 실제로는 SecurityContext, AuthService 등에서 가져옴
    return "your-access-token";
  }
}
