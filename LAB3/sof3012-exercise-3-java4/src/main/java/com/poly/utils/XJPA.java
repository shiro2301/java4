package com.poly.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("PolyOE_Lab3");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
