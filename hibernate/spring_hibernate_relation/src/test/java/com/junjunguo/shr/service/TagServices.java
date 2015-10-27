package com.junjunguo.shr.service;

import com.junjunguo.shr.service.model.Tag;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface TagServices {

    List<Tag> listAllTags();

    Tag getByTagId(int id);

    Tag getByTagLabel(String label);

    String createTag(String label);
}
