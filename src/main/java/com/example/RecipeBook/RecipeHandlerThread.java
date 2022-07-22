package com.example.RecipeBook;

import com.example.RecipeBook.Data.MockDatabase;
import com.example.RecipeBook.JSONHandler.JSONConverter;
import com.example.RecipeBook.ThreadController.RecipeHandlerImpl;
import com.example.RecipeBook.ThreadController.RestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;


/**
 * TODO Make this a singleton class for implementing multithreaded access to the database.
 * TODO implement this solution for POST request https://howtodoinjava.com/jersey/jax-rs-gson-example/
 */
@Service
public class RecipeHandlerThread extends Thread implements RestListener {

    //Map<Long, Recipe> recipes = new HashMap<>();

    RecipeHandlerImpl recipeHandler;



    public RecipeHandlerThread() {
       recipeHandler = RecipeHandlerImpl.getInstance();
    }


    //@Override
    public String getRecipes() {
        Collection<Recipe> results = RecipeHandlerImpl.getInstance().getMockDatabase().recipes.values();
        List<Recipe> response = new ArrayList<>(results);
        return recipeHandler.getMockDatabase().gson.toJson(response);
    }

   // @Override
    public String getRecipe(Long id) {
        return recipeHandler.getMockDatabase().gson.toJson(RecipeHandlerImpl.getInstance().getMockDatabase().recipes.get(id));
    }

   // @Override
    public Response createRecipe(String gsonPost) throws IOException {

        Recipe recipe = recipeHandler.getMockDatabase().gson.fromJson(String.valueOf(gsonPost), Recipe.class);
        recipeHandler.getMockDatabase().currId++;
        recipe.setId( recipeHandler.getMockDatabase().currId);
        RecipeHandlerImpl.getInstance().getMockDatabase().recipes.put(recipe.getId(), recipe);
        return Response.ok(recipe).build();
    }
    public Response updateRecipe(String gsonPut) {
        Recipe recipe = recipeHandler.getMockDatabase().gson.fromJson(String.valueOf(gsonPut), Recipe.class);
        Recipe currRecipe = RecipeHandlerImpl.getInstance().getMockDatabase().recipes.get(recipe.getId());
        Response response;
        if (currRecipe != null) {
            RecipeHandlerImpl.getInstance().getMockDatabase().recipes.put(recipe.getId(), recipe);
            response = Response.ok().build();
        } else {
            response = Response.notModified().build();
        }
        return response;
    }

   // @Override
    public Response deleteRecipe(Long id) {
        Recipe recipe = RecipeHandlerImpl.getInstance().getMockDatabase().recipes.get(id);

        Response response;
        if (recipe != null) {
            RecipeHandlerImpl.getInstance().getMockDatabase().recipes.remove(id);
            response = Response.ok().build();
        } else {
            response = Response.notModified().build();
        }
        return response;
    }
}
