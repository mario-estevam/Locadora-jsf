package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutil {

    private static EntityManagerFactory conectar = Persistence.createEntityManagerFactory("ProjetoLOC");

    public static EntityManager getEntityManager() {
        return conectar.createEntityManager();
    }
}