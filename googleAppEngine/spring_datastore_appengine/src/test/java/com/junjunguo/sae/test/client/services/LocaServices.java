package com.junjunguo.sae.test.client.services;

import com.junjunguo.sae.test.client.model.Location;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface LocaServices {
    /**
     * @return list of all locations
     */
    List<Location> listAllLocations();

    /**
     * @param id location id (Generated from server)
     * @return Location
     */
    Location getById(long id);
}
