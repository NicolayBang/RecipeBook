package com.example.RecipeBook;

import com.google.gson.Gson;


public class JSONConverter  {

    public static String toJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object fromJSON(String json, Class classType) {
        Gson gson = new Gson();
        return gson.fromJson(json, classType);
    }

}
