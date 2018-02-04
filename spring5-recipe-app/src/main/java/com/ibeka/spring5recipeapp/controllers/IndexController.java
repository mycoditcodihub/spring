package com.ibeka.spring5recipeapp.controllers;

import com.ibeka.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    //1. This part does not use the service interface.
//    private CategoryRepository categoryRepository;
//    private UnitOfMeasureRepository unitOfMeasureRepository;
//
//    //Constructor based dependency injection, and we don't have to use the @Autowired because constructor based dependency injection is done by default in Spring 4 and upward
//    @Autowired
//    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//        this.categoryRepository = categoryRepository;
//        this.unitOfMeasureRepository = unitOfMeasureRepository;
//    }
//
//    @RequestMapping({"","/","/index"})
//    public String getIndexPage(){
//        System.out.println("This is request mapping for index...");
//
//        /*
//         * The String "American" is from data.sql. The string value "American" corresponds to the field value for description column in category table in database.
//         * The String "Teaspoon" is from data.sql. The string value "Teaspoon" corresponds to the field value for uom column in unit_Of_measure table in database.
//         */
//        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Teaspoon");
//
//        System.out.println("Category Id is: " + categoryOptional.get().getId());
//        System.out.println("UOM Id is: " + unitOfMeasureOptional.get().getId());
//
//        return "index";
//    }



    //2. This part uses the service interface
    private final RecipeService recipeService; //Pass the RecipeService

    //Constructor based dependency injection, and we don't have to use the @Autowired because constructor based dependency injection is done by default in Spring 4 and upward
    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        log.debug("log from getIndexPage..");
        System.out.println("This is request mapping for index...");
        model.addAttribute("recipes", recipeService.getRecipes()); //We called the getRecipes() and assigned to a model attribute object called recipes.
        return "index";
    }
}
