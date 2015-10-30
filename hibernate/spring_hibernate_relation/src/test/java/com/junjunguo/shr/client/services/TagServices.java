package com.junjunguo.shr.client.services;

import com.junjunguo.shr.client.model.Tag;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface TagServices {

    List<Tag> listAllTags();

    Tag getByTagId(long id);

    Tag getByTagLabel(String label);

    String createTag(String label);
}
