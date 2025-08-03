package com.metanet.study.interfaces.config;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.metanet.study.global.domain.ApiResponse;
import com.metanet.study.interfaces.dto.UserRequestDto;
import com.metanet.study.interfaces.dto.UserResponseDto;

@FeignClient(name = "user-service", url = "${user-service.url}"
// , configuration = CustomFeignConfig.class // 인터셉터, 에러 디코더 따로 관리 가능
)
public interface UserClient {

  @GetMapping("/api/users/all")
  ApiResponse<List<UserResponseDto>> getUserAll();

  @GetMapping("/api/users/{id}")
  ApiResponse<UserResponseDto> getUserById(@PathVariable("id") Long id);

  @PostMapping("/api/users")
  ApiResponse<?> createUser(@RequestBody UserRequestDto userRequest);

  @PutMapping("/api/users/{id}")
  ApiResponse<UserResponseDto> updateUser(@PathVariable("id") Long id,
      @RequestBody UserRequestDto userRequest);

  @DeleteMapping("/api/users/{id}")
  void deleteUser(@PathVariable("id") Long id);
}
