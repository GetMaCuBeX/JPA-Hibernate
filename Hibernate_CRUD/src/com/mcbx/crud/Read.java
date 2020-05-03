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
//            Authors author = new Authors("firstname", "lastname", "contactnumber", "Male");
//            session.save(author);
//            session.getTransaction().commit();
//            openSession();
//            displayAuthor(getData_Authors(author));
//            openSession();
//            displayAuthor(getData_Authors(3));

//            openSession();
//            displayAuthorsList(getData_Authors());
//            openSession();
//            displayAuthorsList(getData_Authors("Male"));

            createSessionFactory(Members.class);
            displayMembersList(getData_Members());

        } finally {
            factory.close();
        }
    }

//------------------------------------------------------------------------------ RETURN ARUTHOR
    private static Authors getData_Authors(Authors authorsObj) {
        if (!isNull(authorsObj)) {
            if (isEntityIDExist(Authors.class,
                    authorsObj.getIdauthors())) {
                Authors get = session.get(Authors.class, authorsObj.getIdauthors());

                session.getTransaction()
                        .commit();
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
                if (isEntityIDExist(Authors.class,
                        list.getIdauthors())) {
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
