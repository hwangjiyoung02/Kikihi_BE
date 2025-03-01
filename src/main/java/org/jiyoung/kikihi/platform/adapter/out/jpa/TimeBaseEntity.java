package org.jiyoung.kikihi.platform.adapter.out.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class TimeBaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDt;

    @LastModifiedBy
    private LocalDateTime upDt;
}
