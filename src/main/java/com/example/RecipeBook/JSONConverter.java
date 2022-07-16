package com.example.RecipeBook;

import com.google.gson.*;

import java.lang.reflect.Type;
/*
* Implement this solution: https://www.codeproject.com/Tips/1159537/Simple-JSON-REST-Consumption-with-GSON-API
*
* public class JsonDeserializerEmployee implements JsonDeserializer<Employee>{

	public Employee deserialize(JsonElement json, Type typeOfT,
	JsonDeserializationContext context) throws JsonParseException {

	    JsonObject employeeJson = json.getAsJsonObject();
	    int id = employeeJson.get("id").getAsInt();
	    String firstName = employeeJson.get("first_name").getAsString();
	    String lastName = employeeJson.get("last_name").getAsString();
	    Date date = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a", Locale.ENGLISH);
			date = sdf.parse(employeeJson.get("date").getAsString() );
		} catch (ParseException e) {
			e.printStackTrace();
		}

	    String photoPath = employeeJson.get("photo").getAsString();
	    boolean married =employeeJson.get("married").getAsBoolean();

	    Employee employee = new Employee();
	    employee.setId(id);
	    employee.setFirstName(firstName);
	    employee.setLastName(lastName);
	    employee.setDate(date);
	    employee.setPhoto(photoPath);
	    employee.setMarried(married);

	    return employee;
	}
}
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

