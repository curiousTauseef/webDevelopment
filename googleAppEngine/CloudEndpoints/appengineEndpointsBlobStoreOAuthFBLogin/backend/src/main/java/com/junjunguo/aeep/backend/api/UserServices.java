package com.junjunguo.aeep.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.junjunguo.aeep.backend.model.Event;
import com.junjunguo.aeep.backend.model.Person;
import com.junjunguo.aeep.backend.model.UsersEvents;
import com.junjunguo.aeep.backend.utility.Constant;

import java.util.ArrayList;
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
@ApiClass(resource = "userServices",
          clientIds = {Constant.ANDROID_CLIENT_ID, Constant.IOS_CLIENT_ID, Constant.WEB_CLIENT_ID},
          audiences = {Constant.AUDIENCE_ID})
public class UserServices {

    /* GET */

    /**
     * for testing
     * @return Lists all the user entities inserted in dataStore.
     */
    @ApiMethod(httpMethod = "GET")
    public List<Person> listUsers() {
        return ofy().load().type(Person.class).list();
    }

    /* GET */
    @ApiMethod(httpMethod = "GET")
    public Person getUserByEmail(@Named("email") String email) {
        Person person = findUser(email);
        if (person != null) {
            person.setEvents(getUserEvents(email));
        }
        return person;
    }


    /* POST */

    /**
     * @param person Inserts the user entity into App Engine datastore. It uses HTTP POST method.
     * @return result
     */
    @ApiMethod(httpMethod = "POST")
    public Person createUser(Person person) throws ConflictException {
        if (findUser(person.getEmail()) != null) {
            throw new ConflictException("user with email: " + person.getEmail() + " already exist!");
        }
        ofy().save().entity(person).now();
        return person;
    }

    /* PUT */

    /**
     * Updates an entity. It uses HTTP PUT method.
     * @param person User
     * @return updated user
     */
    @ApiMethod(httpMethod = "PUT")
    public Person updateUser(Person person, User user) throws OAuthRequestException {
        if (user == null) {
            throw new OAuthRequestException("NOT authorized! Please login!");
        } else if (findUser(user.getEmail()) == null) {
            throw new OAuthRequestException("NOT authorized! Please login with correct account!");
        } else {
            ofy().save().entity(person).now();
            return person;
        }
    }

    /* DELETE */

    /**
     * @param email user email
     * @return the deleted user
     */
    @ApiMethod(httpMethod = "DELETE")
    public Person deleteUserByEmail(@Named("email") String email, User user)
            throws NotFoundException, OAuthRequestException {
        if (user == null) {
            throw new OAuthRequestException("NOT authorized! Please login!");
        }
        Person person = findUser(email);
        if (person == null) {
            throw new NotFoundException("user with email: " + person.getEmail() + " not found!");
        }
        ofy().delete().entity(person).now();
        return person;
    }

    private List<Event> getUserEvents(String email) {
        List<Event> events = new ArrayList<>();
        List<UsersEvents> usersEvents = ofy().load().type(UsersEvents.class).filter("ownerEmail", email).list();
        log("user events : " + usersEvents.toString());
        if (usersEvents != null) {
            for (UsersEvents ue : usersEvents) {
                events.add(findEvent(ue.getEventId()));
            }
        }
        return events;
    }

    /**
     * @param email user email as id
     * @return user by given email
     */
    private Person findUser(String email) {
        return ofy().load().type(Person.class).id(email).now();
    }

    private Event findEvent(long id) {
        return ofy().load().type(Event.class).id(id).now();
    }

    private void log(String s) {
        System.out.println(this.getClass().getSimpleName() + "------" + s);
    }

}
