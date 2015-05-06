package com.github.asufana.jpa.functions.enhancer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class EnhancerTest {
    
    @Test
    public void test() {
        Enhancer.enhance(SomeClass.class);
        final SomeClass someClass = new SomeClass();
        assertThat(someClass.toString(), is("Hello world!"));
    }
    
    public static class SomeClass {
        @Override
        public String toString() {
            return "hoge";
        }
    }
    
}
