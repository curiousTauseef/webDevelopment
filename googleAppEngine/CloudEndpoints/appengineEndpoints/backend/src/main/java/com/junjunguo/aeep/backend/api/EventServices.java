package com.junjunguo.aeep.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Query;
import com.junjunguo.aeep.backend.model.Event;
import com.junjunguo.aeep.backend.model.User;
import com.junjunguo.aeep.backend.utility.Constant;

import java.util.List;

import static com.junjunguo.aeep.backend.dao.OfyService.ofy;


/**
 * This file is part of appengineEndpoints
 * <p/>
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
     * List events list.
     * @return Lists all the video entities inserted in dataStore.
     */
    @ApiMethod(httpMethod = "GET", path = "event/list")
    public List<Event> listEvents() {
        return ofy().load().type(Event.class).list();
    }

    /**
     * Gets events by owner's email.
     * @param email the email
     * @return the events by email
     */
/* GET */
    @ApiMethod(httpMethod = "GET", path = "event/email")
    public List<Event> getEventsByEmail(@Named("email") String email) {
        return ofy().load().type(Event.class).filter("ownerEmail", email).list();
    }

    /* GET */

    /**
     * Gets event by event id.
     * @param id the id
     * @return the event by id
     */
    @ApiMethod(httpMethod = "GET", path = "event/id")
    public Event getEventById(@Named("id") long id) {
        return findEvent(id);
    }

    /* GET */

    /**
     * Gets events nearby.
     * @param radius the radius in meters
     * @param center the center
     * @return the events nearby
     */
    @ApiMethod(httpMethod = "GET", path = "event/nearby")
    public List<Event> getEventsNearby(@Named("radius") double radius, GeoPt center) {
        Query.Filter f = new Query.StContainsFilter("location", new Query.GeoRegion.Circle(center, radius));
        return ofy().load().type(Event.class).filter(f).list();
    }

    /* POST */

    /**
     * Create event.
     * @param event Inserts the event entity into App Engine datastore. It uses HTTP POST method.
     * @return result event
     * @throws ConflictException the conflict exception
     */
    @ApiMethod(httpMethod = "POST", path = "event/create")
    public Event createEvent(Event event) throws ConflictException {
        if (false) {//TODO: check if already uploaded
            throw new ConflictException("event already uploaded!");
        }
        ofy().save().entity(event).now();   // save event
        return event;
    }

    /* PUT */

    /**
     * Update event event.
     * @param event the event
     * @return the event
     */
    @ApiMethod(httpMethod = "PUT", path = "event/update")
    public Event updateEvent(Event event) {
        ofy().save().entity(event).now();
        return event;
    }

    /* DELETE */

    /**
     * Delete event by id event.
     * @param id the id
     * @return the event
     */
    @ApiMethod(httpMethod = "DELETE", path = "event/delete")
    public Event deleteEventById(@Named("id") long id) {
        Event event = findEvent(id);
        if (event == null) {
            return null;
        }
        ofy().delete().entity(event).now();
        return event;
    }

    private Event findEvent(long id) {
        return ofy().load().type(Event.class).id(id).now();
    }

    private User findUser(String email) {
        return ofy().load().type(User.class).id(email).now();
    }

}
