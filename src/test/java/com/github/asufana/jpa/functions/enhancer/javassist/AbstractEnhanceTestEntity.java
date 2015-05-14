package com.github.asufana.jpa.functions.enhancer.javassist;


public abstract class AbstractEnhanceTestEntity {
    
    public static long count() {
        throw new RuntimeException("unimplement.");
    }
    
}
