package org.jiyoung.kikihi.platform.domain.keyboard.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    private Long id;

    private String name;

    private String code;

    private String description;

    ///  생성자
    public static Tag of(String name,String code, String description) {
        return Tag.builder()
                .name(name)
                .code(code)
                .description(description)
                .build();
    }



}
