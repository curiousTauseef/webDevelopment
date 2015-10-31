package com.junjunguo.shr.service;

import com.junjunguo.shr.model.Tag;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public interface TagService {
    /**
     * @param label tag label
     * @return Tag (contains list of videos)
     */
    Tag findByLabel(String label);

    /**
     * @param id tag id
     * @return Tag (only tag)
     */
    Tag findByTagId(long id);

    /**
     * @return a list of tags
     */
    List<Tag> findAllTags();

    void addTag(String label);

    boolean isLabelExist(String label);
}
