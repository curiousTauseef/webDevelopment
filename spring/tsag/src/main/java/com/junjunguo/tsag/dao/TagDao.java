package com.junjunguo.tsag.dao;

import com.junjunguo.tsag.model.Tag;
import com.junjunguo.tsag.model.User;

import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 16/10/15.
 */

public interface TagDao {
    Tag findTagById(int id);

    Tag findTagByLabel(String label);

    Tag findByLabelInitialized(String label);

    List<Tag> findAllTags();

    void saveTag(String tag);

    void saveTag(Tag tag);

    void deleteTag(String tag);

    List<User> findUsersByTag(int id);
}
