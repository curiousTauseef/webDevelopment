package com.junjunguo.shr.client.services;

import com.junjunguo.shr.client.model.Tag;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface TagServices {
    /**
     * @return a list of tags
     */
    List<Tag> listAllTags();

    /**
     * @param id tag id
     * @return Tag (only tag)
     */
    Tag getByTagId(long id);

    /**
     * @param label tag label
     * @return Tag (contains list of videos)
     */
    Tag getByTagLabel(String label);

    /**
     * @param label create a new Tag by given label
     */
    String createTag(String label);
}
