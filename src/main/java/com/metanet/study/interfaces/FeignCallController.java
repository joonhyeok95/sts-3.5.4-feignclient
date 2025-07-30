package com.metanet.study.interfaces;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.metanet.study.interfaces.dto.UserResponseDto;
import com.metanet.study.interfaces.service.UserClientService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/feign")
@RequiredArgsConstructor
public class FeignCallController {

  private final UserClientService userClientService;

  // GlobalResponseWrapper 를 활용하기
  @GetMapping
  public List<UserResponseDto> getAllUsers(HttpServletRequest request) {
    List<UserResponseDto> users = userClientService.getAllUsers();
    return users;
  }

  @GetMapping("/{id}")
  public UserResponseDto getAllUsers(@PathVariable("id") Long id, HttpServletRequest request) {
    UserResponseDto user = userClientService.getUserById(id);
    return user;
  }
}
