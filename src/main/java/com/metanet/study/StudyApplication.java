package com.metanet.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // feignclient 사용
public class StudyApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudyApplication.class, args);
  }

}
