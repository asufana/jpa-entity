package com.github.asufana.jpa;

import java.util.*;

import javax.persistence.*;

import com.github.asufana.jpa.functions.*;
import com.github.asufana.jpa.functions.enhancer.javassist.*;

public class JPA {
    
    private static EntityManagerFactory emFactory;
    private static EntityManager em;
    private static JPA INSTANCE = instance();
    private static Map<String, String> configMap;
    
    public static JPA instance() {
        if (INSTANCE == null) {
            configMap = ClassScanner.findJPAEntityConnectionConfig();
            INSTANCE = new JPA(configMap);
            enhanceClass();
        }
        return INSTANCE;
    }
    
    private static void enhanceClass() {
        final List<String> enhanceClassNames = ClassScanner.findJPAEntityEnhanceConfig();
        enhanceClassNames.forEach(Enhancer::enhance);
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
