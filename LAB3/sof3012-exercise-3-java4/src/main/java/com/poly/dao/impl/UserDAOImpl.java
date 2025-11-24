package com.poly.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import com.poly.dao.UserDAO;
import com.poly.entity.User;
import com.poly.utils.XJPA;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        try (EntityManager em = XJPA.getEntityManager()) {
            TypedQuery<User> q = em.createQuery("SELECT u FROM User u", User.class);
            return q.getResultList();
        }
    }

    @Override
    public User findById(String id) {
        try (EntityManager em = XJPA.getEntityManager()) {
            return em.find(User.class, id);
        }
    }

    @Override
    public void create(User entity) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback(); // Rollback nếu có lỗi
            System.out.println("Lỗi khi create: " + e.getMessage());
            throw e; // Ném lại exception để tầng Servlet có thể xử lý
        } finally {
            em.close(); // Luôn luôn đóng EM dù thành công hay thất bại
        }
    }

    @Override
    public void update(User entity) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity); // SỬA TỪ persist -> merge
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Lỗi khi update: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(String id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            User entity = em.find(User.class, id);
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Lỗi khi delete: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }
}
