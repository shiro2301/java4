package com.poly.test;

import com.poly.entity.User;
import com.poly.manager.UserManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserTest {
    public static void main(String[] args) {
        UserManager dao = new UserManager();

        // Tạo hậu tố thời gian để tránh trùng email
        String timeSuffix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        String email = "trongvt" + timeSuffix + "@fpt.edu.vn";

        // Insert user mới mỗi lần chạy
        dao.insert(new User("Văn Trọng " + timeSuffix, email, "123", false));

        // Hiển thị danh sách toàn bộ user
        System.out.println("===== Danh sách toàn bộ user =====");
        dao.findAll().forEach(u ->
                System.out.println(u.getId() + " - " + u.getFullname() + " - " + u.getEmail()));

        // Tìm user có email @fpt.edu.vn và không phải admin
        System.out.println("\n===== User @fpt.edu.vn (không phải admin) =====");
        dao.findByEmailAndRole().forEach(u ->
                System.out.println(u.getFullname() + " - " + u.getEmail()));

        // Phân trang
        System.out.println("\n===== Trang 3 (pageSize=5) =====");
        dao.findByPage(2, 5).forEach(u ->
                System.out.println(u.getId() + " - " + u.getFullname()));

        dao.close(); // đóng EntityManager sau khi chạy xong
    }
}
