package com.github.asufana.jpa.functions;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.persistence.*;

import org.junit.*;

import com.github.asufana.*;

public class EmFactoryTest extends AbstractTest {
    
    @Test
    public void testCreate() {
        final EntityManagerFactory emFactory = EmFactory.create(T.params);
        assertThat(emFactory, is(notNullValue()));
        assertThat(emFactory.isOpen(), is(true));
    }
}
