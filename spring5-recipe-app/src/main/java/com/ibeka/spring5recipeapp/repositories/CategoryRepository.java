package com.ibeka.spring5recipeapp.repositories;

import com.ibeka.spring5recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*
 * Spring Data with Spring 5 is now using Optional ie (Optional<T> findById(ID var1);)
 * To see the query methods in spring data, place your cursor on CrudRepository and then command + enter
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    /*
     * Created another finder
     * Make sure that whatever you add to findBy must-
     * 1. have the same name as whatever you have inside the argument eg description in findByDescription must match the String name description inside (String description)
     * 2. Description must be upper case in findByDescription. Note, this follows Java's camelCase naming convention
     * 3. In the entity class Category, the Id variable type must be the same type as what is defined in CrudRepository<Category, Long> ie, both types are Long.
     * 4. In the entity class, Category, the variable name you want to use to match ie "description" in  private String description; must match points 1 and 2 described above.
     */

    Optional<Category> findByDescription(String description);
}
