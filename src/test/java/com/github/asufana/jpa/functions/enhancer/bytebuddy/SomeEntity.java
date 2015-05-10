package com.github.asufana.jpa.functions.enhancer.bytebuddy;

public class SomeEntity extends SomeAbstractEntity {
    
    public static long count() {
        throw new RuntimeException("unimplement.");
    }
    
}
