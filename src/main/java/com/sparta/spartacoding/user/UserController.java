package com.sparta.spartacoding.user;

import com.sparta.spartacoding.user.dto.SignupRequestDto;
import com.sparta.spartacoding.user.dto.SignupResponseDto;
import com.sparta.spartacoding.user.dto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new SignupResponseDto());
        }
        User savedUser = userService.signup(requestDto);
        return ResponseEntity.ok(new SignupResponseDto(savedUser));
    }

    @GetMapping("/test")
    public String testMethod(){
        return "OK";
    }
}
