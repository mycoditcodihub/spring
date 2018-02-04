package com.ibeka.spring5recipeapp.services;

import com.ibeka.spring5recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
