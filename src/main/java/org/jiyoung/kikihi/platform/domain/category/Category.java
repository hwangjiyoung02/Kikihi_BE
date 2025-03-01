package org.jiyoung.kikihi.platform.domain.category;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    private Long categoryId;

    private Category parent;

    private String name;

    private String code;

    private String description;

    // of
    public static Category of(Category parent, String name, String code, String description) {
        return Category.builder()
                .name(name)
                .parent(parent)
                .code(code)
                .description(description)
                .build();
    }

}