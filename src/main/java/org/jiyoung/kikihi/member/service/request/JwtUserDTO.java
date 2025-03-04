package org.jiyoung.kikihi.member.service.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtUserDTO {
    private String email;
    private String password;
    private String name;

    @JsonCreator
    public JwtUserDTO(@JsonProperty("email") String email, @JsonProperty("password") String password, @JsonProperty("name") String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
