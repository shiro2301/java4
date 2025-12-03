package com.poly.dao;

import com.poly.entity.User;
import com.poly.util.JPAContext;
import jakarta.persistence.EntityManager;

public class UserDaoImpl implements UserDAO {
    @Override
    public User findById(String username) {
        EntityManager em = JPAContext.getEntityManager();
        try {
            return em.find(User.class, username);
        } finally {
            em.close();
        }
    }
}
