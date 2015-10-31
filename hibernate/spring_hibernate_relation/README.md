#Spring framework, Hibernate ORM
##content

- Many to Many ORM
- One to Many ORM
- Many to One ORM

##entity relationship diagram

(this is a general view, some attributes might be different from the real Class)

![entity relationship diagram](doc/orm.png)

##Native SQL Query VS the Hibernate Query Language:

###Native SQL Query Language:

```
        Query q = sessionFactory.getCurrentSession().createSQLQuery(
                "select Video.* from Video " +
                "inner join VIDEO_TAG on VIDEO.ID = VIDEO_TAG.VIDEO_ID " +
                "where TAG_ID = '" + id + "'");
        return q.list().isEmpty() ? null : (List<Video>) q.list();
```
return an unmapped List<Video> object which is directly form the 'VIDEO' table,
        
        - tags not linked
        - owner not linked (only an email address)


###The Hibernate Query Language:


```
        Query q = sessionFactory.getCurrentSession().createQuery(
                "from Video v inner join v.tags t where t.id = '" + id + "'");
        return q.list().isEmpty() ? null : (List<Video>) q.list();
```

returns a full linked List<video> object with its tags and owner information.

        - `from Video v` = `from Video as v`
        - `v inner join v.tags` : 
                - Video has tags (List<Tag>) 
                - Video - Tag ---> many to many relation, which is mapped already, so don't need to think the `VIDEO_TAG` middle table.
        - `v.tags t` = `v.tags as t`
        - `t.id = id`
                - tag with id = given id
   
                    