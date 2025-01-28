package org.eightbit.damdda.DamDda.common;

import lombok.Getter;
// 사용자 정의 예외
@Getter
public class CustomException extends RuntimeException{
    private final CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode customErrorCode){
        super(customErrorCode.getMessage());//RuntimeException 클래스 생성자 호출
        this.customErrorCode=customErrorCode;
    }

}
