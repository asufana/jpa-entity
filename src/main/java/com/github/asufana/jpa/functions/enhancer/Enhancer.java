package com.github.asufana.jpa.functions.enhancer;

import net.bytebuddy.*;
import net.bytebuddy.agent.*;
import net.bytebuddy.dynamic.loading.*;
import net.bytebuddy.implementation.*;
import net.bytebuddy.matcher.*;

public class Enhancer {
    
    private static final Enhancer INSTANCE = new Enhancer();
    
    private Enhancer() {
        ByteBuddyAgent.installOnOpenJDK();
    }
    
    public static void enhance(final Class<?> klass) {
        new ByteBuddy().redefine(klass)
                       .method(ElementMatchers.named("toString"))
                       .intercept(FixedValue.value("Hello world!"))
                       .make()
                       .load(INSTANCE.getClass().getClassLoader(),
                             ClassReloadingStrategy.fromInstalledAgent());
    }
}
