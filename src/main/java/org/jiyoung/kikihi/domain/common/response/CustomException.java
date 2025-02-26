package org.jiyoung.kikihi.domain.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 사용자 정의 예외
@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;

    public String getMessage() {
        return errorCode.getMessage();
    }


}
