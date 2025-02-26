package org.jiyoung.kikihi.infrastructure.oauth2.response;

public interface OAuth2Response {
    // 제공자 ( google, naver, kakao )
    String getProvider();

    // 제공자에서 발급해주는 아이디
    String getProviderId();

    //이메일
    String getEmail();

    //사용자 실명
    String getName();

}
