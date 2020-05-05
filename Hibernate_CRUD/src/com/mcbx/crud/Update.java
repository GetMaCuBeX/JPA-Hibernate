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
import static com.mcbx.crud.Create.session;
import static com.mcbx.crud.Delete.session;
import hibernate.Config;
import com.mcbx.entity.Authors;
import java.util.List;
import static java.util.Objects.isNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Update {

    static SessionFactory factory;
    static Session session;
    static int randomNumber = getRandomNumber(100, 1);

    public static void main(String[] args) {
        try {
            createSessionFactory(Authors.class);
//------------------------------------------------------------------------------ CREATE NEW OBJECT
            Authors author = new Authors("Jojo", "Scooby", "09587931547", "Male");
            addAuthor(author);
//------------------------------------------------------------------------------ UPDATE OBJECT
            openSession();
            updateAuthor(author, randomNumber + ""); 
//------------------------------------------------------------------------------ UPDATE OBJECT
//            updateData_Authors(randomNumber);
//            updateData_Authors(Authors.class, 1);
//------------------------------------------------------------------------------ UPDATE LIST OF OBJECTS
//            openSession();
//            Query query = session.createNamedQuery("Authors.findAll");
//            List<Authors> authorsList = query.getResultList();
//            updateData_Authors(Authors.class, authorsList);
//            displayAuthorsList(authorsList);
        } finally {
            factory.close();
        }
    }

//------------------------------------------------------------------------------ CREATE OBJECT
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

//------------------------------------------------------------------------------ UPDATE OBJECT
    public static void updateAuthor(Authors authorsObj, String firstname) {
        try {
            authorsObj.setFirstname(firstname);
            session.update(authorsObj);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
    }

//------------------------------------------------------------------------------ UPDATE ALL AUTHORS
    private static void updateData_Authors(int newValue) {
        session.createQuery("update Authors set integerval=" + newValue).executeUpdate();
        session.getTransaction().commit();
    }

//------------------------------------------------------------------------------ UPDATE AUTHOR @ID
    private static void updateData_Authors(Class<Authors> authorEntity, int id) {
        if (!isNull(authorEntity)) {
            if (isEntityIDExist(authorEntity, id)) {
                Authors author = session.get(authorEntity, id);
                author.setFirstname(randomNumber + "");
                session.getTransaction().commit();
            }

        }
    }

//------------------------------------------------------------------------------ UPDATE LIST OF AUTHORS @LIST
    private static void updateData_Authors(Class<Authors> authorEntity, List<Authors> authorsList) {
        if (!isNull(authorsList)) {
            for (Authors list : authorsList) {
                if (isEntityIDExist(authorEntity, list.getIdauthors())) {
                    Authors author = session.find(authorEntity, list.getIdauthors());
                    author.setIntegerval(randomNumber);
                }
            }
            session.getTransaction().commit();
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
        if (!isNull(author)) {
            System.out.println(author.toString());
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

//------------------------------------------------------------------------------ GET RANDOM NUMBER   
    private static int getRandomNumber(int min, int max) {
        int range = max - min + 1;
        int ran = (int) (Math.random() * range) + min;
        return ran;
    }
}
