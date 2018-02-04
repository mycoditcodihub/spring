package com.ibeka.spring5recipeapp.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"}) //You have to use the EqualsAndHashCode annotation when you have bi-direction relationship and exclude the field reference from the other entity (in this case, recipe reference)
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//If you don't use IDENTITY and you do not include the ID value in our sql script, we will get a jdbcSQLException like this: Caused by: org.h2.jdbc.JdbcSQLException: NULL not allowed for column "ID"; SQL statement:
    private Long id;
    private String description;
    private BigDecimal amount;

    /*
     * Also we do not want to set up a cascade since we do not want the UnitOfMeasure object to be deleted or changed when we delete/change the Ingredient object (class).
     * Also note that we do not have to check
     */
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    //We are going to set up the inverse relationship.
    @ManyToOne
    private Recipe recipe;

    public Ingredient(){
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

}
