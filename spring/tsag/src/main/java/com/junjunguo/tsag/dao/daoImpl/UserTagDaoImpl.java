package com.junjunguo.tsag.dao.daoImpl;

import com.junjunguo.tsag.dao.UserTagDao;
import com.junjunguo.tsag.model.UserTag;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuoJunjun <junjunguo.com> on 16/10/15.
 */

public class UserTagDaoImpl implements UserTagDao {
    @Autowired
    private SessionFactory sessionFactory;

    public UserTagDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Integer> findTagsIdByUserId(String userId) {
        Query q = sessionFactory.getCurrentSession().createQuery("from USER_TAG where USER_ID = '" + userId + "'");
        if (q.list().isEmpty()) {
            return null;
        } else {
            List<Integer> taglist = new ArrayList();
            for (UserTag ut : ((List<UserTag>) q.list())) {
                taglist.add(ut.getTagId());
            }
            return taglist;
        }
    }

    public List<String> findUsersIdByTagId(int tagId) {
        Query q = sessionFactory.getCurrentSession().createQuery("from USER_TAG where TAG_ID = '" + tagId + "'");
        if (q.list().isEmpty()) {
            return null;
        } else {
            List<String> userIdlist = new ArrayList<String>();
            for (UserTag ut : ((List<UserTag>) q.list())) {
                userIdlist.add(ut.getUserId());
            }
            return userIdlist;
        }
    }

    public List<UserTag> findAll() {
        @SuppressWarnings("unchecked")
        List<UserTag> list = (List<UserTag>) sessionFactory.getCurrentSession()
                .createCriteria(UserTag.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return list;
    }

    public void save(UserTag userTag) {
        sessionFactory.getCurrentSession().saveOrUpdate(userTag);
    }

    public void delete(UserTag userTag) {
        sessionFactory.getCurrentSession().delete(userTag);
    }
}
