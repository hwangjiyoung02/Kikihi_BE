package org.jiyoung.kikihi.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        HttpStatus httpStatus,
        String message,
        @Nullable T data,
        @Nullable ErrorHandler error
) {
    // 성공 응답
    public static <T> ApiResponse<T> ok(@Nullable T data) {
        return new ApiResponse<>(HttpStatus.OK, "Success!", data, null);
    }

    // 실패 응답
    public static ApiResponse<Object> fail(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null, null);
    }

    // 서버 오류 응답
    public static <T> ApiResponse<T> serverError(String message) {
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, message, null, null);
    }

    // 사용자 정의 응답
    public static <T> ApiResponse<T> custom(HttpStatus status, String message, T data, @Nullable ErrorHandler error) {
        return new ApiResponse<>(status, message, data, error);
    }

    // ResponseEntity로 감싸기
    public static <T> ResponseEntity<ApiResponse<T>> toResponseEntity(ApiResponse<T> apiResponse) {
        return ResponseEntity.status(apiResponse.httpStatus).body(apiResponse);
    }
}
