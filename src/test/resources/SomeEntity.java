package test.domain;

import javax.persistence.*;

import com.github.asufana.jpa.entity.*;

@Entity
public class SomeEntity extends JPAEntity {
    @Id
    @Column
    private final String name;
    
    public SomeEntity(final String name) {
        this.name = name;
    }
}
