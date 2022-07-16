package com.example.RecipeBook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


/**
 * TODO Make this a singleton class for implementing multithreaded access to the database.
 * TODO implement this solution for POST request https://howtodoinjava.com/jersey/jax-rs-gson-example/
 */
@Service
public class RecipeHandlerImpl implements RecipeHandler {
//    @Bean
//    public org.codehaus.jackson.jaxrs.JacksonJsonProvider jsonProvider(){
//        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
//        return jsonProvider;
//    }
//    @Bean
//    public com.google.gson.Gson jsonProvider(){
//        Gson jsonProvider = new Gson();
//        return jsonProvider;
//    }
//    private static RecipeHandlerImpl instance = null;
    Map<Long, Recipe> recipes = new HashMap<>();
    long currId = 1;
    JSONConverter jsonConverter = new JSONConverter();
    GSONHandler gsonHandler = new GSONHandler();
    Gson gson;
   // ApplicationConfig applicationConfig = new ApplicationConfig();

    public RecipeHandlerImpl(){
        init();
    }

//    public static RecipeHandlerImpl getInstance(){
//        if(instance == null){
//            instance = new RecipeHandlerImpl();
//        }
//        return instance;
//    }
    void init(){
        gson = new GsonBuilder().registerTypeAdapter(Recipe.class, jsonConverter)
                  .serializeNulls().create();
//        applicationConfig = new ApplicationConfig();
//        gsonHandler = new GSONHandler();

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
//    @PostMapping(
//            value = "/recipes", consumes = "application/json", produces = "application/json")
    public Response createRecipe(String gsonPost) throws IOException {
//        JsonReader reader = gsonPost.toString();
        Recipe recipe = gson.fromJson(String.valueOf(gsonPost), Recipe.class);
//        readRecipe(reader, recipe);

        recipe.setId(++currId);
        recipes.put(recipe.getId(), recipe);

        return Response.ok(recipe).build();
    }

    public void readRecipe(JsonReader reader, Recipe recipe) throws IOException {
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                recipe.setId(reader.nextLong());
            } else if (name.equals("name")) {
                recipe.setName(reader.nextString());
            } else if (name.equals("description")) {
                recipe.setDescription(reader.nextString());
            } else if (name.equals("ingredients")) {
                recipe.setIngredients(new StringBuilder(reader.nextString()));
            } else if (name.equals("directions")) {
                recipe.setDirections(reader.nextString());
            } else {
                reader.skipValue();
            }

            recipes.put(recipe.getId(), recipe);
        }
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
