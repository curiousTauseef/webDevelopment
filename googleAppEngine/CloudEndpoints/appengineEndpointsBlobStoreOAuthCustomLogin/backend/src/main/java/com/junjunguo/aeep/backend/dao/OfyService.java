package com.junjunguo.aeep.backend.dao;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.junjunguo.aeep.backend.model.Event;
import com.junjunguo.aeep.backend.model.Tag;
import com.junjunguo.aeep.backend.model.TaggedEvent;
import com.junjunguo.aeep.backend.model.Person;
import com.junjunguo.aeep.backend.model.UsersEvents;

/**
 * This file is part of appengineEndpoints
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on January 07, 2016.
 */
public final class OfyService {
    /**
     * Default constructor, never called.
     */
    private OfyService() {
    }

    static {
        factory().register(Tag.class);
        factory().register(Person.class);
        factory().register(Event.class);
        factory().register(UsersEvents.class);
        factory().register(TaggedEvent.class);
    }

    /**
     * Returns the Objectify service wrapper.
     * @return The Objectify service wrapper.
     */
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    /**
     * Returns the Objectify factory service.
     * @return The factory service.
     */
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}