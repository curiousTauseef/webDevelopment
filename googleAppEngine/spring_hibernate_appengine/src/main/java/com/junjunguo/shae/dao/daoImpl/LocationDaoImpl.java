package com.junjunguo.shae.dao.daoImpl;

import com.junjunguo.shae.dao.LocationDao;
import com.junjunguo.shae.model.Location;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
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
        List<Location> locations =
                (List<Location>) sessionFactory.getCurrentSession()
                                               .createCriteria(Location.class)
                                               .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                                               .list();
        return locations;
    }

    @Transactional
    public Location findById(long id) {
        Query q = sessionFactory.getCurrentSession().createQuery(
                "from Location where " +
                "ID = '" + id + "'");
        return !q.list().isEmpty() ? (Location) q.list().get(0) : null;
    }

    public Location findByLocation(Location l) {
        @SuppressWarnings("unchecked")
        List<Location> locations =
                sessionFactory.getCurrentSession().createCriteria(Location.class)
                              .add(Restrictions.conjunction()
                                               .add(Property.forName("latitude").eq(l.getLatitude()))
                                               .add(Property.forName("longitude").eq(l.getLongitude()))
                                               .add(Property.forName("altitude").eq(l.getAltitude()))).list();
        return !locations.isEmpty() ? locations.get(0) : null;
    }
}
