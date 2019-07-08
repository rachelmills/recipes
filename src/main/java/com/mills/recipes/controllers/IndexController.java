package com.mills.recipes.controllers;

import com.mills.recipes.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rachelmills on 29/6/19.
 */
@Controller
@Slf4j
public class IndexController {

    private RecipeServiceImpl recipeService;


    public IndexController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("adding recipes to the model");
        return "index";
    }
}
