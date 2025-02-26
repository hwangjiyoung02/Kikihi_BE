package org.jiyoung.kikihi.infrastructure.oauth2;

import org.jiyoung.kikihi.domain.member.dto.OAuthUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final OAuthUserDTO userDTO;

    public CustomOAuth2User(OAuthUserDTO userDTO){
        this.userDTO=userDTO;
    }

    @Override
    public Map<String,Object> getAttributes(){
        return null;
    }


    @Override
    public String getName(){
        return userDTO.getName();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collection=new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return null;
            }
        });
        return collection;
    }

    public String getUsername(){
        return userDTO.getUsername();
    }
}
