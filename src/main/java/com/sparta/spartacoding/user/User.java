package com.sparta.spartacoding.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.lecture.entity.Lecture;
import com.sparta.spartacoding.user.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String gender;

    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollmentList = new ArrayList<>();

    public User (String email, String password, SignupRequestDto requestDto){
        this.email = email;
        this.password = password;
        this.name = requestDto.getName();
        this.phoneNum = requestDto.getPhoneNumber();
        this.birth = requestDto.getBirth();
        this.gender = requestDto.getGender();
    }
}
