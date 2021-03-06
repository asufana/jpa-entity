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
}
