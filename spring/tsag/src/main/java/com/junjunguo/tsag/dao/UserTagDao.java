package com.junjunguo.tsag.dao;

import com.junjunguo.tsag.model.Tag;
import com.junjunguo.tsag.model.UserTag;

import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 16/10/15.
 */

public interface UserTagDao {
    List<Integer> findTagsIdByUserId(String userId);

    List<String> findUsersIdByTagId(int tagId);

    List<UserTag> findAll();

    void save(UserTag userTag);

    void delete(UserTag userTag);
}
