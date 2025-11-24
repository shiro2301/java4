// src/main/java/dao/VideoDAO.java
package dao;

import java.util.List;
import entity.Video;

public interface VideoDAO {
    // ... các phương thức CRUD cơ bản khác
    
    /**
     * Tìm video có title chứa từ khóa (Bài 3)
     * @param keyword Chuỗi từ khóa (ví dụ: "%java%")
     * @return Danh sách Video phù hợp
     */
    List<Video> findByTitleKeyword(String keyword);

    /**
     * Truy vấn 10 video được yêu thích nhiều nhất (Bài 1 (3))
     */
    List<Video> findTop10MostLiked();

	List<Video> findSharedInYear(int year);
}