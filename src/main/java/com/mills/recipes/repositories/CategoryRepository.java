package com.mills.recipes.repositories;

import com.mills.recipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rachelmills on 4/7/19.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
