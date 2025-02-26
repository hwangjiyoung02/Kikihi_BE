package org.jiyoung.kikihi.domain.member.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 14440)
public class RefreshEntity {
    @Id
    private String username;
    private String refresh;
    private String expiration;
}
