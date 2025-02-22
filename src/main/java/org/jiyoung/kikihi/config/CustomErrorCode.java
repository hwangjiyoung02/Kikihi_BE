package org.jiyoung.kikihi.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
// 커스텀 예외
@Getter
@RequiredArgsConstructor
enum CustomErrorCode {
    INVALID_REQUEST("Invalid request", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;
}


