package com.kimcompany.jangbogbackendver2.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "이메일 과 비밀번호가 일치하지 않거나 가입하지 않은 이메일입니다"),
    MEMBER_NOT_FOUND(INTERNAL_SERVER_ERROR, "해당 사용자를 찾을 수 없습니다"),

    NOT_FOUND_REFRESH_INFO(INTERNAL_SERVER_ERROR, "재발급 정보를 찾지 못했습니다"),

    EXPIRATION_REFRESH_TOKEN(INTERNAL_SERVER_ERROR,"세션 만료 재로그인 필요"),
    FAIL_MAKE_TOKEN(INTERNAL_SERVER_ERROR,"세션 만료 재로그인 필요")

            ;

    private final HttpStatus httpStatus;
    private final String detail;

}
