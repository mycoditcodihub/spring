package com.ibeka.spring5recipeapp.services;

import com.ibeka.spring5recipeapp.model.Recipe;
import com.ibeka.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService; //This is the class we want to test so we have to add it to the test

    @Mock
    RecipeRepository recipeRepository; //RecipeServiceImpl class is dependent on the RecipeRepository, so we have to mock this dependency

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);//This tells Mockito to get us a mock of the RecipeRepository

        recipeService = new RecipeServiceImpl(recipeRepository); //initialize recipeService

    }

    /*

     @Test
    public void getRecipes() throws Exception{

        Set<Recipe> recipes = recipeService.getRecipes();

        //We have not added any object to the set so the size of the set is 0.
        assertEquals(recipes.size(), 0); This will complete successfully as both equals 0

        assertEquals(recipes.size(), 1); // This would fail as both do not equal 0.
    }

     */

    @Test
    public void getRecipes() throws Exception {

        //Use this approach to add only one element
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        /*
         * You have to import static org.mockito.Mockito.when; However, for thenReturn, you don't need any import. You have to close the when() method before .thenReturn().
         * The line below says that when the recipeService.getRecipes() method is called, then return recipesData
         */
        when(recipeService.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1); // This would now pass as both equal 1.

        //Use verify to test interaction/integration
        verify(recipeRepository, times(1)).findAll();
    }
}