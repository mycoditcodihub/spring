package com.ibeka.spring5recipeapp.controllers;

import com.ibeka.spring5recipeapp.model.Recipe;
import com.ibeka.spring5recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController; //This is the class I want to test

    @Mock
    RecipeService recipeService; // The IndexController class is dependent on the RecipeService, so I will mock this dependency

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this); //This tells Mockito to get a mock of the RecipeService object
        indexController = new IndexController(recipeService); //Initialize indexController
    }

    //This is a unit test. This is the best way to test controllers because it does not bring up the Spring context
    @Test
    public void testMockMVC() throws Exception{
        /*
         * The MockMvcBuilders has 2 options: standaloneSetup() and the webAppContextSetup. If you use the webAppContextSetup(), this loads the Spring context, so we no longer will be talking about unit testing.
         * The standaloneSetup(), takes a controller argument or several constructors
         */
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() throws Exception{

        //If you want to add more than 1 object, use the approach below
        Set<Recipe> recipesTest = new HashSet<>();

        recipesTest.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipesTest.add(recipe);

        Recipe recipe1 = new Recipe();
        recipe1.setId(2L);
        recipesTest.add(recipe1);

        //The 2 other Recipe objects below will not be added by Spring to recipesTest and the verify test will fail
        recipesTest.add(new Recipe());
        recipesTest.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipesTest);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String view = indexController.getIndexPage(model);

        //Test if indexController.getIndexPage() returns the String "index"
        assertEquals("index", view);

        //Interaction/integration tests
        //1. Verify recipeService.getRecipe() is called just once
        verify(recipeService, times(1)).getRecipes();
        //2. Verify mode.addAttribute() is called just once
        verify(model, times(1)).addAttribute("recipes", recipeService.getRecipes()); //The String "recipes" matches the model attribute object called "recipes" in IndexController class, otherwise it will fail.
//        verify(model, times(1)).addAttribute(eq("recipes"), recipeService.getRecipes()); // This will fail because we used raw values with the matchers (eq()). We should not not use already created set in this case.
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());

        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(3, setInController.size());//This will true because only 3 objects instead of 5 objects were added to the set
    }
}