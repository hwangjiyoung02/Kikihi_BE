package org.jiyoung.kikihi.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuthUserDTO {
    private String name;
    private String username;


}
