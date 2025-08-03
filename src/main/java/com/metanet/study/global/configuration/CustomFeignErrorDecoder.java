package com.metanet.study.global.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomFeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    log.info("Exception:::decode!!!!");
    int status = response.status();
    String body = "";
    try (InputStream inputStream = response.body().asInputStream();
        Reader reader = new InputStreamReader(inputStream)) {
      body = Util.toString(reader);
      log.info("feignclient body:{}", body);
      // body 사용
    } catch (IOException e) {
      // 예외 처리
    }

    switch (status) {
      case 400:
        return new IllegalArgumentException("Feign 400 에러");
      case 404:
        return new NotFoundException("Feign 404 에러");
      case 500:
        return new ExternalServerException("Feign 500 에러");
      // 필요한 status 추가!
      default:
        return new RuntimeException("Feign 오류 응답(" + status + ")");
    }
  }
}
