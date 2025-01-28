package org.eightbit.damdda.DamDda.member.service.request;

import lombok.Getter;

@Getter

public class AddUserRequest {
    private String email;
    private String password;
    private String name;
}
