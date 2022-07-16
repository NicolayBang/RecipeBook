package com.example.RecipeBook.ThreadController;

import com.example.RecipeBook.RecipeHandler;
import com.example.RecipeBook.RecipeHandlerThread;

import javax.ws.rs.core.Response;
import java.io.IOException;

@org.springframework.web.bind.annotation.RestController

public class RecipeHandlerImpl implements RestListener, RecipeHandler {


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
