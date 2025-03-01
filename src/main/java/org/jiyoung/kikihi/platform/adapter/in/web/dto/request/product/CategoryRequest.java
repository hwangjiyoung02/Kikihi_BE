package org.jiyoung.kikihi.platform.adapter.in.web.dto.request.product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryRequest {

    private Long parentId;
    private String name;
    private String code;
    private String description;
}
