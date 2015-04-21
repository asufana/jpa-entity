package com.github.asufana.jpa.entity;

import java.lang.reflect.*;

import javax.persistence.*;

import com.github.asufana.jpa.exceptions.*;

public abstract class BaseEntity {
    
    protected String className() {
        return this.getClass().getSimpleName();
    }
    
    public Field key() {
        for (final Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)
                    || field.isAnnotationPresent(EmbeddedId.class)) {
                field.setAccessible(true);
                return field;
            }
        }
        throw new JPAEntityException("Cannot find @Id annotation.");
    }
    
}
