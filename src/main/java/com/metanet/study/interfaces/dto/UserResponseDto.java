package com.metanet.study.interfaces.dto;

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
  // 일반적인 API 스펙으로 변경
  // private DepartmentDto department;
  private String departmentId;
  private String departmentName;
}
