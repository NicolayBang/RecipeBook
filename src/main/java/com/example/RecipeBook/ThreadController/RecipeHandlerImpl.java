package com.example.RecipeBook.ThreadController;

import com.example.RecipeBook.Data.MockDatabase;
import com.example.RecipeBook.Recipe;
import com.example.RecipeBook.RecipeHandler;
import com.example.RecipeBook.RecipeHandlerThread;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.core.Response;
import java.io.IOException;

@org.springframework.web.bind.annotation.RestController

public class RecipeHandlerImpl implements RestListener, RecipeHandler {

private final MockDatabase mockDatabase = new MockDatabase();
private static final RecipeHandlerImpl recipeHandler = new RecipeHandlerImpl();

    public static RecipeHandlerImpl getInstance() {
        return recipeHandler;
    }

    public  MockDatabase getMockDatabase() {
        return mockDatabase;
    }


    @Override
    public String getRecipes() {
        RecipeHandlerThread thread = new RecipeHandlerThread();
        thread.start();
        return thread.getRecipes();
    }

    @Override
    public String getRecipe(Long id) {
        RecipeHandlerThread thread = new RecipeHandlerThread();
        thread.start();
        return thread.getRecipe(id);
    }

    @Override
    public Response createRecipe(String gsonPost) throws IOException {
        RecipeHandlerThread thread = new RecipeHandlerThread();
        thread.start();
        return thread.createRecipe(gsonPost);
    }

    @Override
    public Response updateRecipe(String gsonPut) {
        RecipeHandlerThread thread = new RecipeHandlerThread();
        thread.start();
        return thread.updateRecipe(gsonPut);
    }

    @Override
    public Response deleteRecipe(Long id) {
        RecipeHandlerThread thread = new RecipeHandlerThread();
        thread.start();
        return thread.deleteRecipe(id);
    }
}
