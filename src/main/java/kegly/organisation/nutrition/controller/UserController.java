package kegly.organisation.nutrition.controller;

import jakarta.validation.Valid;
import kegly.organisation.nutrition.dto.request.UserRequestDto;
import kegly.organisation.nutrition.dto.response.UserResponseDto;
import kegly.organisation.nutrition.mapper.UserMapper;
import kegly.organisation.nutrition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toDto(userService.create(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toDto(userService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,
                                                  @RequestBody @Valid UserRequestDto dto) {
        return ResponseEntity.ok(userMapper.toDto(userService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}