package com.poly.dao;

import com.poly.entity.User;

public interface UserDAO {
    /**
     * Tìm kiếm một người dùng dựa trên tên đăng nhập (Username).
     * @param username Tên đăng nhập của người dùng cần tìm.
     * @return Đối tượng User nếu tìm thấy, ngược lại trả về null.
     */
    User findById(String username);
}
