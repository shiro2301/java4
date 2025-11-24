package dao;

import dao.UserDAO;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;


import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findByIdOrEmail(String idOrEmail) {
        EntityManager em = JpaUtil.getEntityManager();
        // JPQL cho Bài 1 (1): Tìm User theo id HOẶC email
        String jpql = "SELECT u FROM User u WHERE u.id = :idOrEmail OR u.email = :idOrEmail"; 

        try {
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("idOrEmail", idOrEmail);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } finally {
            em.close();
        }
    }
    
    // CÁC PHƯƠNG THỨC CRUD KHÁC:
    @Override
    public void insert(User user) { /* ... Triển khai Persist ... */ }
    @Override
    public User update(User user) { /* ... Triển khai Merge ... */ return user; }
    @Override
    public void delete(String id) { /* ... Triển khai Remove ... */ }
    @Override
    public User findById(String id) { /* ... Triển khai Find ... */ return null; }
    @Override
    public List<User> findAll() { /* ... Triển khai SELECT ALL ... */ return null; }
}