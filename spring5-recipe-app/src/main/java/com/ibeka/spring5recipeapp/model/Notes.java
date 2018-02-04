package com.ibeka.spring5recipeapp.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//If you don't use IDENTITY and you do not include the ID value in our sql script, we will get a jdbcSQLException like this: Caused by: org.h2.jdbc.JdbcSQLException: NULL not allowed for column "ID"; SQL statement:
    private Long id;

    //We will not add the cascade, we want Recipe to own the relationship such that for instance if we delete the note object, we do not want it to cascade to the recipe object.
    @OneToOne
    private Recipe recipe;

    /*
     * According to: https://docs.oracle.com/javaee/7/api/javax/persistence/Lob.html
     * @Lob Specifies that a persistent property or field should be persisted as a large object to a database-supported large object type.
     * @javax.persistence.Lob signifies that the annotated field should be represented as BLOB (binary data) in the DataBase.
     * We could have BLOB and CLOB.
     * You can annotated any serializable data type with this annotation. In JPA, upon persisting (retrieval) the field content will be serialized (deserialized) using standard Java serialization.
     * One use case to using @Lob is that since the hibernate has about 250 characters for Strings, you would want to be able to type more than that.
     */
    @Lob
    private String recipeNotes;

}
