package com.junjunguo.aeep.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.googlecode.objectify.Key;
import com.junjunguo.aeep.backend.model.Event;
import com.junjunguo.aeep.backend.model.User;
import com.junjunguo.aeep.backend.utility.Constant;

import java.util.List;

import static com.junjunguo.aeep.backend.dao.OfyService.ofy;


/**
 * This file is part of appengineEndpoints
 * <p>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 09, 2016.
 */
@Api(name = "myEndpointsAPI", version = "v1",
     namespace = @ApiNamespace(ownerDomain = Constant.API_OWNER, ownerName = Constant.API_OWNER,
                               packagePath = Constant.API_PACKAGE_PATH))
@ApiClass(resource = "eventServices",
          clientIds = {Constant.ANDROID_CLIENT_ID, Constant.IOS_CLIENT_ID, Constant.WEB_CLIENT_ID},
          audiences = {Constant.AUDIENCE_ID})
public class EventServices {

    /* GET */

    /**
     * @return Lists all the video entities inserted in dataStore.
     */
    @ApiMethod(httpMethod = "GET")
    public List<Event> listEvents() {
        return ofy().load().type(Event.class).list();
    }

    /* GET */
    @ApiMethod(httpMethod = "GET")
    public List<Event> getEventsOwnerEmail(@Named("email") String email) {
        List<Key<Event>> eventsKey = findUser(email).getEvents();
        return ofy().load().type(Event.class).filter("eventsKey", eventsKey).list();
    }
//
    //    /* GET */
    //    @ApiMethod(httpMethod = "GET")
    //    public User getUserByEmail(@Named("email") String email) {
    //        return findUser(email);
    //    }

    /* POST */

    /**
     * @param user Inserts the user entity into App Engine datastore. It uses HTTP POST method.
     * @return result
     */
    @ApiMethod(httpMethod = "POST")
    public User createUser(User user) throws ConflictException {
        if (findUser(user.getEmail()) != null) {
            throw new ConflictException("user with email exist!");
        }
        ofy().save().entity(user).now();
        return user;
    }

    /* PUT */

    /**
     * Updates an entity. It uses HTTP PUT method.
     *
     * @param user User
     * @return updated user
     */
    @ApiMethod(httpMethod = "PUT")
    public User updateUser(User user) {
        ofy().save().entity(user).now();
        return user;
    }

    /* DELETE */

    /**
     * @param email user email
     * @return the deleted user
     */
    @ApiMethod(httpMethod = "DELETE")
    public User deleteUserByEmail(@Named("email") String email) {
        User user = findUser(email);
        if (user == null) {
            return null;
        }
        ofy().delete().entity(user).now();
        return user;
    }

    /**
     * @param email user email as id
     * @return user by given email
     */
    private User findUser(String email) {
        return ofy().load().type(User.class).id(email).now();
    }

}
