package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory factory;

    // Khởi tạo EntityManagerFactory chỉ một lần
    public static EntityManager getEntityManager() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("PolyOEE"); // Tên persistence unit trong persistence.xml
        }
        return factory.createEntityManager();
    }
    
    // Đóng EntityManagerFactory khi ứng dụng kết thúc
    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}