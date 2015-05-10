package com.github.asufana.jpa.functions.enhancer.bytebuddy;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.*;

public class EnhancerTest {
    
    @Test
    public void testEnhance() {
        Enhancer.enhance(SomeEntity.class);
        assertThat(SomeEntity.count(), is(0L));
    }
}
