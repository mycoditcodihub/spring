package com.ibeka.JokeApp.controller;

import com.ibeka.JokeApp.service.JokeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JokeController {

    JokeServices jokeServices;

    @Autowired//We actually don't need to add the Autowired annotation for constructor based dependency injection
    public JokeController(JokeServices jokeServices) {
        this.jokeServices = jokeServices;
    }

    @RequestMapping({"/", ""}) //This is how you map the context root to the view. The context root is {"/", ""}. The view name is chucknorris. The attribute name is "jokes"
    public String getQuotes(Model model) {
        model.addAttribute("jokes", jokeServices.getJokes()); //This adds an attribute named joke from jokesServices

        return "chucknorris"; //This would be the name of the view
    }

}
