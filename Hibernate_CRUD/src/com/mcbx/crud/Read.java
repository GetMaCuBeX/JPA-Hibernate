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
import hibernate.Config;
import com.mcbx.entity.Authors;
import com.mcbx.entity.Members;
import java.util.List;
import static java.util.Objects.isNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Read {

    static SessionFactory factory;
    static Session session;

    public static void main(String[] args) {
        try {
            createSessionFactory(Authors.class);
//------------------------------------------------------------------------------ CREATE NEW OBJECT
//            Authors author = new Authors("Jojo", "Scooby", "09587931547", "Male");
//            addAuthor(author);
//------------------------------------------------------------------------------ GET OBJECT VIA ID
//            openSession();
//            Authors getObj = getAuthor(4111);
//            displayAuthor(getObj);
//------------------------------------------------------------------------------ GET OBJECT VIA ID
            openSession();
            Authors getObj = (Authors) getObject(Authors.class, 233333);
            displayObject(getObj);
//------------------------------------------------------------------------------
//            openSession();
//            displayAuthor(getData_Authors(3));
//            openSession();
//            displayAuthorsList(getData_Authors());
//            openSession();
//            displayAuthorsList(getData_Authors("Male"));
//            createSessionFactory(Members.class);
//            displayMembersList(getData_Members());
        } finally {
            factory.close();
        }
    }

//------------------------------------------------------------------------------ RETURN OBJECT, NULL IF NOT FOUND
    public static Object getObject(Class<?> pojoClass, int id) {
        try {
            Object obj = session.get(pojoClass, id);
            session.getTransaction().commit();
            return obj;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return null;
    }

//------------------------------------------------------------------------------ RETURN OBJECT, NULL IF NOT FOUND
    public static Authors getAuthor(int id) {
        try {
            Authors obj = session.get(Authors.class, id);
            session.getTransaction().commit();
            return obj;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        }
        return null;
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

//------------------------------------------------------------------------------ RETURN ARUTHOR
    private static Authors getData_Authors(Authors authorsObj) {
        if (!isNull(authorsObj)) {
            if (isEntityIDExist(Authors.class, authorsObj.getIdauthors())) {
                Authors get = session.get(Authors.class, authorsObj.getIdauthors());
                session.getTransaction().commit();
                return get;
            }
        }
        return null;
    }
//------------------------------------------------------------------------------ RETURN ARUTHOR

    private static Authors getData_Authors(int authorsID) {
        if (!isNull(authorsID)) {
            if (isEntityIDExist(Authors.class, authorsID)) {
                Authors get = session.get(Authors.class, authorsID);
                session.getTransaction().commit();
                return get;
            }
        }
        return null;
    }

//------------------------------------------------------------------------------ RETURN ALL LIST OF AUTHOR
    public static List<Authors> getData_Authors() {
        Query query = session.createNamedQuery("Authors.findAll");
        List<Authors> authorsList = query.getResultList();
        return authorsList;
    }

    public static List<Authors> getData_Authors(String gender) {
        Query query = session.createNamedQuery("Authors.findByGender");
        query.setParameter("gender", gender);
        List<Authors> authorsList = query.getResultList();
        return authorsList;
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
        } else { 
        }
    }

//------------------------------------------------------------------------------ DISPLAY INFO
    private static void displayAuthorsList(List<Authors> authorsList) {
        if (!isNull(authorsList)) {
            for (Authors list : authorsList) {
                if (isEntityIDExist(Authors.class, list.getIdauthors())) {
                    System.out.println(list.toString());
                }
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

//------------------------------------------------------------------------------ GET MEMBERS
    public static List<Members> getData_Members() {
        Query query = session.createNamedQuery("Members.findAll");
        List<Members> members = query.getResultList();
        return members;
    }

//------------------------------------------------------------------------------ DISPLAY LIST OF MEMBERS INFO
    private static void displayMembersList(List<Members> members) {
        members.forEach((list) -> {
            System.out.println(list.toString());
        });
    }

//------------------------------------------------------------------------------ DISPLAY MEMBER INFO
    private static void displayMember(Members member) {
        if (!isNull(member)) {
            System.out.println(member.toString());
        }
    }

}
