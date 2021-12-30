package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45), lastName VARCHAR(45), age INT(3), PRIMARY KEY (id))")
                    .addEntity(User.class)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory factory = Util.getSessionFactory();
        User user = new User(name, lastName, age);
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем: " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory = Util.getSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("DELETE User WHERE id = " + id)
                    .executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory factory = Util.getSessionFactory();
        List<User> user = new ArrayList<>();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            user = session.createQuery("FROM User").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
        return user;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
