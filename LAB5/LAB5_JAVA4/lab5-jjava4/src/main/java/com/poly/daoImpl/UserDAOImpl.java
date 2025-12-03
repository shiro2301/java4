package com.poly.daoImpl;

import com.poly.dao.UserDAO;
import com.poly.entity.User;
import com.poly.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findById(String username) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            User user = em.find(User.class, username);
            return user; // Trả về null nếu không tìm thấy
        } finally {
            em.close();
        }
    }
}
