package com.example.RecipeBook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication//(exclude = {JacksonAutoConfiguration.class})
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);


     // Recipe recipe = gson.fromJson(content, new TypeToken<Employee>(){}.getType());
    }

}
