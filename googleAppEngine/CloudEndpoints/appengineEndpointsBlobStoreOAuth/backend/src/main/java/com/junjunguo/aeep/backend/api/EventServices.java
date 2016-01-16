package com.junjunguo.aeep.backend.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiClass;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.ConflictException;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Query;
import com.junjunguo.aeep.backend.model.BlobAccess;
import com.junjunguo.aeep.backend.model.Event;
import com.junjunguo.aeep.backend.model.QueryWrapper;
import com.junjunguo.aeep.backend.model.Tag;
import com.junjunguo.aeep.backend.model.TaggedEvent;
import com.junjunguo.aeep.backend.model.Person;
import com.junjunguo.aeep.backend.model.UsersEvents;
import com.junjunguo.aeep.backend.utility.Constant;

import java.util.ArrayList;
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
     * @param radius    the radius in meters
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the events nearby
     */
    @ApiMethod(httpMethod = "GET", path = "event/nearby")
    public List<Event> getEventsNearby(@Named("radius") double radius, @Named("latitude") float latitude,
            @Named("longitude") float longitude) {
        //        log("nearby: radius: " + radius + "m; center: " + center.getLatitude() + " " + center.getLongitude());
        Query.Filter f = new Query.StContainsFilter("location",
                new Query.GeoRegion.Circle(new GeoPt(latitude, longitude), radius));
        log("filter: " + f.toString());
        return ofy().load().type(Event.class).filter(f).list();
    }


    /**
     * Gets tagged events nearby.
     * @param radius    the radius in meters
     * @param latitude  the latitude of the center
     * @param longitude the longitude of the center
     * @param tag1      the tag 1 & nullable tag 1, 2, 3 are and relations, all can be empty
     * @param tag2      the tag 2 & nullable
     * @param tag3      the tag 3 nullable
     * @return the tagged events nearby
     */
    @ApiMethod(httpMethod = "GET", path = "event/taggedeventnearby")
    public List<Event> getTaggedEventsNearby(@Named("radius") double radius, @Named("latitude") float latitude,
            @Named("longitude") float longitude, @Named("tag1") @Nullable String tag1,
            @Named("tag2") @Nullable String tag2, @Named("tag3") @Nullable String tag3) {
        Query.Filter f = new Query.StContainsFilter("location",
                new Query.GeoRegion.Circle(new GeoPt(latitude, longitude), radius));
        log("filter: " + f.toString());
        List<Event> events = ofy().load().type(Event.class).filter(f).list();

        ArrayList<Tag> tags = new ArrayList<>();

        if (tag1 != null && tag1.length() > 0) {
            Tag tag = findTag(tag1);
            log("find " + tag1 + " result : " + tag);
            tags.add(findTag(tag1));
        }
        if (tag2 != null && tag2.length() > 0) tags.add(findTag(tag2));
        if (tag3 != null && tag3.length() > 0) tags.add(findTag(tag3));
        if (tags.isEmpty()) {
            return events;
        } else {
            // events loaded from data store Event entity has no tags, init it  to fill its tag
            events = initEvents(events);
            List<Event> result = new ArrayList<>();
            for (Event e : events) {
                log("event tags: " + e.getTags().toString() + "; tags: " + tags);
                if (e.getTags().containsAll(tags)) {
                    log("event tags: " + e.getTags().toString() + " >= tags: " + tags);
                    result.add(e);
                }
            }
            return result;
        }
    }

    /**
     * Gets events nearby.
     * @param qw the qw
     * @return the events nearby
     */
    @ApiMethod(httpMethod = "GET", path = "event/querywrapper")
    public List<Event> getEventsByQueryWrapper(QueryWrapper qw) {
        log("querywrapper: " + qw.toString() + " .getCenter: " + qw.getCenter() + " radius: " + qw.getRadius() +
                " tags: " + qw.getTags());
        if (qw.getCenter() == null) {
            return null;
        }
        Query.Filter f =
                new Query.StContainsFilter("location", new Query.GeoRegion.Circle(qw.getCenter(), qw.getRadius()));
        log("q w: filter " + f.toString());
        // get filtered events
        List<Event> events = ofy().load().type(Event.class).filter(f).list();
        if (qw.getTags().isEmpty()) {
            return events;
        } else {
            // events loaded from data store Event entity has no tags, init it  to fill its tag
            events = initEvents(events);
            List<Event> result = new ArrayList<>();
            for (Event e : events) {
                if (e.getTags().containsAll(qw.getTags())) {
                    result.add(e);
                }
            }
            return result;
        }
    }

    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @ApiMethod(httpMethod = "GET", path = "event/uploadurl")
    public BlobAccess getUploadUrl() {
        log("getUploadUrl called");
        log("rul" + blobstoreService.createUploadUrl("/videoservice"));

        return new BlobAccess(blobstoreService.createUploadUrl("/videoservice"));
    }

    /**
     * events loaded from data store Event entity has no tags, init it  to fill its tag
     * @param events list
     * @return events list with tags objects in
     */
    private List<Event> initEvents(List<Event> events) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            e.setTags(getEventTags(e));
            result.add(e);
        }
        return result;
    }

    private List<Tag> getEventTags(Event e) {
        List<Tag> tags = new ArrayList<>();
        List<TaggedEvent> te = ofy().load().type(TaggedEvent.class).filter("eventId", e.getId()).list();
        for (TaggedEvent t : te) {
            tags.add(ofy().load().type(Tag.class).id(t.getTagId()).now());
        }
        return tags;
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
        saveUserEvent(event);
        saveTaggedEvent(event);
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
        saveTaggedEvent(event);
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

    private void saveUserEvent(Event event) {
        ofy().save().entity(new UsersEvents(event.getId(), event.getOwnerEmail())).now();
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
                ofy().save().entity(tag).now();
            }
            log("event.get id: " + event.getId() + " get tags: " + event.getTags());
            try {
                TaggedEvent taggedEvent = new TaggedEvent(event.getId(), tag.getLabel());
                log(taggedEvent.toString());
                ofy().save().entity(taggedEvent).now();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

    private boolean hasTag(Tag tag) {
        //TODO: crate a cache instance to sync the data store when needed and compare with search data store every time
        return (ofy().load().type(Tag.class).filterKey(tag).first().now() != null);
    }

    private Tag findTag(String label) {
        return ofy().load().type(Tag.class).id(label).now();
    }

    private Event findEvent(long id) {
        return ofy().load().type(Event.class).id(id).now();
    }

    private Person findUser(String email) {
        return ofy().load().type(Person.class).id(email).now();
    }

    private void log(String s) {
        System.out.println(this.getClass().getSimpleName() + "------" + s);
    }
}
