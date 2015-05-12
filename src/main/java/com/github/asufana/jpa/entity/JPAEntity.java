package com.github.asufana.jpa.entity;

import java.util.*;

import javax.persistence.*;

import com.github.asufana.jpa.*;
import com.github.asufana.jpa.exceptions.*;

public abstract class JPAEntity<T> extends BaseEntity {
    
    protected static EntityManager em() {
        return JPA.instance().em();
    }
    
//    private void isEntity() {
//        if (this.getClass().getDeclaredAnnotation(Entity.class) == null) {
//            throw new JPAEntityException("This is not @Entity class: "
//                    + className());
//        }
//    }
    
    public boolean isPersistent() {
        return em().contains(this);
    }
    
    public static long count() {
        throw new JPAEntityException("unimplement.");
    }
    
    @SuppressWarnings("unchecked")
    public T save() {
        if (em().contains(this) == false) {
            em().persist(this);
        }
        em().flush();
        return (T) this;
    }
    
    @SuppressWarnings("unchecked")
    public T delete() {
        em().remove(this);
        em().flush();
        return (T) this;
    }
    
    //TODO hana to Static method
    @SuppressWarnings("unchecked")
    public List<T> find(final String query, final Object... params) {
        Query q = em().createQuery(JPQL.createQuery(className(), query));
        q = JPQL.setParams(q, params);
        return q.getResultList();
    }
    
    //TODO hana to Static method
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return em().createQuery(String.format("select e from %s e",
                                              this.getClass().getSimpleName()))
                   .getResultList();
    }
    
}
