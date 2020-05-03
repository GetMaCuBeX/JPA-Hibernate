package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Config {

    public static String HIBERNATE_CONFIG = "/hibernate/hibernate.cfg.xml";

    /*
    CREATE AND RETURN SESSION FACTORY
     */
    public static SessionFactory createSessionFactory(String config, Class entityClass) {
        return new Configuration().configure(config).addAnnotatedClass(entityClass).buildSessionFactory();
    }
}
