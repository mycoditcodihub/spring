package com.ibeka.spring5recipeapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * The package model can also be called domain. Anyone is correct.
 * A bidirectional relationship is created this way:
 * i. Create 2 reference variables. ie Each entity/class/table should refer to the second entity/class/table.
 * For ex 1
 * - private Notes notes; //notes is created inside Recipe class/Entity/Table
 * - private Recipe recipe; //recipe variable is created inside Notes class/Entity/Table
 * Ex 2:
 * - private Set<Ingredient> ingredients; //ingredients variable is created inside Recipe class/Entity/Table
 * - private Recipe recipe; //recipe variable is created inside Ingredient class/Entity/Table
 * ii. Add the JPA relationship annotation eg @OneToOne, @OneToMany, @ManyToOne, @ManyToMany to both classes eg
 * Ex 1:
 * - Since you have one Recipe class which can relate to many ingredient fields, so you annotate ingredients variable in Recipe class with @OneToMany. This means that the Recipe class is the One side while the Ingredients class is the Many side
 * - Then you go the the ingredients class and annotate the recipe variable with @ManyToOne. The Many side is the Ingredients class/Entity while the One side is the Recipe class/Entity
 * Ex 2:
 * - Annotate the notes variable inside Recipe class/Entity with @OneToOne
 * - Also go to the Notes class/Entity and annotate the recipe variable with @OneToOne
 *
 * A unidirectional relationship is created this way:
 * i. Create 1 reference variable. For ex:
 * private UnitOfMeasure uom; //uom reference variable is created inside Ingredient class/Entity/Table
 * ii. Inside UnitOfMeasure class, you do not create a reference to Ingredients (unlike the bidirectional relationship where you create references between the classes/Entities
 * iii. Only annotate uom variable inside Ingredient class with @OneToOne
 */

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//If you don't use IDENTITY and you do not include the ID value in our sql script, we will get a jdbcSQLException like this: Caused by: org.h2.jdbc.JdbcSQLException: NULL not allowed for column "ID"; SQL statement:
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    /*
     *EnumType can either be ORDINAL or STRING. The difference is how the way data is persisted in both cases
     * i. ORDINAL: This is the default. What this does is that in the Difficulty class, we set 3 constant (EASY, MODERATE, HARD), this will cause each of the constants to be persisted as 1, 2, 3. So if you add another constant like this:
     *  (EASY, MODERATE, MODERATELY_HARD, HARD), then the value of HARD has changed from 3 to 4. This may cause some issues with coding if you don't change the code before persisting.
     *  ii. STRING: This persists each constant as string so the issue seen in ORDINAL is avoided.
     */
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    /*
     * We are saying that each Set of Ingredient (ie <Ingredient>) will be stored in a property named "recipe".
     * Note that the property named "recipe" was created in the Ingredient class and is of type "Recipe".
     * Also read: https://stackoverflow.com/questions/2584521/in-a-bidirectional-jpa-onetomany-manytoone-association-what-is-meant-by-the-in
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    @Lob
    private Byte[] image;

    //So we want Recipe object (Entity) to own this relationship
    // We also want to set the cascade type to all so that if we for instance we want to delete the Recipe object,
    // every entity related to the object will also be deleted
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    /*
     * Note that h2 database creates table names in all caps. So recipe_category will be named "RECIPE_CATEGORY".
     * If you remove @JoinTable from categories field and then run this program, in http://localhost:8080/h2-console, you will see two tables created (CATEGORY_RECIPES and RECIPE_CATEGORIES). By default, if you do not include a @JoinTable and the mappedBy for a bi-directional ManyToMany relationship, hibernate creates ...
     * two tables like this:
      * Table 1:
      * In private Set<Category> categories; the generic type is a Category object (in database terms, it is a Category table and the other side of the relationship is the recipes field in the Category class.
      * So hibernate names this table based on this relationship as CATEGORY_RECIPES.
      * Table 2:
      * This will be the inverse relationship ie from the Category class, the field involved in the relationship is recipes and has a generic type of Recipe ie Set<Recipe> recipes.
       * So the relationship will be RECIPE_CATEGORIES.
     * However, in the bi-directional ManyToMany relationship, we do not want to tables to be created by hibernate showing the relationship between the entities. So to join the tables by columns we want, we use the @JoinTable.
     * However, just using the @JoinTable will not join both tables by their columns (ie ids - recipe_id and category_id). We still need to go to the the Category Class and add the mappedBy.
     * See the screenshots (Bidirectional-Without_JoinTable_and_mappedBy and Bidirectional-With_JoinTable_and_mappedBy) in the spring folder for the database structures for both scenarios.
     */
    @ManyToMany
    @JoinTable(name="recipe_category",
        joinColumns=@JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories;
//    private Category categories; //If you attempt to use a JPA relationship of @OneToMany, @ManyToOne or @ManyToMany on a non Collection, you will get a runtime error like:
    /*
     * ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is org.hibernate.AnnotationException: Illegal attempt to map a non collection as a @OneToMany, @ManyToMany or @CollectionOfElements: com.ibeka.Recipe.model.Recipe.categories

     */

    public Recipe() {
        this.ingredients = new HashSet<>();
        this.categories = new HashSet<>();
    }

    //Note that if you use @Data or any other annotation that internally provides getters or setters, anything named get*** or set** will be removed even if it the method was not supposed to be a setter or getter.
    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);//We are adding this for bidirectional relationship and avoid doing it on lines 177 and 178 in RecipeBootstrap class
    }

    public Recipe addIngredient(Ingredient ingredient) {
        //We are adding lines 188 and 189 for bidirectional relationship and avoid doing it on lines 177 and 178 in RecipeBootstrap class
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
