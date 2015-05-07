package com.github.asufana.jpa.functions.enhancer;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import com.github.asufana.jpa.entity.*;

public class EnhancerTest {
    
    @Test
    public void testEnhance() {
        Enhancer.enhance(SomeEntity.class);
        assertThat(SomeEntity.count2(), is(0L));
    }
}
