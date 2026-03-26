package com.baozipu.service;

import com.baozipu.entity.Tag;
import java.util.List;

public interface TagService {
    List<Tag> list();
    Tag getById(Long id);
    Tag create(Tag tag);
    void update(Long id, Tag tag);
    void delete(Long id);
}