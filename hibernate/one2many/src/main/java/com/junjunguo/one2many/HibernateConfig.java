package com.junjunguo.one2many;

import com.junjunguo.one2many.model.Tag;
import com.junjunguo.one2many.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

/**
 * Created by GuoJunjun <junjunguo.com> on 25/10/15.
 * <p/>
 * <a>Programmatic configuration: http://docs.jboss.org/hibernate/core/3.3/reference/en/html/session-configuration.html</a>
 */

public class HibernateConfig {

    private static HibernateConfig instance;

    private HibernateConfig() {
    }

    public static HibernateConfig getInstance() {
        if (instance == null) {
            instance = new HibernateConfig();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.setProperties(getHibernateProperties());
            cfg.addAnnotatedClass(Tag.class);
            cfg.addAnnotatedClass(User.class);
            return cfg.buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * <li> hibernate.connection.datasource	datasource JNDI name</li>
     * <p/>
     * <li>  hibernate.jndi.url	URL of the JNDI provider (optional)</li>
     * <p/>
     * <li>  hibernate.jndi.class	class of the JNDI InitialContextFactory (optional)</li>
     * <p/>
     * <li> hibernate.connection.username	database user (optional)</li>
     * <p/>
     * <li>  hibernate.connection.password	database user password (optional)</li>
     *
     * @return Hibernate Datasource Properties
     */
    public Properties getDataSource(Properties properties) {
        properties.put("connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/tsag_test");
        properties.put("hibernate.connection.username", "junjunguo");
        properties.put("hibernate.connection.password", "passwords");
        return properties;
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
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.connection.charSet", "UTF-8");
        properties.put("hibernate.connection.characterEncoding", "UTF-8");
        properties.put("hibernate.connection.useUnicode", "true");
        properties.put("hibernate.dialect", "com.junjunguo.one2many.CustomMysqlDialect");
        return getDataSource(properties);
    }
}
