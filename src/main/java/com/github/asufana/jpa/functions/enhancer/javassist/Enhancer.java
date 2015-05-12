package com.github.asufana.jpa.functions.enhancer.javassist;

import javassist.*;

public class Enhancer {
    
    public static void enhance(final String className) {
        try {
            final ClassPool classPool = ClassPool.getDefault();
            final CtClass cc = classPool.get(className);
            
            //count
            cc.addMethod(CtMethod.make("public static long count() { return Long.parseLong(com.github.asufana.jpa.JPA.instance().em().createQuery(\"select count(*) from "
                                               + className
                                               + " e\").getSingleResult().toString()); }",
                                       cc));
            
            cc.toClass();
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
