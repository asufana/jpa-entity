package com.github.asufana.jpa.entity;

import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

import com.github.asufana.jpa.*;
import com.github.asufana.jpa.functions.test.*;

public class SomeEntityTest extends UnitTest {
    
    @Before
    public void before() {
        JPA.beginTran();
    }
    
    @After
    public void after() {
        JPA.commitTran();
    }
    
    @Test
    public void testInsert() {
        final SomeEntity entity = new SomeEntity("hana");
        assertThat(SomeEntity.count(), is(0L));
        assertThat(entity.isPersistent(), is(false));
        
        //Save
        entity.save();
        assertThat(SomeEntity.count(), is(1L));
        assertThat(entity.isPersistent(), is(true));
        
        //FindAll
        assertThat(SomeEntity.findAll().get(0), is(entity));
        
        //Select
        assertThat(SomeEntity.find("name=?", "hana").get(0), is(entity));
    }
    
    @Test
    public void testDelete() {
        final SomeEntity entity = new SomeEntity("hana");
        assertThat(SomeEntity.count(), is(0L));
        assertThat(entity.isPersistent(), is(false));
        
        //Save
        entity.save();
        assertThat(SomeEntity.count(), is(1L));
        assertThat(entity.isPersistent(), is(true));
        
        //Delete
        entity.delete();
        assertThat(SomeEntity.count(), is(0L));
        assertThat(entity.isPersistent(), is(false));
    }
}
