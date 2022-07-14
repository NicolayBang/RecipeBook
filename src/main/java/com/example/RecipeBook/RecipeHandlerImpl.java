package com.example.RecipeBook;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


/**
 * TODO Make this a singleton class for implementing multithreaded access to the database.
 */
@Service
public class RecipeHandlerImpl implements RecipeHandler {

    Map<Long, Recipe> recipes = new HashMap<>();
    long currId = 1;
    JSONConverter jsonConverter = new JSONConverter();
    Gson gson = new Gson();

    public RecipeHandlerImpl(){
        init();
    }
    void init(){
    Recipe recipe = new Recipe();
    recipe.setId(currId);
    recipe.setName("Traditional Andalucian Gazpacho");
    recipe.setDescription("Produces 1L of gazpacho. Recipe from Spanish newspaper 'La Vanguardi'");
    recipe.addIngredient("1Kg Ripe Tomatoes");
    recipe.addIngredient("1 Green Italian Pepper");
    recipe.addIngredient("1 Cucumber");
    recipe.addIngredient("1 Garlic Clove");
    recipe.addIngredient("3 tbsp. Olive Oil");
    recipe.addIngredient("3 tbsp. White Wine Vinegar");
    recipe.addIngredient("Pinch of Salt");
    recipe.setDirections("Mix it all in a blender and serve cold");
    recipes.put(recipe.getId(), recipe);
    }

    @Override
    public String getRecipes() {
        Collection <Recipe> results = recipes.values();
        List <Recipe> response = new ArrayList<>(results);
        return gson.toJson(response);
    }

    @Override
    public String getRecipe(Long id) {
        return gson.toJson(recipes.get(id));
    }

    @Override
    public Response createRecipe(Gson gsonPost) {
        Recipe recipe = gson.fromJson(String.valueOf(gsonPost), Recipe.class);
        recipe.setId(++currId);
        recipes.put(recipe.getId(), recipe);
        return Response.ok(recipe).build();
    }

    @Override
    public Response updateRecipe(Recipe recipe) {

        Recipe currRecipe=recipes.get(recipe.getId());

        Response response;
        if(currRecipe != null){
            recipes.put(recipe.getId(), recipe);
            response = Response.ok().build();
        }else{
            response=Response.notModified().build();
        }
        return response;
    }

    @Override
    public Response deleteRecipe(Long id) {
        Recipe recipe = recipes.get(id);

        Response response;
        if(recipe != null){
            recipes.remove(id);
            response = Response.ok().build();
        }else{
            response=Response.notModified().build();
        }
        return response;
    }
}
