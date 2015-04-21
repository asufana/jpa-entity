package com.github.asufana.jpa;

import java.util.*;

import javax.persistence.*;

import com.github.asufana.jpa.functions.*;

public class JPA {
    
    private static EntityManagerFactory emFactory;
    private static EntityManager em;
    private static JPA INSTANCE = instance();
    
    public static JPA instance() {
        if (INSTANCE == null) {
            INSTANCE = new JPA(ClassScanner.findJPAEntityConfig());
        }
        return INSTANCE;
    }
    
    private JPA(final Map<String, String> params) {
        emFactory = EmFactory.create(params);
    }
    
    public EntityManager em() {
        if (em == null) {
            em = emFactory.createEntityManager();
        }
        return em;
    }
    
    public static void beginTran() {
        instance().em().getTransaction().begin();
    }
    
    public static void commitTran() {
        instance().em().getTransaction().commit();
    }
    
    public boolean isOpen() {
        return emFactory.isOpen() && em != null && em.isOpen();
    }
    
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emFactory.isOpen()) {
            emFactory.close();
        }
    }
}
