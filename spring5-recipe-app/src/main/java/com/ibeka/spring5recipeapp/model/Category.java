package com.ibeka.spring5recipeapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//If you don't use IDENTITY and you do not include the ID value in our sql script, we will get a jdbcSQLException like this: Caused by: org.h2.jdbc.JdbcSQLException: NULL not allowed for column "ID"; SQL statement:
    private Long id;
    private String description;

    /*
     * This is telling hibernate that in the bidirectional relationship, we want to map this relationship by the categories variable created in Recipe class, so no CATEGORY_RECIPE table will be created in the database.
     * Instead, only the RECIPE_CATEGORY table will be created as defined in the categories field in the Recipe class an in addition, both the recipe_id and category_id will be created inside the RECIPE_CATEGORY table
     */

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Category() {
    }

    public Category(String description) {
        this.description = description;
        this.recipes = new HashSet<>();
    }

}
