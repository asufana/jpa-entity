package com.github.asufana.jpa.functions;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class ClassScannerTest {
    
    @Test
    public void testFindAllEntityClasses() {
        final Set<Class<?>> classes = ClassScanner.findAllEntityClasses();
        assertThat(classes.size(), is(not(0)));
        
        classes.stream().forEach(klass -> System.out.println(klass.getName()));
    }
    
    @Test
    public void testFindJPAEntityConfig() throws Exception {
        final Map<String, String> params = ClassScanner.findJPAEntityConfig();
        assertThat(params, is(notNullValue()));
        assertThat(params.size(), is(not(0)));
    }
}
