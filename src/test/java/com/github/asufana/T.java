package com.github.asufana;

import java.util.*;

import org.hibernate.dialect.*;

import com.github.asufana.jpa.annotations.*;

public class T {
    
    @SuppressWarnings("serial")
    @JPAEntityConfig
    public final static Map<String, String> params = new HashMap<String, String>() {
        {
            put("db.driver", "org.h2.Driver");
            //put("db.driver", "org.postgresql.Driver");
            put("db.url", "jdbc:h2:mem:test");
            //put("db.url", "jdbc:postgresql://127.0.0.1:5432/test");
            put("db.user", "sa");
            //put("db.user", "postgres");
            put("db.pass", "");
            
            put("hibernate.hbm2ddl.auto", "create");
            put("javax.persistence.transaction", "RESOURCE_LOCAL");
            put("hibernate.dialect", H2Dialect.class.getName());
            //put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        }
    };
    
}
