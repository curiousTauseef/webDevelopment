package com.junjunguo.shr.dao.daoImpl;

import com.junjunguo.shr.dao.LocationDao;
import com.junjunguo.shr.model.Location;
import com.junjunguo.shr.model.Video;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Repository
public class LocationDaoImpl implements LocationDao {
    @Autowired
    private SessionFactory sessionFactory;

    public LocationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public LocationDaoImpl() {
    }

    @Transactional
    public List<Location> findAllLocations() {
        @SuppressWarnings("unchecked")
        List<Location> locations = (List<Location>) sessionFactory.getCurrentSession()
                                                                  .createCriteria(Location.class)
                                                                  .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                                                                  .list();
        return locations;
    }

    @Transactional
    public Location findById(long id) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Location where ID = '" + id + "'");
        return !q.list().isEmpty() ? (Location) q.list().get(0) : null;
    }
}
