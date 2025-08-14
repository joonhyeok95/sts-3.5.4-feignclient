package com.metanet.study.interfaces.web;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.metanet.study.global.domain.ApiResponse;
import com.metanet.study.global.domain.PageResponse;
import com.metanet.study.global.model.ResponseEntityUtil;
import com.metanet.study.interfaces.domain.service.UserClientService;
import com.metanet.study.interfaces.domain.service.dto.UserRequestDto;
import com.metanet.study.interfaces.domain.service.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/feign")
@RequiredArgsConstructor
public class FeignCallController {

  private final UserClientService userClientService;

  /*
   * GlobalResponseWrapper 를 활용했을 때 GlobalResponseWrapper.java
   */
  @GetMapping("/all")
  public List<UserResponseDto> getAllUsers() {
    List<UserResponseDto> users = userClientService.getAllUsers();
    return users;
  }

  // ResponseEntity
  @GetMapping
  public ResponseEntity<ApiResponse<PageResponse<UserResponseDto>>> getAllUsers(
      @RequestParam("page") int page, @RequestParam("size") int size, HttpServletRequest request) {

    PageResponse<UserResponseDto> datas = userClientService.getAllUsersPage(page, size);
    return ResponseEntityUtil.buildResponse(datas, HttpStatus.OK, "User retrieved successfully",
        request);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable("id") long id,
      HttpServletRequest request) {
    UserResponseDto user = userClientService.getUserById(id);
    return ResponseEntityUtil.buildResponse(user, HttpStatus.OK, "User retrieved successfully",
        request);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Long>> createUsers(@Valid @RequestBody UserRequestDto dto,
      HttpServletRequest request) {
    long userId = userClientService.createUser(dto);
    return ResponseEntityUtil.buildResponse(userId, HttpStatus.CREATED, "User created successfully",
        request);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(@PathVariable("id") long id,
      @Valid @RequestBody UserRequestDto dto, HttpServletRequest request) {
    UserResponseDto user = userClientService.updateUser(id, dto);
    return ResponseEntityUtil.buildResponse(user, HttpStatus.OK, "User Modify successfully",
        request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> deleteUsers(@PathVariable("id") long id,
      HttpServletRequest request) {
    int user = userClientService.deleteUser(id);
    return ResponseEntityUtil.buildResponse("User deleted successfully", HttpStatus.OK,
        "Delete success", request);
  }

  @GetMapping("/test")
  public ResponseEntity<?> getTest(HttpServletRequest request) {
    return ResponseEntityUtil.buildResponse(1, HttpStatus.OK, "User retrieved successfully",
        request);
  }
}
