package com.github.asufana.jpa.entity;

import javax.persistence.*;

public class JPQL {
    
    public static String createQuery(final String entityName, final String query) {
        if (query == null || query.trim().isEmpty()) {
            return String.format("from %s", entityName);
        }
        return String.format("from %s where %s", entityName, query);
    }
    
    public static Query setParams(final Query q, final Object... params) {
        if (params == null) {
            return q;
        }
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        return q;
    }
}
