package com.junjunguo.aeep.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.junjunguo.aeep.backend.model.Tag;
import com.junjunguo.aeep.backend.utility.Constant;

import java.util.List;

import static com.junjunguo.aeep.backend.dao.OfyService.ofy;


/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
@Api(name = "myEndpointsAPI", version = "v1",
     namespace = @ApiNamespace(ownerDomain = Constant.API_OWNER, ownerName = Constant.API_OWNER,
                               packagePath = Constant.API_PACKAGE_PATH))
@ApiClass(resource = "tagServices",
          clientIds = {Constant.ANDROID_CLIENT_ID, Constant.IOS_CLIENT_ID, Constant.WEB_CLIENT_ID},
          audiences = {Constant.AUDIENCE_ID})
public class TagServices {

    /**
     * List tags list.
     * @return Lists all the user entities inserted in dataStore.
     */
    @ApiMethod(httpMethod = "GET", path = "tag")
    public List<Tag> listTags() {
        return ofy().load().type(Tag.class).list();
    }

    //    /**
    //     * Gets tag by id.
    //     * @param id the id
    //     * @return the tag by id
    //     */
    //    @ApiMethod(httpMethod = "GET", path = "tag/id")
    //    public Tag getTagById(@Named("id") long id) {
    //        return findTag(id);
    //    }

    /**
     * Gets tag by label.
     * @param tag the label
     * @return the tag by label
     */
    @ApiMethod(httpMethod = "GET", path = "tag/tag")
    public Tag getTagByLabel(@Named("tag") String tag) {
        return findTag(tag);
    }


    @ApiMethod(httpMethod = "POST", path = "tag")
    public Tag createTag(@Named("tag") String tag) {
        Tag t = findTag(tag);
        if (t != null) {
            return t;                   // return tag from data store
        }
        Tag nt = new Tag(tag);
        ofy().save().entity(nt).now(); // save new tag
        return nt;
    }

    //    /**
    //     * Delete tag by id tag.
    //     * @param id the id
    //     * @return the tag if succeed, null if not found in data store
    //     */
    //    @ApiMethod(httpMethod = "DELETE", path = "tag/id")
    //    public Tag deleteTagById(@Named("id") long id) {
    //        Tag tag = findTag(id);
    //        if (tag != null) {
    //            ofy().delete().entity(tag).now();
    //            return tag;
    //        }
    //        return null;
    //    }

    /**
     * Delete tag by label tag.
     * @param tag the label
     * @return the tag if succeed, null if not found in data store
     */
    @ApiMethod(httpMethod = "DELETE", path = "tag")
    public Tag deleteTagByLabel(@Named("tag") String tag) {
        Tag t = findTag(tag);
        if (t != null) {
            ofy().delete().entity(t).now();
            return t;
        }
        return null;
    }

    //    private Tag findTag(long id) {
    //        return ofy().load().type(Tag.class).id(id).now();
    //    }

    //    private Tag findTag(String label) {
    //        return ofy().load().type(Tag.class).filter("label=", label).first().now();
    //    }

    private Tag findTag(String label) {
        return ofy().load().type(Tag.class).id(label).now();
    }
}
