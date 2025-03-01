package org.jiyoung.kikihi.platform.application.out.keyboard.tag;

import org.jiyoung.kikihi.platform.domain.keyboard.tag.Tag;

import java.util.Optional;

public interface TagPort {

    // 태그 저장하기
    Tag SaveTage(Tag tag);

    // 태그 가져오기
    Optional<Tag> loadTageById(Long id);

}
