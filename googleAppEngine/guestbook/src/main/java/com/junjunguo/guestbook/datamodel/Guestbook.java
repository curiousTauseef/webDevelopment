package com.junjunguo.guestbook.datamodel;

/**
 * This file is part of guestbook.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 03/01/16.
 * <p/>
 * Guestbook class to represent an entire guestbook. While this example doesn't explicitly create a Guestbook object in
 * the datastore, it uses this class as part of the datastore key for the Greeting objects. This demonstrates how you
 * might define keys so that all of the greetings in a guestbook could be updated in a single datastore transaction.
 */

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * The @Entity tells Objectify about our entity.  We also register it in OfyHelper.java -- very important.
 * <p/>
 * This is never actually created, but gives a hint to Objectify about our Ancestor key.
 */
@Entity
public class Guestbook {
    @Id public String book;
}
