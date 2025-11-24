package poly.dao;

import java.util.List;

import poly.entity.User;

public interface UserDAO {
    List<User> findAll();

    User findById(String id);

    void create(User entity);

    void update(User entity);

    void deleteById(String id);
}
