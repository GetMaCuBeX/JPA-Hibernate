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
            testAddDelete();
        } finally {
            factory.close();
        }
    }

//------------------------------------------------------------------------------ 
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

//------------------------------------------------------------------------------ 
    public static void deleteData_Authors(List<Authors> authorsObj) {
        if (!isNull(authorsObj)) {
            for (Authors list : authorsObj) {
                int authorsID = list.getIdauthors();
//                if (isEntityIDExist(Authors.class, authorsID)) { 
                session.createQuery("delete from Authors where idauthors=" + authorsID).executeUpdate();
//                }
            }
            session.getTransaction().commit();
        }

    }

//------------------------------------------------------------------------------ DISPLAY INFO
    private static void displayAuthor(Authors authorsObj) {
        if (!isNull(authorsObj)) {
            System.out.println(authorsObj.toString());
        }
    }

//------------------------------------------------------------------------------ DISPLAY INFO
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
