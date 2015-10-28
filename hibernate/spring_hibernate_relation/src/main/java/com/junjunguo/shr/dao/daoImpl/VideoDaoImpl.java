package com.junjunguo.shr.dao.daoImpl;

import com.junjunguo.shr.dao.VideoDao;
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
public class VideoDaoImpl implements VideoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public VideoDaoImpl() {
    }

    public VideoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Video findById(int id) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Video where ID = '" + id + "'");
        return !q.list().isEmpty() ? (Video) q.list().get(0) : null;
    }

    public List<Video> findByEmail(String email) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Video where EMAIL = '" + email + "'");
        return !q.list().isEmpty() ? (List<Video>) q.list() : null;
    }

    @Transactional
    public List<Video> findByTitle(String title) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Video where TITLE = '" + title + "'");
        return !q.list().isEmpty() ? (List<Video>) q.list() : null;
    }

    @Transactional
    public void saveVideo(Video video) {

        sessionFactory.getCurrentSession().saveOrUpdate(video);
    }

    @Transactional
    public void deleteVideoById(int id) {
        sessionFactory.getCurrentSession().delete(findById(id));
    }

    @Transactional
    public List<Video> findAllVideos() {
        @SuppressWarnings("unchecked")
        List<Video> videos = (List<Video>) sessionFactory.getCurrentSession()
                                                         .createCriteria(Video.class)
                                                         .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return videos;
    }

    public boolean hasVideo(int id) {
        Query q = sessionFactory.getCurrentSession().createQuery("from Video where id = '" + id + "'");
        return !q.list().isEmpty() ? ((Video) q.list().get(0)).isHasVideo() : false;
    }
}
