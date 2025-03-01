package org.jiyoung.kikihi.platform.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuthUserDTO {
    private String name;
    private String username;


}
