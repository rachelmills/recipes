package com.mills.recipes.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by rachelmills on 3/7/19.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = "recipe")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne  // if we delete the recipe notes we don't want to delete the recipe so no (default) cascade type is used
    private Recipe recipe;

    @Lob  // jpa will store in clob field in database
    private String recipeNotes;

}
