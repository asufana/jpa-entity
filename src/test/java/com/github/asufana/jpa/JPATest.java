package com.github.asufana.jpa;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.persistence.*;

import org.junit.*;

public class JPATest {
    
    @Test
    public void testConstructor() {
        final JPA jpa = JPA.instance();
        assertThat(jpa, is(notNullValue()));
        
        final EntityManager em = jpa.em();
        assertThat(em, is(notNullValue()));
        assertThat(jpa.isOpen(), is(true));
        
        jpa.close();
        assertThat(jpa.isOpen(), is(false));
    }
    
}
