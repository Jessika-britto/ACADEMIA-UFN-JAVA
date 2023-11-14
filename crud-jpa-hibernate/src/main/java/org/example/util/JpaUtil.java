package org.example.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
