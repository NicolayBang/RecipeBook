package com.example.RecipeBook;

import com.example.RecipeBook.JSONHandler.JSONConverter;
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

    Map<Long, Recipe> recipes = new HashMap<>();
    long currId = 1;
    JSONConverter jsonConverter = new JSONConverter();
    Gson gson;



    //@Override
    public String getRecipes() {
        Collection<Recipe> results = recipes.values();
        List<Recipe> response = new ArrayList<>(results);
        return gson.toJson(response);
    }

   // @Override
    public String getRecipe(Long id) {
        return gson.toJson(recipes.get(id));
    }

   // @Override
    public Response createRecipe(String gsonPost) throws IOException {
        Recipe recipe = gson.fromJson(String.valueOf(gsonPost), Recipe.class);
        recipe.setId(++currId);
        recipes.put(recipe.getId(), recipe);
        return Response.ok(recipe).build();
    }

//    public void readRecipe(JsonReader reader, Recipe recipe) throws IOException {
//        while (reader.hasNext()) {
//            String name = reader.nextName();
//            if (name.equals("id")) {
//                recipe.setId(reader.nextLong());
//            } else if (name.equals("name")) {
//                recipe.setName(reader.nextString());
//            } else if (name.equals("description")) {
//                recipe.setDescription(reader.nextString());
//            } else if (name.equals("ingredients")) {
//                recipe.setIngredients(new StringBuilder(reader.nextString()));
//            } else if (name.equals("directions")) {
//                recipe.setDirections(reader.nextString());
//            } else {
//                reader.skipValue();
//            }
//
//            recipes.put(recipe.getId(), recipe);
//        }
//    }

    //@Override
    public Response updateRecipe(String gsonPut) {
        Recipe recipe = gson.fromJson(String.valueOf(gsonPut), Recipe.class);
        Recipe currRecipe = recipes.get(recipe.getId());
        Response response;
        if (currRecipe != null) {
            recipes.put(recipe.getId(), recipe);
            response = Response.ok().build();
        } else {
            response = Response.notModified().build();
        }
        return response;
    }

   // @Override
    public Response deleteRecipe(Long id) {
        Recipe recipe = recipes.get(id);

        Response response;
        if (recipe != null) {
            recipes.remove(id);
            response = Response.ok().build();
        } else {
            response = Response.notModified().build();
        }
        return response;
    }
}
