package com.mills.recipes.services;

import com.mills.recipes.domain.Recipe;

import java.util.Set;

/**
 * Created by rachelmills on 6/7/19.
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
}
