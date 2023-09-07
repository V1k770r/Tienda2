package com.latam.alura.tienda2.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

    private static EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("tienda2");

    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }

}
