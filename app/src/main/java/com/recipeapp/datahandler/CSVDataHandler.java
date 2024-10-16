package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.recipeapp.model.*;

public class CSVDataHandler implements DataHandler {

    private String filename = "app/src/main/resources/recipes.csv";
    private String filePath = "";
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public CSVDataHandler() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                filePath += line;
                filePath += "\n";
            }
            reader.close();
            // IOExceptionが発生したときはError reading file: 例外のメッセージとコンソールに表示します。
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    public String getMode() {
        return "CSV";
    }

    public ArrayList<Recipe> readData() throws IOException { //recipes.csvからレシピデータを読み込み、それをリスト形式で返します
        if (!filePath.isEmpty()) {
            String g[] = filePath.split("\n");
            for (String a : g) {
                String b[] = a.split(",");
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1; i < b.length; i++) {
                    Ingredient ingredient = new Ingredient(b[i]);
                    ingredients.add(ingredient);
                }
                Recipe recipe = new Recipe(b[0], ingredients);
                recipes.add(recipe);
            }
            return this.recipes;
        } else {
            return null;
        }
    }

    public void writeData(Recipe recipe) throws IOException {
        String filename2 = filename;
        File dataFile = new File(filename2);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile,true))){
            writer.write(recipe.getName()+",");
            String i = "";
            for(Ingredient a : recipe.getIngredients()){
                i += a.getName();
                i += ",";
            }    //レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
            String f = i.substring(0,i.length()-1);
            writer.write(f);
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage()); //IOExceptionが発生したときはError reading file: 例外のメッセージとコンソールに表示します。
        }
    }

    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}
