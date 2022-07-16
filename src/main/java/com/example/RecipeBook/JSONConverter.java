package com.example.RecipeBook;

import com.google.gson.*;

import java.lang.reflect.Type;
/*

*  */

public class JSONConverter implements JsonDeserializer<Recipe> {

    static RecipeHandlerImpl recipeHandler;

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
//        recipeHandler = RecipeHandlerImpl.getInstance();
//        RecipeHandlerImpl recipeHandler = RecipeHandlerImpl.getInstance();

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Recipe recipe = new Recipe();

       if(jsonObject.get("id") != null) {
           recipe.setId(jsonObject.get("id").getAsLong());
       }else{
           recipe.setId(recipeHandler.currId);} //TODO change this to increment id

        recipe.setName(jsonObject.get("name").getAsString());
        recipe.setDescription(jsonObject.get("description").getAsString());
        recipe.setIngredients(new StringBuilder(jsonObject.get("ingredients").getAsString()));
        recipe.setDirections(jsonObject.get("directions").getAsString());
        return recipe;
    }
}

