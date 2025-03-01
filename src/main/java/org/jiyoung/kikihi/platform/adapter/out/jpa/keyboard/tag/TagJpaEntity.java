package org.jiyoung.kikihi.platform.adapter.out.jpa.keyboard.tag;


import jakarta.persistence.*;
import lombok.*;
import org.jiyoung.kikihi.platform.domain.keyboard.tag.Tag;

@Entity
@Table(name = "tags")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private String code;

    private String description;

    // from
    public static TagJpaEntity from(Tag tag) {
        return TagJpaEntity.builder()
                .tagId(tag.getId())
                .name(tag.getName())
                .code(tag.getCode())
                .description(tag.getDescription())
                .build();
    }

    // toDomain
    public Tag toDomain(){
        return Tag.builder()
                .id(tagId)
                .name(name)
                .code(code)
                .description(description)
                .build();
    }
}
