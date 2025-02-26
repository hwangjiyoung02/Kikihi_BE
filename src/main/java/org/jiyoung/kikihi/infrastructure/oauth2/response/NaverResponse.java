package org.jiyoung.kikihi.infrastructure.oauth2.response;

import java.util.Map;

public class NaverResponse implements OAuth2Response {
    private final Map<String,Object> attribute;

    // 이중키 형식으로 담김
    public NaverResponse(Map<String,Object> attribute){
        this.attribute=(Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }
    @Override
    public String getProviderId(){
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail(){
        return attribute.get("email").toString();
    }
    @Override
    public String getName(){
        return attribute.get("name").toString();
    }
}

