package com.poly.dao.impl;

import jakarta.persistence.EntityManager;
import com.poly.dao.ShareDAO;
import com.poly.entity.Share;
import com.poly.utils.XJPA;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShareDAOImpl implements ShareDAO {

    @Override
    public List<Share> findAll() {
        try (EntityManager em = XJPA.getEntityManager()) {
            TypedQuery<Share> q = em.createQuery("SELECT s FROM Share s", Share.class);
            return q.getResultList();
        }
    }

    @Override
    public Share findById(Long id) {
        try (EntityManager em = XJPA.getEntityManager()) {
            return em.find(Share.class, id);
        }
    }

    @Override
    public void create(Share e) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback(); // Rollback nếu có lỗi
            System.out.println("Lỗi khi create: " + ex.getMessage());
            throw ex; // Ném lại exception để tầng Servlet có thể xử lý
        } finally {
            em.close(); // Luôn luôn đóng EM dù thành công hay thất bại
        }
    }

    @Override
    public void update(Share e) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(e);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Lỗi khi update: " + ex.getMessage());
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            Share s = em.find(Share.class, id);
            em.getTransaction().begin();
            em.remove(s);
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
