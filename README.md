

# JPAEntity

A PlayFramework1-like JPA Entity manager.

[![Build Status](https://travis-ci.org/asufana/JPAEntity.svg?branch=master)](https://travis-ci.org/asufana/JPAEntity)


## Examples

### Configuration

DB config is Map field annotated with @JPAEntityConfig.

```java
@JPAEntityConfig
public final static Map<String, String> params = new HashMap<String, String>() {{
    put("db.driver", "org.h2.Driver");
    put("db.url", "jdbc:h2:mem:test");
    put("db.user", "sa");
    put("db.pass", "");
    
    put("hibernate.hbm2ddl.auto", "create");
    put("javax.persistence.transaction", "RESOURCE_LOCAL");
    put("hibernate.dialect", H2Dialect.class.getName());
}};

@JPAEntityEnhanceClassNames
public final static List<String> classNames = new ArrayList<String>() {{
    add("com.github.asufana.jpa.entity.SomeEntity");
}};

```

### Model

Create JPA annotated class.

```java
@Entity
public class SomeEntity extends JPAEntity<SomeEntity> {
    @Id @Column
    private final String name;
    
    public SomeEntity(final String name) {
        this.name = name;
    }
}
```

### CRUD

```java
JPA.beginTran();

//Count
assertThat(SomeEntity.count(), is(0L));

final SomeEntity entity = new SomeEntity("hana");
assertThat(entity.isPersistent(), is(false));

//Save
entity.save();
assertThat(SomeEntity.count(), is(1L));
assertThat(entity.isPersistent(), is(true));

//Select
assertThat(SomeEntity.find("name=?", "hana").get(0), is(entity));

//SelectAll
assertThat(SomeEntity.findAll().get(0), is(entity));

//Delete
entity.delete();
assertThat(SomeEntity.count(), is(0L));
assertThat(entity.isPersistent(), is(false));

JPA.commitTran();
```

