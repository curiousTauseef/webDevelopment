package com.junjunguo.shr.service.serviceImpl;

import com.junjunguo.shr.dao.LocationDao;
import com.junjunguo.shr.model.Location;
import com.junjunguo.shr.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService{
    @Autowired
    private LocationDao locationDao;

    public List<Location> findAllLocations() {
        return locationDao.findAllLocations();
    }

    public Location findById(int id) {
        return locationDao.findById(id);
    }
}
