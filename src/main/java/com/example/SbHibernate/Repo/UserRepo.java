package com.example.SbHibernate.Repo;

import com.example.SbHibernate.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    @Autowired
    SessionFactory sessionFactory;

    public void registerUser(User user) {
        Session currentSession = sessionFactory.openSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(user);
        transaction.commit();
        currentSession.close();
    }


    public boolean existsByUsername(String userName) {
        Session currentSession = sessionFactory.openSession();
        Long count = currentSession.createQuery("SELECT COUNT(u) FROM User u WHERE u.userName = :username", Long.class)
                .setParameter("username", userName)
                .uniqueResult();
        currentSession.close();

        return count > 0;


    }
    public boolean existsByEmail(String email) {
        Session currentSession = sessionFactory.openSession();
        Long count = currentSession.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        currentSession.close();

        return count > 0;
    }

    public String getEncodedPassword(String userNameOrEmail) {

        Session currentSession = sessionFactory.openSession();

        String password = currentSession.createQuery(
                        "SELECT u.password FROM User u WHERE u.userName = :userNameOrEmail OR u.email = :userNameOrEmail", String.class)
                .setParameter("userNameOrEmail", userNameOrEmail)
                .getSingleResult();

        currentSession.close();

        return password;
    }
}
