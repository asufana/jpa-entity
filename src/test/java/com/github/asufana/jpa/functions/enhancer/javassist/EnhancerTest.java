package com.github.asufana.jpa.functions.enhancer.javassist;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class EnhancerTest {
    
    @Test
    public void test() {
        Enhancer.enhance("com.github.asufana.jpa.functions.enhancer.javassist.SomeEntity");
        assertThat(SomeEntity.count(), is(1L));
    }
}
