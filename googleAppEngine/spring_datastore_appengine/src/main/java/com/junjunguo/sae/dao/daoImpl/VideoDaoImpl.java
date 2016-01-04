package com.junjunguo.sae.dao.daoImpl;

import com.junjunguo.sae.dao.VideoDao;
import com.junjunguo.sae.model.Location;
import com.junjunguo.sae.model.Video;
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
public class VideoDaoImpl implements VideoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public VideoDaoImpl() {
    }

    public VideoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Video findById(long id) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Video where ID = '" + id + "'");
        return !q.list().isEmpty() ? (Video) q.list().get(0) : null;
    }

    public List<Video> findByEmail(String email) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Video where OWNER_EMAIL = '" + email + "'");
        return !q.list().isEmpty() ? (List<Video>) q.list() : null;
    }

    @Transactional
    public List<Video> findByTitle(String title) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Video where TITLE = '" + title + "'");
        return !q.list().isEmpty() ? (List<Video>) q.list() : null;
    }

    public List<Video> findByTag(long id) {
        Query q = sessionFactory.getCurrentSession().createQuery(
                "from Video v inner join v.tags t where t.id = '" + id + "'");
        return q.list().isEmpty() ? null : (List<Video>) q.list();
    }

    //TODO: need to change later for better performance :
    //https://en.wikipedia.org/wiki/Oracle_Spatial_and_Graph ||http://postgis.refractions.net/
    // http://mysql.rjweb.org/doc.php/latlng
    public List<Video> findNearBy(Location location, double boundary) {
        log("find near by");
        Query q = sessionFactory.getCurrentSession().createQuery(
                "from Video v inner join v.location l " +
                "where l.latitude < '" + (location.getLatitude() + boundary) + "'" +
                "and l.latitude > '" + (location.getLatitude() - boundary) + "'" +
                "and l.longitude < '" + (location.getLongitude() + boundary) + "'" +
                "and l.longitude > '" + (location.getLongitude() - boundary) + "'");
        log("query finished " + q);
        return q.list().isEmpty() ? null : (List<Video>) q.list();
    }

    @Transactional
    public void saveVideo(Video video) {
        sessionFactory.getCurrentSession().saveOrUpdate(video);
    }

    @Transactional
    public void deleteVideoById(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Transactional
    public List<Video> findAllVideos() {
        @SuppressWarnings("unchecked")
        List<Video> videos = (List<Video>) sessionFactory.getCurrentSession()
                                                         .createCriteria(Video.class)
                                                         .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                                                         .list();
        return videos;
    }

    public void log(String s) {
        System.out.println(this.getClass().getSimpleName() + "- - - - - - " + s);
    }
}
