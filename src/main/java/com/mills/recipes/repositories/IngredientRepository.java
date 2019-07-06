package com.mills.recipes.repositories;

import com.mills.recipes.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rachelmills on 6/7/19.
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
