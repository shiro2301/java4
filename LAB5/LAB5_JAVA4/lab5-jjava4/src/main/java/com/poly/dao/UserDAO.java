package com.poly.dao;

import com.poly.entity.User;

public interface UserDAO {
	User findById(String username);
}
