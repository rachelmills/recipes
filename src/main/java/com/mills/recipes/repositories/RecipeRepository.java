package com.mills.recipes.repositories;

import com.mills.recipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rachelmills on 4/7/19.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
