package com.github.asufana.jpa.functions.test;

import org.junit.runner.*;
import org.junit.runner.notification.*;
import org.junit.runners.*;
import org.junit.runners.model.*;

import com.github.asufana.jpa.*;

public class JPAEntityJUnitRunner extends Runner {
    
    private final JUnit4 jUnit4;
    
    public JPAEntityJUnitRunner(final Class<?> testClass) throws InitializationError {
        jUnit4 = new JUnit4(testClass);
        
        //Enhance class
        JPA.instance();
    }
    
    @Override
    public Description getDescription() {
        return jUnit4.getDescription();
    }
    
    @Override
    public void run(final RunNotifier notifier) {
        jUnit4.run(notifier);
    }
    
}
