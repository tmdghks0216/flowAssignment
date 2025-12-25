package com.example.flow.fileExtension.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum statusCodeEnum {

    SUCCESS(1,"요청이 성공적으로 처리되었습니다."),
    LIMIT_EXCEEDED(10, "최대 등록 개수(200개)를 초과했습니다."),
    DUPLICATE_EXTENSION(11, "이미 등록된 확장자입니다."),
    FAIL(12, "요청이 실패하였습니다.");

    private final int code;
    private final String msg;

}
