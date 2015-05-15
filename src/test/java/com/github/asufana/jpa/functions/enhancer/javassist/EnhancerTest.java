package com.github.asufana.jpa.functions.enhancer.javassist;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.*;

public class EnhancerTest {
    
    @Ignore
    @Test
    public void test() {
        Enhancer.enhance("com.github.asufana.jpa.functions.enhancer.javassist.EnhanceTestEntity");
        assertThat(EnhanceTestEntity.count(), is(0L));
    }
}
