package com.sparta.spartacoding.user.dto;

import com.sparta.spartacoding.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupResponseDto {
    private String email;
    private String name;
    private String phoneNum;
    private String birth;
    private String gender;

    public SignupResponseDto(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        this.phoneNum = user.getPhoneNum();
        this.birth = user.getBirth();
        this.gender = user.getGender();
    }
}
