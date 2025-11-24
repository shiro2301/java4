package com.poly.dao.impl;

import jakarta.persistence.EntityManager;
import com.poly.dao.FavoriteDAO;
import com.poly.entity.Favorite;
import com.poly.utils.XJPA;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FavoriteDAOImpl implements FavoriteDAO {

    @Override
    public List<Favorite> findAll() {
        try (EntityManager em = XJPA.getEntityManager()) {
            TypedQuery<Favorite> q = em.createQuery("SELECT f FROM Favorite f", Favorite.class);
            return q.getResultList();
        }
    }

    @Override
    public Favorite findById(Long id) {
        try (EntityManager em = XJPA.getEntityManager()) {
            return em.find(Favorite.class, id);
        }
    }

    @Override
    public void create(Favorite entity) {
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
    public void update(Favorite entity) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
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
    public void deleteById(Long id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            Favorite f = em.find(Favorite.class, id);
            em.getTransaction().begin();
            em.remove(f);
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
