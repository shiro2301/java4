package com.poly.daoImpl;

import com.poly.dao.LogDAO;
import com.poly.entity.Log;
import com.poly.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LogDAOImpl implements LogDAO {

    @Override
    public void create(Log log) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.persist(log); 
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            System.err.println("Lỗi khi ghi Log vào CSDL bằng JPA: " + e.getMessage());
        } finally {
            em.close();
        }
    }


}
