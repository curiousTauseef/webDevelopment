package com.junjunguo.shr.service;

import com.junjunguo.shr.service.model.Location;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/10/15.
 */
public interface LocationServices {

    List<Location> listAllLocations();

    Location getById(int id);
}
