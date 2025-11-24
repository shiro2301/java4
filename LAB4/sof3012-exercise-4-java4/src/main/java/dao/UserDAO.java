package dao;

import entity.User;
import java.util.List;

public interface UserDAO {
    // CRUD Cơ bản
    void insert(User user);
    User update(User user);
    void delete(String id);
    User findById(String id);
    List<User> findAll();
    
    // Phương thức theo yêu cầu Lab 4 (Bài 2)
    User findByIdOrEmail(String idOrEmail);
}