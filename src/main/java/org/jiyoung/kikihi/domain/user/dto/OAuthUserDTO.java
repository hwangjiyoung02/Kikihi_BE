package org.jiyoung.kikihi.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuthUserDTO {
    private String name;
    private String username;


}
