package com.github.asufana.jpa.functions;

import java.lang.reflect.*;
import java.util.*;

import javax.persistence.*;

import org.reflections.*;
import org.reflections.scanners.*;
import org.reflections.util.*;

import com.github.asufana.jpa.annotations.*;
import com.github.asufana.jpa.exceptions.*;

public class ClassScanner {
    
    //private static final Reflections INSTANCE = new Reflections("com.github.asufana");
    private static final Reflections INSTANCE = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("com.github.asufana"))
                                                                                          .setScanners(new SubTypesScanner(false),
                                                                                                       new TypeAnnotationsScanner(),
                                                                                                       new FieldAnnotationsScanner()));
    
    public static Set<Class<?>> findAllEntityClasses() {
        return INSTANCE.getTypesAnnotatedWith(Entity.class);
    }
    
    public static Map<String, String> findJPAEntityConfig() {
        final Set<Field> fields = findFieldWithJPAEntityConfig();
        return getFieldValue(fields);
    }
    
    private static Set<Field> findFieldWithJPAEntityConfig() {
        final Set<Field> fields = INSTANCE.getFieldsAnnotatedWith(JPAEntityConfig.class);
        if (fields.size() == 0) {
            throw new JPAEntityException(String.format("Can't find @%s field.",
                                                       JPAEntityConfig.class.getSimpleName()));
        }
        return fields;
    }
    
    @SuppressWarnings("unchecked")
    private static Map<String, String> getFieldValue(final Set<Field> fields) {
        Object object = null;
        Field field = null;
        for (final Field f : fields) {
            field = f;
            isStaticField(field);
            object = getValue(field);
        }
        if (object == null) {
            throw new JPAEntityException(String.format("@JPAEntityConfig field '%s' has no value.",
                                                       field.getType()
                                                            .getSimpleName()));
        }
        if (object instanceof Map<?, ?> == false) {
            throw new JPAEntityException(String.format("@JPAEntityConfig field '%s' value is not Map.",
                                                       field.getType()
                                                            .getSimpleName()));
        }
        return (Map<String, String>) object;
    }
    
    private static void isStaticField(final Field field) {
        if (Modifier.isStatic(field.getModifiers()) == false) {
            throw new JPAEntityException(String.format("@JPAEntityConfig field %s is not static.",
                                                       field.getType()
                                                            .getSimpleName()));
        }
    }
    
    private static Object getValue(final Field field) {
        try {
            return field.get(null);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            throw new JPAEntityException(e);
        }
    }
    
}
