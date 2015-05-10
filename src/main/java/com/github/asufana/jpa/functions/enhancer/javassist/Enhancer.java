package com.github.asufana.jpa.functions.enhancer.javassist;

import javassist.*;

public class Enhancer {
    
    public static void enhance(final String className) {
        try {
            final ClassPool classPool = ClassPool.getDefault();
            final CtClass cc = classPool.get(className);
            cc.addMethod(CtMethod.make("public static long count() { return 1L; }",
                                       cc));
            cc.toClass();
        }
        catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
