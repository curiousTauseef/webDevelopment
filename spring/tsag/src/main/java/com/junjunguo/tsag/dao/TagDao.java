package com.junjunguo.tsag.dao;

import com.junjunguo.tsag.model.Tag;

import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 16/10/15.
 */

public interface TagDao {
    Tag findTagById(int id);

    Tag findTagByLabel(String label);

    List<String> findAllTags();

    void saveTag(String tag);

    void deleteTag(String tag);
}
