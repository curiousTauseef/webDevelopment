package com.junjunguo.tsag.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 24/10/15.
 */

public class TagList {
    @JsonManagedReference
    public List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
