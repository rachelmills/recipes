package com.mills.recipes.bootstrap;

import com.mills.recipes.domain.*;
import com.mills.recipes.repositories.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rachelmills on 5/7/19.
 */
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private NotesRepository notesRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, CategoryRepository categoryRepository, NotesRepository notesRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.notesRepository = notesRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initialiseData();
    }

    private void initialiseData() {
        createGuacamoleRecipe();
        createSpicyGrilledChickenTacos();
    }

    private void createSpicyGrilledChickenTacos() {
        Recipe tacos = new Recipe();
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> dash = unitOfMeasureRepository.findByDescription("Dash");
        Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> pint = unitOfMeasureRepository.findByDescription("Pint");

        Set<Ingredient> tacoIngredients = new HashSet<>();
        tacoIngredients.add(new Ingredient("Chilli powder", new BigDecimal(2), tablespoon.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Dried oregano", new BigDecimal(1), teaspoon.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Dried cumin", new BigDecimal(1), teaspoon.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Sugar", new BigDecimal(1), teaspoon.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Salt", new BigDecimal(0.5), teaspoon.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Garlic", new BigDecimal(1), null, tacos));
        tacoIngredients.add(new Ingredient("Orange zest", new BigDecimal(1), tablespoon.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Orange juice", new BigDecimal(3), tablespoon.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Olive oil", new BigDecimal(2), tablespoon.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Boneliess chicken thighs", new BigDecimal(4), null, tacos));
        tacoIngredients.add(new Ingredient("Corn tortillas", new BigDecimal(8), null, tacos));
        tacoIngredients.add(new Ingredient("Packed baby arugula", new BigDecimal(3), cup.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Avocado", new BigDecimal(3), null, tacos));
        tacoIngredients.add(new Ingredient("Radish", new BigDecimal(4), null, tacos));
        tacoIngredients.add(new Ingredient("Cherry tomatoes", new BigDecimal(0.5), pint.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Red Onion", new BigDecimal(0.25), null, tacos));
        tacoIngredients.add(new Ingredient("Sour cream", new BigDecimal(0.5), cup.orElseThrow(RuntimeException::new), tacos));
        tacoIngredients.add(new Ingredient("Lime", new BigDecimal(1), null, tacos));

        Set<Category> tacoCategories = new HashSet<>();
        Optional<Category> dinner = categoryRepository.findByDescription("Dinner");
        dinner.ifPresent(tacoCategories::add);
        Optional<Category> grill = categoryRepository.findByDescription("Grill");
        grill.ifPresent(tacoCategories::add);
        Optional<Category> quickAndEasy = categoryRepository.findByDescription("Quick and easy");
        quickAndEasy.ifPresent(tacoCategories::add);
        Optional<Category> chicken = categoryRepository.findByDescription("Chicken");
        chicken.ifPresent(tacoCategories::add);
        Optional<Category> mexican = categoryRepository.findByDescription("Mexican");
        mexican.ifPresent(tacoCategories::add);


        dinner.ifPresent(tacoCategories::add);
        grill.ifPresent(tacoCategories::add);
        quickAndEasy.ifPresent(tacoCategories::add);
        chicken.ifPresent(tacoCategories::add);
        mexican.ifPresent(tacoCategories::add);

        tacos.setDescription("Spicy Grilled Chicken Tacos Recipe");
        tacos.setDirections("Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n)" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setCategories(tacoCategories);
        tacos.setCookTime(15);
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.setIngredients(tacoIngredients);

        File tacoImage = new File("/Users/rachelmills/Desktop/SpringFramework5/recipes/src/main/resources/images/taco.jpg");
        byte[] imageInBytes = convertImageToByteArray(tacoImage);
        tacos.setImage(imageInBytes);

        String recipeNotes = "Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.";
        Notes tacoNotes = new Notes(tacos, recipeNotes);

        tacos.setNotes(tacoNotes);
        tacos.setPrepTime(20);
        tacos.setServing(4);
        tacos.setSource("what is this field?");
        recipeRepository.save(tacos);

//        tacoNotes.setRecipe(tacos);
//        notesRepository.save(tacoNotes);
    }

    private void createGuacamoleRecipe() {
        Recipe guacamole = new Recipe();
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> dash = unitOfMeasureRepository.findByDescription("Dash");

        Set<Ingredient> guacIngredients = new HashSet<>();
        guacIngredients.add(new Ingredient("Avocado", new BigDecimal(2), null, guacamole));
        guacIngredients.add(new Ingredient("Salt", new BigDecimal(0.5), teaspoon.orElseThrow(RuntimeException::new), guacamole));
        guacIngredients.add(new Ingredient("Lime juice", new BigDecimal(1), tablespoon.orElseThrow(RuntimeException::new), guacamole));
        guacIngredients.add(new Ingredient("Red Onion", new BigDecimal(2), tablespoon.orElseThrow(RuntimeException::new), guacamole));
        guacIngredients.add(new Ingredient("Chilli", new BigDecimal(2), null, guacamole));
        guacIngredients.add(new Ingredient("Cilantro", new BigDecimal(2), tablespoon.orElseThrow(RuntimeException::new), guacamole));
        guacIngredients.add(new Ingredient("Black pepper", new BigDecimal(1), dash.orElseThrow(RuntimeException::new), guacamole));
        guacIngredients.add(new Ingredient("Tomato", new BigDecimal(0.5), null, guacamole));

        Set<Category> guacamoleCategories = new HashSet<>();
        Optional<Category> mexican = categoryRepository.findByDescription("Mexican");
        mexican.ifPresent(guacamoleCategories::add);

        Category category2 = new Category();
        category2.setDescription("Dips");
        categoryRepository.save(category2);
        guacamoleCategories.add(category2);

        Category category3 = new Category();
        category3.setDescription("Healthy");
        categoryRepository.save(category3);
        guacamoleCategories.add(category3);

        guacamole.setDescription("Perfect Guacamole");
        guacamole.setDirections("Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n)" +
                "Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown." +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this guacamole and adjust to your taste.\n" +
                "Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setCategories(guacamoleCategories);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setIngredients(guacIngredients);

        File guacomoleImage = new File("/Users/rachelmills/Desktop/SpringFramework5/recipes/src/main/resources/images/guacamole.jpg");
        byte[] imageInBytes = convertImageToByteArray(guacomoleImage);
        guacamole.setImage(imageInBytes);

        String recipeNotes = "The BEST guacamole! So easy to make with ripe avocados, salt, serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips. Watch how to make guacamole - it's easy!";
        Notes guacamoleNotes = new Notes(guacamole, recipeNotes);

        guacamole.setNotes(guacamoleNotes);
        guacamole.setPrepTime(10);
        guacamole.setServing(4);
        guacamole.setSource("what is this field?");
        recipeRepository.save(guacamole);
    }

    private byte[] convertImageToByteArray(File guacomoleImage) {
        byte[] imageInBytes = new byte[]{};
        try {
            FileInputStream fis = new FileInputStream(guacomoleImage);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buffer)) != -1;) {
                    byteArrayOutputStream.write(buffer, 0, readNum);
                    System.out.println("read " + readNum + " bytes");
                }
            } catch (IOException e) {
                Logger.getLogger(RecipeBootstrap.class.getName()).log(Level.SEVERE, null, e);
            }
            imageInBytes = byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageInBytes;
    }
}
