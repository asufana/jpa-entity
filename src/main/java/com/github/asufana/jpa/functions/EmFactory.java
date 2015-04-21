package com.github.asufana.jpa.functions;

import java.beans.*;
import java.util.*;

import javax.persistence.*;

import org.hibernate.ejb.*;

import com.github.asufana.jpa.exceptions.*;
import com.mchange.v2.c3p0.*;

@SuppressWarnings("deprecation")
public class EmFactory {
    
    public static EntityManagerFactory create(final Map<String, String> params) {
        
        //https://access.redhat.com/documentation/ja-JP/Red_Hat_JBoss_Web_Server/1.0/html-single/Hibernate_Entity_Manager_Reference_Guide/index.html#setup-configuration-bootstrapping
        final Ejb3Configuration cfg = createConfigObject(params);
        cfg.setDataSource(createDataSource(params));
        
        for (final Class<?> klass : ClassScanner.findAllEntityClasses()) {
            cfg.addAnnotatedClass(klass);
        }
        return cfg.buildEntityManagerFactory();
    }
    
    private static Ejb3Configuration createConfigObject(final Map<String, String> params) {
        final Ejb3Configuration cfg = new Ejb3Configuration();
        cfg.setProperty("hibernate.dialect", params.get("hibernate.dialect"));
        cfg.setProperty("hibernate.hbm2ddl.auto",
                        params.get("hibernate.hbm2ddl.auto"));
        cfg.setProperty("javax.persistence.transaction",
                        params.get("javax.persistence.transaction"));
        return cfg;
    }
    
    private static ComboPooledDataSource createDataSource(final Map<String, String> params) {
        final ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(params.get("db.driver"));
            ds.setJdbcUrl(params.get("db.url"));
            ds.setUser(params.get("db.user"));
            ds.setPassword(params.get("db.pass"));
        }
        catch (final PropertyVetoException e) {
            throw new JPAEntityException(e);
        }
        return ds;
    }
}
