package com.github.asufana.jpa.entity;

import javax.persistence.*;

@Entity
public class SomeEntity extends JPAEntity<SomeEntity> {
    @Id
    @Column
    private final String name;
    
    public SomeEntity(final String name) {
        this.name = name;
    }
    
//    public static Long count2() {
//        throw new UnsupportedOperationException("Please annotate your JPA model with @javax.persistence.Entity annotation.");
//    }
}
