package com.metanet.study.global.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.metanet.study.global.domain.ApiResponse;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomFeignErrorDecoder implements ErrorDecoder {
  private final ObjectMapper objectMapper;

  public CustomFeignErrorDecoder() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
    this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  @Override
  public Exception decode(String methodKey, Response response) {
    int status = response.status();
    String body = "";
    try (InputStream inputStream = response.body().asInputStream();
        Reader reader = new InputStreamReader(inputStream)) {
      body = Util.toString(reader);
      log.info("feignclient body:{}", body);
      // JSON 파싱 (ApiResponse<?> 구조에 맞춰 제네릭 무시하고 파싱)
      ApiResponse<?> apiResponse =
          objectMapper.readValue(body, new TypeReference<ApiResponse<?>>() {});

      // ApiResponse 내 메시지를 활용해 메시지 생성
      String apiMessage = apiResponse.getError() != null ? apiResponse.getError() : "No message";
      switch (status) {
        case 400:
          return new IllegalArgumentException("Feign 400 에러: " + apiMessage);
        case 404:
          return new NotFoundException("Feign 404 에러: " + apiMessage);
        case 500:
          return new ExternalServerException("Feign 500 에러: " + apiMessage);
        // 필요한 status 추가!
        default:
          return new RuntimeException("Feign 오류 응답(" + status + ")");
      }
    } catch (IOException e) {
      // 예외 처리
      log.warn("Feign error body 읽기 실패 또는 파싱 실패", e);
      // JSON 파싱 실패 시, 기본 메시지만 포함해 에러 던짐
      switch (status) {
        case 400:
          return new IllegalArgumentException("Feign 400 에러: 응답본문 파싱 실패");
        case 404:
          return new NotFoundException("Feign 404 에러: 응답본문 파싱 실패");
        case 500:
          return new ExternalServerException("Feign 500 에러: 응답본문 파싱 실패");
        default:
          return new RuntimeException("Feign 오류 응답(" + status + "): 응답본문 파싱 실패");
      }

    }
  }
}
