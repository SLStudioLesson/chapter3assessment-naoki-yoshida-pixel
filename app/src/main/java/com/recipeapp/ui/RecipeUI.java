package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.recipeapp.datahandler.*;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;
    private ArrayList<Recipe> recipes;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    public void displayMenu() { 

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        System.out.println("Adding a new recipe.");
                        System.out.print("Enter recipe name: ");
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() { ////DataHandlerから読み込んだレシピデータを整形してコンソールに表示する
        try {
            recipes = dataHandler.readData();
            if (recipes != null) {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for (Recipe a : recipes) {
                    System.out.println("Recipe Name: " + a.getName());
                    System.out.print("Main Ingredients: ");
                    String ingredientsName = "";
                    for (Ingredient b : a.getIngredients()) {
                        ingredientsName += b.getName();
                        ingredientsName += ",";
                    }
                    String ingredientsName2 = ingredientsName.substring(0, ingredientsName.length() - 1);
                    System.out.print(ingredientsName2);
                    System.out.print("\n");
                    System.out.println("-----------------------------------");
                }
            } else {
                System.out.println("No recipes available.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    private void addNewRecipe() {
        try {
            String recipeName;
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            // ユーザーからレシピ名と主な材料を入力させます
            System.out.print("Enter recipe name: ");
            recipeName = reader.readLine();
            System.out.print("Enter ingredients (type 'done' when finished): ");
            System.out.print("\n");
            while (true) {
                System.out.print("Ingredient: ");
                String ingredientName = reader.readLine();
                if (ingredientName.equals("done")) {
                    break;
                } else {
                    ingredients.add(new Ingredient(ingredientName));
                }
            }
            Recipe recipe = new Recipe(recipeName, ingredients);// writeDataを使用してrecipes.csvに新しいレシピを追加します。
            CSVDataHandler csv = new CSVDataHandler();
            csv.writeData(recipe);
            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
