package com.ibeka.spring5recipeapp.repositories;

import com.ibeka.spring5recipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

//The CrudRepository takes a generic type<T, ID>
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
