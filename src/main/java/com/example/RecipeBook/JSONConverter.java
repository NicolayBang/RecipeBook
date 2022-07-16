package com.example.RecipeBook;

import com.google.gson.*;

import java.lang.reflect.Type;
/*

*  */

public class JSONConverter implements JsonDeserializer<Recipe> {

    public static String toJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object fromJSON(String json, Class classType) {
        Gson gson = new Gson();
        return gson.fromJson(json, classType);
    }

    @Override
    public Recipe deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Recipe recipe = new Recipe();
        recipe.setId(jsonObject.get("id").getAsLong());
        recipe.setName(jsonObject.get("name").getAsString());
        recipe.setDescription(jsonObject.get("description").getAsString());
        JsonArray ingredients = jsonObject.get("ingredients").getAsJsonArray();
        for (JsonElement ingredient : ingredients) {
            recipe.addIngredient(ingredient.getAsString());
        }
        recipe.setDirections(jsonObject.get("directions").getAsString());
        return recipe;
    }
}

