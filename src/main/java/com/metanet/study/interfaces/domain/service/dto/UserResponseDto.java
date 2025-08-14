package com.metanet.study.interfaces.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

  private long id;
  private String name;
  private String email;

  // 부서는 없을 수도 있다는 조건 추가
  private Long departmentId;
  private String departmentName;
}
