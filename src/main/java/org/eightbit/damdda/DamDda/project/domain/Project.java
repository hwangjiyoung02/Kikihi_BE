package org.eightbit.damdda.DamDda.project.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.eightbit.damdda.DamDda.member.domain.Member;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore//✌순환참조 방지
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectTag> projectTags=new ArrayList<ProjectTag>();

    private String title;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String descriptionDetail;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long targetFunding;

    @ColumnDefault("0")
    private Long fundsReceive;

    @ColumnDefault("0")
    private Long supporterCnt;

    @ColumnDefault("0")
    private Long viewCnt;

    @ColumnDefault("0")
    private Long likeCnt;

    private String thumbnailUrl;

    private LocalDateTime submitAt;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;
}