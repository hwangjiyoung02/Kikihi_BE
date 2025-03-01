package org.jiyoung.kikihi.platform.adapter.out.redis.token;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 14440)
public class RefreshTokenRedisHash {
    @Id
    private String username;
    private String refresh;
    private String expiration;
}
