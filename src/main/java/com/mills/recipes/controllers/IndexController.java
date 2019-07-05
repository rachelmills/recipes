package com.mills.recipes.controllers;

import com.mills.recipes.domain.Category;
import com.mills.recipes.domain.UnitOfMeasure;
import com.mills.recipes.repositories.CategoryRepository;
import com.mills.recipes.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by rachelmills on 29/6/19.
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        Optional<Category> category = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
        System.out.println("Cat id is " + category.get().getId());
        System.out.println("Unit of measure is " + unitOfMeasure.get().getId());
        return "index";
    }
}
