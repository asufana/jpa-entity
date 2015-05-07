package com.github.asufana.jpa.functions.enhancer;

import net.bytebuddy.*;
import net.bytebuddy.agent.*;
import net.bytebuddy.dynamic.loading.*;
import net.bytebuddy.implementation.*;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.matcher.*;

import com.github.asufana.jpa.*;

public class Enhancer {
    
    private static final Enhancer INSTANCE = new Enhancer();
    
    private Enhancer() {
        ByteBuddyAgent.installOnOpenJDK();
    }
    
    public static void enhance(final Class<?> klass) {
        new ByteBuddy().redefine(klass)
                       .method(ElementMatchers.named("count2"))
                       .intercept(MethodDelegation.to(Count.class))
                       .make()
                       .load(INSTANCE.getClass().getClassLoader(),
                             ClassReloadingStrategy.fromInstalledAgent());
    }
    
    public static class Count {
        public static Long count2(@Origin final Class<?> klass) {
            return Long.parseLong(JPA.instance()
                                     .em()
                                     .createQuery(String.format("select count(*) from %s e",
                                                                klass.getSimpleName()))
                                     .getSingleResult()
                                     .toString());
        }
    }
    
}
