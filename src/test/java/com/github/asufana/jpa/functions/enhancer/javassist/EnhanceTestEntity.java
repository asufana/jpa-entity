package com.github.asufana.jpa.functions.enhancer.javassist;

import javax.persistence.*;

@Entity
public class EnhanceTestEntity extends AbstractEnhanceTestEntity {
    
    @Id
    private Long id;
}
