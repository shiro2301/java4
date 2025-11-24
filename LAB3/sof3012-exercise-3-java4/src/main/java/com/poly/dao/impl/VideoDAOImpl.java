package com.poly.dao.impl;


import jakarta.persistence.EntityManager;
import com.poly.dao.VideoDAO;
import com.poly.entity.Video;
import com.poly.utils.XJPA;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDAOImpl implements VideoDAO {

    @Override
    public List<Video> findAll() {
        try (EntityManager em = XJPA.getEntityManager()) {
            TypedQuery<Video> q = em.createQuery("SELECT v FROM Video v", Video.class);
            return q.getResultList();
        }
    }

    @Override
    public Video findById(String id) {
        try (EntityManager em = XJPA.getEntityManager()) {
            return em.find(Video.class, id);
        }
    }

    @Override
    public void create(Video e) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit(); // THÊM DÒNG NÀY
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Lỗi khi create: " + ex.getMessage());
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Video e) {
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
    public void deleteById(String id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            Video entity = em.find(Video.class, id);
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
