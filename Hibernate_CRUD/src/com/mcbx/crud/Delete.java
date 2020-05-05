/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcbx.crud;

/**
 *
 * @author MaCuBeX
 */
import hibernate.Config;
import com.mcbx.entity.Authors;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Delete {

    static SessionFactory factory;
    static Session session;

    public static void main(String[] args) {
        try {
            createSessionFactory(Authors.class);
//------------------------------------------------------------------------------ CREATE NEW OBJECT
            Authors author = new Authors("Jojo", "Scooby", "09587931547", "Male");
            addAuthor(author);
//------------------------------------------------------------------------------ DELETE OBJECT VIA INSTANCE
            openSession();
            deleteAuthor(author);
//------------------------------------------------------------------------------ TEST CREATE, DELETE NEW OBJECT 
//            testAddDelete();
        } finally {
            factory.close();
        }
    }

//------------------------------------------------------------------------------ DELETE OBJECT VIA INSTANCE
    public static void deleteAuthor(Authors authorsObj) {
        try {
            session.delete(authorsObj);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
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

//------------------------------------------------------------------------------ DELETE OBJECT VIA ID
    public static void deleteData_Authors(Authors authorsObj) {
        if (!isNull(authorsObj)) {
            int authorsID = authorsObj.getIdauthors();
            if (isEntityIDExist(Authors.class, authorsID)) {
                session.createQuery("delete from Authors where idauthors=" + authorsID).executeUpdate();
                System.out.println("AUTHORS ID: " + authorsID + " IS DELETED");
                session.getTransaction().commit();
            }
        }
    }

//------------------------------------------------------------------------------ DELETE OBJECTS VIA LIST OF INSTANCE
    public static void deleteData_Authors(List<Authors> authorsObj) {
        if (!isNull(authorsObj)) {
            for (Authors list : authorsObj) {
                if (isEntityIDExist(Authors.class, list.getIdauthors())) {
                    session.createQuery("delete from Authors where idauthors=" + list.getIdauthors()).executeUpdate();
                }
            }
            session.getTransaction().commit();
        }

    }

//------------------------------------------------------------------------------ DISPLAY OBJECT INFO VIA OBJECT
    private static void displayObject(Object object) {
        if (!isNull(object)) {
            System.out.println(object.toString());
        } else {
        }
    }

//------------------------------------------------------------------------------ DISPLAY INFO VIA INSTANCE
    private static void displayAuthor(Authors authorsObj) {
        if (!isNull(authorsObj)) {
            System.out.println(authorsObj.toString());
        }
    }

//------------------------------------------------------------------------------ DISPLAY OBJECTS INFO VIA LIST OF OF INSTANCE
    private static void displayAuthorsList(List<Authors> authorsList) {
        if (!isNull(authorsList)) {
            for (Authors list : authorsList) {
//                if (isEntityIDExist(Authors.class, list.getIdauthors())) {
                System.out.println(list.toString());
//                }
            }
        }
    }

//------------------------------------------------------------------------------ RETURN BOOLEAN
    private static boolean isEntityIDExist(Class<Authors> entityClass, int classID) {
        openSession();
        return session.find(entityClass, classID) != null;
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

//------------------------------------------------------------------------------ TESTING
    private static void testAddDelete() {
        System.out.println("CREATE NEW AUTHORS DATA-----------------------------");
        int count = 1;
        Authors author1 = new Authors(count++ + "", "lastname", "contactnumber", "Male");
        session.save(author1);
        session.getTransaction().commit();

        openSession();
        Authors author2 = new Authors(count++ + "", "lastname", "contactnumber", "Male");
        session.save(author2);
        session.getTransaction().commit();

        openSession();
        Authors author4 = new Authors(count++ + "", "lastname", "contactnumber", "Male");
        session.save(author4);
        session.getTransaction().commit();

        List<Authors> lisa = new ArrayList<>();
        lisa.add(author1);
        lisa.add(author2);
        lisa.add(author4);

        System.out.println("DISPLAY NEW CREATED DATA----------------------------");
        openSession();
        displayAuthorsList(lisa);

        System.out.println("DELETE NEW CREATED DATA-----------------------------");
        openSession();
        deleteData_Authors(lisa);
    }

}
