package com.junjunguo.shae.service.serviceImpl;

import com.junjunguo.shae.dao.TagDao;
import com.junjunguo.shae.model.Tag;
import com.junjunguo.shae.service.TagService;
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
        return tagDao.findByLabel(label);
    }

    public Tag findByTagId(long id) {
        return tagDao.findById(id);
    }

    public List<Tag> findAllTags() {
        return tagDao.findAllTags();
    }

    public void addTag(String label) {
        tagDao.saveTag(new Tag(label));
    }

    public boolean isLabelExist(String label) {
        return tagDao.findByLabel(label) != null;
    }
}
