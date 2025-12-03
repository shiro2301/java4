package com.poly.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	private static final String PERSISTENCE_UNIT_NAME = "LAB5_JAVA4"; // Đảm bảo khớp với persistence.xml
    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            System.err.println("Lỗi khởi tạo EntityManagerFactory.");
            e.printStackTrace();
            throw new RuntimeException("JPA initialization failed.");
        }
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    
    public static void closeFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
