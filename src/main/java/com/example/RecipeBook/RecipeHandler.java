package com.example.RecipeBook;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes( {MediaType.APPLICATION_JSON} )
@Path("/recipe_handler")
public interface RecipeHandler {

    @Path("/recipes")
    @GET
    String getRecipes();

    @Path("/recipe/{id}")
    @GET
    String getRecipe(@PathParam("id") Long id);
//
    @Path("/recipes")
    @POST
@PostMapping(
        value = "/recipes", consumes = "application/json", produces = "application/json")
    Response createRecipe(Gson gsonPost) throws IOException;

    @Path("/recipes")
    @PUT
    Response updateRecipe(Recipe recipe);

    @Path("/recipe/{id}")
    @DELETE
    Response deleteRecipe(@PathParam("id") Long id);

    /*
   GET recipes: localhost:8080/recipe_book/services/recipe_handler/recipes
   GET recipe: localhost:8080/recipe_book/services/recipe_handler/recipe/1
   POST --> localhost:8080/recipe_book/services/recipe_handler/recipes

GET books: localhost:8080/a2/services/bookhandler/books
GET book: localhost:8080/a2/services/bookhandler/book/123
POST --> localhost:8080/a2/services/bookhandler/books
--> <Book>
		<title>Lord of The Rings</title>
	</Book>
PUT:localhost:8080/a2/services/bookhandler/books
    <Book>
        <id>124</id>
        <title>Lord of The Rings: The Two Towers</title>
    </Book>
*/


}
