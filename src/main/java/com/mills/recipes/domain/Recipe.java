package com.mills.recipes.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rachelmills on 3/7/19.
 */
@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This type supports the automatic generation of a sequence
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer serving;
    private String source;
    private String url;
    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Enumerated(value=EnumType.STRING)
    private Difficulty difficulty;

    @Lob   // jpa will create as blob
    private byte[] image;

    @OneToOne(cascade = CascadeType.ALL)  // if we delete recipe we want to delete recipe notes
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient) { // convenience method and encapsulates logic
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}

