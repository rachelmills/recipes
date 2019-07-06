package com.mills.recipes.domain;

import javax.persistence.*;

/**
 * Created by rachelmills on 3/7/19.
 */
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne  // if we delete the recipe notes we don't want to delete the recipe so no (default) cascade type is used
    private Recipe recipe;

    @Lob  // jpa will store in clob field in database
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
