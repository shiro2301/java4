package dao;

import dao.VideoDAO;
import entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utils.JpaUtil;


import java.util.Date;
import java.util.List;

public class VideoDAOImpl implements VideoDAO {

    @Override
    public List<Video> findByTitleKeyword(String keyword) {
        EntityManager em = JpaUtil.getEntityManager();
        // JPQL cho Bài 1 (2) và Bài 3
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :keyword";

        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        // Lưu ý: keyword phải được bọc % ở tầng service/servlet trước khi gọi
        query.setParameter("keyword", keyword); 
        
        List<Video> list = query.getResultList();
        em.close();
        return list;
    }
    
    @Override
    public List<Video> findTop10MostLiked() {
        EntityManager em = JpaUtil.getEntityManager();
        // JPQL cho Bài 1 (3): Video có số lượt thích nhiều nhất
        // Giả sử có collection 'favorites' trong Entity Video
        String jpql = "SELECT v FROM Video v JOIN v.favorites f GROUP BY v ORDER BY COUNT(f) DESC";
        
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setMaxResults(10); // Giới hạn 10 kết quả
        
        List<Video> list = query.getResultList();
        em.close();
        return list;
    }
    
 // src/main/java/dao/impl/VideoDAOImpl.java (Đoạn code đã sửa)
 // ...
 @Override
 public List<Video> findSharedInYear(int year) {
     EntityManager em = JpaUtil.getEntityManager();
     // JPQL đã hoàn chỉnh: Thêm "= :year ORDER BY s.shareDate DESC"
     String jpql = "SELECT s.video FROM Share s WHERE YEAR(s.shareDate) = :year ORDER BY s.shareDate DESC"; 
     
     TypedQuery<Video> query = em.createQuery(jpql, Video.class);
     query.setParameter("year", year);
     
     List<Video> list = query.getResultList();
     em.close();
     return list;
 }
 // ...
    // ... CÁC PHƯƠNG THỨC CRUD KHÁC
}