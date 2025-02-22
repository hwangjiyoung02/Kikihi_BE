package org.jiyoung.kikihi.member.service.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuthUserDTO {
    private String name;
    private String username;


}
