package com.junjunguo.shr.service.serviceImpl;

import com.junjunguo.shr.dao.TagDao;
import com.junjunguo.shr.model.Tag;
import com.junjunguo.shr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Service("tagService")
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    public Tag findByLabel(String label) {
        System.out.println("tag service impl find label: " + label);
        return tagDao.findByLabel(label);
    }

    public Tag findByTagId(long id) {
        return tagDao.findById(id);
    }

    public List<Tag> findAllTags() {
        return tagDao.findAllTags();
    }

    public void addTag(String label) {
        System.out.println("tag service impl " + label);
        tagDao.saveTag(new Tag(label));
    }
}
