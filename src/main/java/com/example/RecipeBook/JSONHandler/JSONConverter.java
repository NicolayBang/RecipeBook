package com.example.RecipeBook.JSONHandler;

import com.example.RecipeBook.Recipe;
import com.google.gson.*;

import java.lang.reflect.Type;
/*

 *  */

public class JSONConverter implements JsonDeserializer<Recipe> {

    @Override
    public Recipe deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Recipe recipe = new Recipe();
        if (jsonObject.get("id") != null) {
            recipe.setId(jsonObject.get("id").getAsLong());
        }
        recipe.setName(jsonObject.get("name").getAsString());
        recipe.setDescription(jsonObject.get("description").getAsString());
        recipe.setIngredients(new StringBuilder(jsonObject.get("ingredients").getAsString()));
        recipe.setDirections(jsonObject.get("directions").getAsString());
        return recipe;
    }
}

