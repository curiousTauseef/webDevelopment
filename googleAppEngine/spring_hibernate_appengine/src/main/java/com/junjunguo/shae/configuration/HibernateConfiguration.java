package com.junjunguo.shae.configuration;

import com.google.appengine.api.utils.SystemProperty;
import com.junjunguo.shae.dao.LocationDao;
import com.junjunguo.shae.dao.TagDao;
import com.junjunguo.shae.dao.UserDao;
import com.junjunguo.shae.dao.VideoDao;
import com.junjunguo.shae.dao.daoImpl.LocationDaoImpl;
import com.junjunguo.shae.dao.daoImpl.TagDaoImpl;
import com.junjunguo.shae.dao.daoImpl.UserDaoImpl;
import com.junjunguo.shae.dao.daoImpl.VideoDaoImpl;
import com.junjunguo.shae.model.Location;
import com.junjunguo.shae.model.Tag;
import com.junjunguo.shae.model.User;
import com.junjunguo.shae.model.Video;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * This file is part of spring_hibernate_relation
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 25/10/15.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.tsag.server.configuration"})
public class HibernateConfiguration {
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        if (SystemProperty.environment.value() ==
            SystemProperty.Environment.Value.Production) {

            dataSource.setDriverClassName("com.mysql.jdbc.GoogleDriver");
            dataSource.setUrl("jdbc:google:mysql://your-project-id:your-instance-name/demo");
        } else {
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/tsag_test");
        }
        //        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //        dataSource.setUrl("jdbc:mysql://localhost:3306/tsag_test");
        dataSource.setUsername("junjunguo");
        dataSource.setPassword("passwords");
        return dataSource;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        properties.put("hibernate.connection.charSet", "UTF-8");
        properties.put("hibernate.connection.characterEncoding", "UTF-8");
        properties.put("hibernate.connection.useUnicode", "true");
        properties.put("hibernate.dialect", "com.junjunguo.shae.util.CustomMysqlDialect");
        return properties;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addProperties(getHibernateProperties());
        sessionBuilder.addAnnotatedClasses(User.class, Tag.class, Video.class, Location.class);
        return sessionBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);
        return transactionManager;
    }

    @Autowired
    @Bean(name = "userDao")
    public UserDao getUserDao(SessionFactory sessionFactory) {
        return new UserDaoImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "videoDao")
    public VideoDao getVideoDao(SessionFactory sessionFactory) {
        return new VideoDaoImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "locationDao")
    public LocationDao getLocationDao(SessionFactory sessionFactory) {
        return new LocationDaoImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "tagDao")
    public TagDao getTagDao(SessionFactory sessionFactory) {
        return new TagDaoImpl(sessionFactory);
    }
}