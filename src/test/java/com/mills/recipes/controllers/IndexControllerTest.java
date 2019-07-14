package com.mills.recipes.controllers;

import com.mills.recipes.domain.Recipe;
import com.mills.recipes.services.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by rachelmills on 14/7/19.
 */
public class IndexControllerTest {

    @Mock
    private RecipeServiceImpl recipeService;

    @Mock
    private Model model;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() throws Exception {
        //given
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("recipe1");
        recipes.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("recipe2");
        recipes.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        //when
        String index = indexController.getIndexPage(model);

        assertEquals("index", index);
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        verify(recipeService, times(1)).getRecipes();
        Set<Recipe> recipeSet = argumentCaptor.getValue();
        assertEquals(2, recipeSet.size());
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }
}