package com.example.RecipeBook;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


@XmlRootElement(name="Recipe")
public class Recipe {
    private long id;
    private String name;
    private String description;
    private StringBuilder ingredients;
    private String directions;

//    public Recipe() {
//    }
    public StringBuilder getIngredients () {return ingredients;}

    public void addIngredient(String ingredient) {
        if (ingredients == null) {
            ingredients = new StringBuilder();
        }
        ingredients.append(ingredient);
    }



    public void setIngredients(StringBuilder ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}
