package com.github.asufana.jpa.functions.enhancer.bytebuddy;

public abstract class SomeAbstractEntity {
    
    public static long count() {
        throw new RuntimeException("unimplement.");
    }
    
}
