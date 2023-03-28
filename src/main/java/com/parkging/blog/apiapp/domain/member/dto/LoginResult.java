package com.parkging.blog.apiapp.domain.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResult {
    private Long id;
    private String name;
    private String email;

    @Builder
    public LoginResult(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
