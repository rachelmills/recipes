package com.mills.recipes.services;

import com.mills.recipes.domain.Notes;
import com.mills.recipes.domain.Recipe;
import com.mills.recipes.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by rachelmills on 10/7/19.
 */
public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

        @Mock
        RecipeRepository recipeRepository;

        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
            recipeService = new RecipeServiceImpl(recipeRepository);
        }

        @Test
        public void getRecipes() throws Exception {
            Recipe recipe = new Recipe();
            Notes notes = new Notes();
            notes.setRecipeNotes("abc");
            recipe.setNotes(notes);
            Set<Recipe> recipes = new HashSet<>();
            recipes.add(recipe);

            when(recipeRepository.findAll()).thenReturn(recipes);
            Set<Recipe> recipesFound = recipeService.getRecipes();
            assertEquals(recipesFound.size(), 1);
            verify(recipeRepository, times(1)).findAll();
        }

        @Test
        public void getRecipeById() {
            Recipe recipe = new Recipe();
            recipe.setId(1L);
            Optional<Recipe> recipeOptional = Optional.of(recipe);
            when(recipeRepository.findById(anyLong())).thenReturn(java.util.Optional.of(recipe));

            Recipe recipeFound = recipeService.getRecipeById(1L);
            assertNotNull(recipeFound);
            verify(recipeRepository, times(1)).findById(anyLong());
            verify(recipeRepository, never()).findAll();
        }
}
