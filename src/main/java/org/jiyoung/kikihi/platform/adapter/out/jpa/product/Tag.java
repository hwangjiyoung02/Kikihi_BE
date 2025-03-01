package org.jiyoung.kikihi.platform.adapter.out.jpa.product;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tags")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
