package com.junjunguo.shr.client.services;

import com.junjunguo.shr.client.model.Location;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface LocaServices {

    List<Location> listAllLocations();

    Location getById(long id);
}
