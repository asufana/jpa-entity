package com.github.asufana.jpa.functions.test;

import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

import com.github.asufana.jpa.entity.*;

public class JPAEntityJUnitRunnerTest extends UnitTest {
    
    @Test
    public void test() throws Exception {
        //UnitTest calls JPA.instance() to enhance classes with javassist.
        //So SomeEntity.count() doesn't throw unimplement exception.
        assertThat(SomeEntity.count(), is(1L));
    }
}
