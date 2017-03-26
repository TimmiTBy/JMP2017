package com.epam.jmp.webservice.dao;

import com.epam.jmp.webservice.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Ales on 19.03.2017.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {
    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
        return listUser;
    }

    @Override
    @Transactional
    public void createOrUpdateUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @Transactional
    public User findUserById(int id) {
        String hql = "from User where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<User> listUser = (List<User>) query.list();
        if (listUser != null && !listUser.isEmpty()) {
            return listUser.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
