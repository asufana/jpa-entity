package com.github.asufana.jpa.functions.enhancer.javassist;

import javassist.*;

public class Enhancer {
    
    public static void enhance(final String className) {
        try {
            final ClassPool classPool = ClassPool.getDefault();
            final CtClass cc = classPool.get(className);
            if (cc.isFrozen()) {
                return;
            }
            
            //count
            cc.addMethod(CtMethod.make("public static long count() { return Long.parseLong(com.github.asufana.jpa.JPA.instance().em().createQuery(\"select count(*) from "
                                               + className
                                               + " e\").getSingleResult().toString()); }",
                                       cc));
            
            //find all
            cc.addMethod(CtMethod.make("public static java.util.List findAll() { return com.github.asufana.jpa.JPA.instance().em().createQuery(\"select e from "
                                               + className
                                               + " e\").getResultList(); }",
                                       cc));
            
            //find
            cc.addMethod(CtMethod.make("public static java.util.List find(String query, Object[] params) { "
                                               + "javax.persistence.Query q = com.github.asufana.jpa.JPA.instance().em().createQuery(com.github.asufana.jpa.entity.JPQL.createQuery(\""
                                               + className
                                               + "\", query));"
                                               + "q = com.github.asufana.jpa.entity.JPQL.setParams(q, params);"
                                               + "return q.getResultList(); }",
                                       cc));
            
            cc.toClass();
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
