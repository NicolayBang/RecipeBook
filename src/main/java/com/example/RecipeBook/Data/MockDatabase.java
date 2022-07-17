package com.example.RecipeBook.Data;

import com.example.RecipeBook.JSONHandler.JSONConverter;
import com.example.RecipeBook.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class MockDatabase {
   public Map<Long, Recipe> recipes = new HashMap<>();
    JSONConverter jsonConverter = new JSONConverter();
    public Gson gson;
    public long currId = 1;

    public MockDatabase(){
        init();
    }
    void init() {
        gson = new GsonBuilder().registerTypeAdapter(Recipe.class, jsonConverter)
                .serializeNulls().create();

        Recipe recipe = new Recipe();
        recipe.setId(currId);
        recipe.setName("Traditional Andalucian Gazpacho");
        recipe.setDescription("Produces 1L of gazpacho. Recipe from Spanish newspaper 'La Vanguardi', ");
        recipe.addIngredient("1Kg Ripe Tomatoes, ");
        recipe.addIngredient("1 Green Italian Pepper, ");
        recipe.addIngredient("1 Cucumber, ");
        recipe.addIngredient("1 Garlic Clove, ");
        recipe.addIngredient("3 tbsp. Olive Oil, ");
        recipe.addIngredient("3 tbsp. White Wine Vinegar, ");
        recipe.addIngredient("A Pinch of Salt");
        recipe.setDirections("Mix it all in a blender and serve cold");
        recipes.put(recipe.getId(), recipe);
    }
}
