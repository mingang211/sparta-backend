package com.sparta.spartacoding.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "최소 6자 이상, 숫자와 영문자 조합."
    )
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String birth;

    @NotBlank
    private String gender;
}
