package com.poly.manager;

import com.poly.entity.User;
import jakarta.persistence.*;
import java.util.List;

public class UserManager {

    // Khai báo EntityManagerFactory & EntityManager toàn cục
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolyOE");
    private final EntityManager em = emf.createEntityManager();

    // Thêm mới 1 User
    public void insert(User user) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // Lấy tất cả User
    public List<User> findAll() {
        return em.createQuery("SELECT o FROM User o", User.class).getResultList();
    }

    // Tìm user có email @fpt.edu.vn và không phải admin
    public List<User> findByEmailAndRole() {
        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("search", "%@fpt.edu.vn");
        query.setParameter("role", false);
        return query.getResultList();
    }

    // Phân trang (trang 3, kích thước 5 user)
    public List<User> findByPage(int pageNumber, int pageSize) {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    // Đóng kết nối khi xong
    public void close() {
        if (em.isOpen()) em.close();
        if (emf.isOpen()) emf.close();
    }
}
