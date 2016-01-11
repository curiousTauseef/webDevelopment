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
import com.junjunguo.aeep.backend.model.QueryWrapper;
import com.junjunguo.aeep.backend.model.Tag;
import com.junjunguo.aeep.backend.model.TaggedEvent;
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

    //    /**
    //     * Gets events nearby.
    //     * @param radius the radius in meters
    //     * @param center the center
    //     * @return the events nearby
    //     */
    //    @ApiMethod(httpMethod = "GET", path = "event/nearby")
    //    public List<Event> getEventsNearby(@Named("radius") double radius, GeoPt center) {
    //        Query.Filter f = new Query.StContainsFilter("location", new Query.GeoRegion.Circle(center, radius));
    //        return ofy().load().type(Event.class).filter(f).list();
    //    }

    /**
     * Gets events nearby.
     * @return the events nearby
     */
    @ApiMethod(httpMethod = "GET", path = "event/nearby")
    public List<Event> getEventsNearby(QueryWrapper qw) {
        if (qw.getCenter() == null) {
            return null;
        }
        if (qw.getTags().isEmpty()) {
            Query.Filter f =
                    new Query.StContainsFilter("location", new Query.GeoRegion.Circle(qw.getCenter(), qw.getRadius()));
            return ofy().load().type(Event.class).filter(f).list();
        } else {
            
            return ;
        }
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
        saveTaggedEvent(event);
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
        saveTaggedEvent(event);
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
        deleteRelevent(event);
        ofy().delete().entity(event).now();
        return event;
    }

    private void deleteRelevent(Event event) { // find all tags of the event
        List taggedEvents = ofy().load().type(TaggedEvent.class).filter("eventId", event.getId()).list();
        ofy().delete().entities(taggedEvents).now(); // delete taggedEvents from data store
    }

    private void saveTaggedEvent(Event event) {
        for (Tag tag : event.getTags()) {
            if (!hasTag(tag)) {
                tag = new Tag(tag.getLabel());
                tag.addEvent(event);
                ofy().save().entity(tag);
            }
            TaggedEvent taggedEvent = new TaggedEvent(event.getId(), tag.getLabel());
            ofy().save().entity(taggedEvent);
        }
    }

    private boolean hasTag(Tag tag) {
        //TODO: crate a cache instance to sync the data store when needed and compare with search data store every time
        return (ofy().load().type(Tag.class).filterKey(tag).first().now() != null);
    }

    private Event findEvent(long id) {
        return ofy().load().type(Event.class).id(id).now();
    }

    private User findUser(String email) {
        return ofy().load().type(User.class).id(email).now();
    }

}
