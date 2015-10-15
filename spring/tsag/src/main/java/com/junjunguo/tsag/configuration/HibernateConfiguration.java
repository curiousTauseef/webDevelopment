package com.junjunguo.tsag.configuration;

import javax.sql.DataSource;

import com.junjunguo.tsag.dao.UserDao;
import com.junjunguo.tsag.dao.daoImpl.UserDaoImpl;
import com.junjunguo.tsag.model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * Created by GuoJunjun <junjunguo.com> on 11/10/15.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.junjunguo.tsag.configuration"})
public class HibernateConfiguration {

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tsag_test");
        dataSource.setUsername("junjunguo");
        dataSource.setPassword("passwords");

        return dataSource;
    }

    /*
    # hibernate.hbm2ddl.auto
    Automatically validates or exports schema DDL to the database when
    the SessionFactory is created. With create-drop, the database schema will be dropped when
    the SessionFactory is closed explicitly.

    e.g. validate | update | create | create-drop

    validate:       validate the schema, makes no changes to the database.
    update:         update the schema. {Not best for production}
    create:         creates the schema, destroying previous data.
    create-drop:    drop the schema at the end of the session.

    # hibernate.show_sql
    which specifies whether the sql queries will be shown in the console or the logger
     */
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.connection.charSet", "UTF-8");
//        properties.put("hibernate.connection.characterEncoding", "UTF-8");
//        properties.put("hibernate.connection.useUnicode", "true");
        properties.put("hibernate.dialect", "com.junjunguo.tsag.util.CustomMysqlDialect");
        return properties;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addProperties(getHibernateProperties());
        sessionBuilder.addAnnotatedClasses(User.class);
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
}
