package com.mcbx.crud;

import hibernate.Config;
import com.mcbx.entity.Authors;
import java.util.List;
import static java.util.Objects.isNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Create {

    static SessionFactory factory;
    static Session session;

    public static void main(String[] args) {
        try {
            createSessionFactory(Authors.class);
            Authors author = new Authors("Jojo", "Scooby", "09587931547", "Male");
            addAuthor(author);
            displayObject(author);
        } finally {
            factory.close();
        }
    }

//------------------------------------------------------------------------------ ADD OBJECT
    public static void addAuthor(Authors authorsObj) {
        try {
            session.save(authorsObj);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }

//------------------------------------------------------------------------------ DISPLAY OBJECT INFO
    private static void displayObject(Object object) {
        if (!isNull(object)) {
            System.out.println(object.toString());
        } else {
        }
    }

//------------------------------------------------------------------------------ DISPLAY INFO
    private static void displayAuthor(Authors author) {
        System.out.println(author.toString());
    }

//------------------------------------------------------------------------------ DISPLAY INFO
    private static void displayAuthorsList(List<Authors> authorsList) {
        for (Authors list : authorsList) {
            if (isEntityIDExist(Authors.class, list.getIdauthors())) {
                System.out.println(list.toString());
            }
        }
    }

//------------------------------------------------------------------------------ RETURN BOOLEAN
    private static boolean isEntityIDExist(Class<Authors> entityClass, int classID) {
        openSession();
        return session.find(entityClass, classID) != null;
    }

//------------------------------------------------------------------------------ RETURN BOOLEAN
    private static boolean isValueExist(String gender) {
        Query query = session.createNamedQuery("Authors.findByGender");
        query.setParameter("gender", gender);
        return !query.getResultList().isEmpty();
    }

//------------------------------------------------------------------------------ START SESSION
    private static void createSessionFactory(Class<?> entityClass) {
        factory = Config.createSessionFactory(Config.HIBERNATE_CONFIG, entityClass);
        session = factory.getCurrentSession();
        session.beginTransaction();
    }

//------------------------------------------------------------------------------ START SESSION
    private static void openSession() {
        session = factory.getCurrentSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

    }

}
