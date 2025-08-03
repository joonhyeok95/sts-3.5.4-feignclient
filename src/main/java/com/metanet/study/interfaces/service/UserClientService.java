package com.metanet.study.interfaces.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.metanet.study.global.domain.ApiResponse;
import com.metanet.study.interfaces.config.UserClient;
import com.metanet.study.interfaces.dto.UserRequestDto;
import com.metanet.study.interfaces.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClientService {

  private final UserClient userClient;

  public List<UserResponseDto> getAllUsers() {
    ApiResponse<List<UserResponseDto>> response = userClient.getUserAll();
    return response.getResult(); // result 필드에 실제 데이터
  }

  public UserResponseDto getUserById(Long id) {
    ApiResponse<UserResponseDto> response = userClient.getUserById(id);
    return response.getResult();
  }

  public int createUser(UserRequestDto dto) {
    ApiResponse<?> response = userClient.createUser(dto);
    return (int) response.getResult();
  }

}
