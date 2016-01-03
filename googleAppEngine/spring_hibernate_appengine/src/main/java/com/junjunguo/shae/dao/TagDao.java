package com.junjunguo.shae.dao;

import com.junjunguo.shae.model.Tag;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
public interface TagDao {
    Tag findByLabel(String label);

    Tag findById(long id);

    List<Tag> findAllTags();

    void saveTag(Tag tag);
}
