package com.metanet.study.interfaces.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.metanet.study.global.domain.ApiResponse;
import com.metanet.study.interfaces.config.UserClient;
import com.metanet.study.interfaces.dto.UserRequestDto;
import com.metanet.study.interfaces.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserClientService {

  private final UserClient userClient;

  public List<UserResponseDto> getAllUsers() {
    ApiResponse<List<UserResponseDto>> response = userClient.getUserAll();
    return response.getResult(); // result 필드에 실제 데이터
  }

  public UserResponseDto getUserById(long id) {
    ApiResponse<UserResponseDto> response = userClient.getUserById(id);
    return response.getResult();
  }

  public long createUser(UserRequestDto dto) {
    ApiResponse<Long> response = userClient.createUser(dto);
    log.info("create:{}", response.getResult().toString());
    return response.getResult();
  }

  public UserResponseDto updateUser(long id, UserRequestDto dto) {
    ApiResponse<UserResponseDto> response = userClient.updateUser(id, dto);
    // update 시 update 된 dto 를 리턴하는 case
    log.info("result:{}", response.getResult().toString());
    return response.getResult();
    // return Optional.ofNullable(response.getResult())
    // .orElseThrow(() -> new IllegalStateException("UserResponseDto is null"));
  }

  public int deleteUser(long id) {
    ApiResponse<?> response = userClient.deleteUser(id);
    return (int) response.getResult();
  }

}
