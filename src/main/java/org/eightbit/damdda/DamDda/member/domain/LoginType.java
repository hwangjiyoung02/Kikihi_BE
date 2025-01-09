package org.eightbit.damdda.DamDda.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class LoginType {

    @Enumerated(EnumType.STRING)
    private LoginProvider loginProvider;

    @Column(nullable = false)
    private String socialId;

    @Builder
    public LoginType(LoginProvider loginProvider, String socialId) {
        this.loginProvider = loginProvider;
        this.socialId = socialId;
    }
}
