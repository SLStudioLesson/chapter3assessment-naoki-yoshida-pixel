import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // ファイル形式を選択する
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();
            if(choice.equals("1")){// ファイル形式選択メニューで "1"を入力した場合
                DataHandler csv1 = new CSVDataHandler();
                RecipeUI recipe1 = new RecipeUI(csv1);
                recipe1.displayMenu();
            } else if(choice.equals("2")){// ファイル形式選択メニューで "2"を入力した場合
                DataHandler json = new JSONDataHandler();
                RecipeUI recipe2 = new RecipeUI(json);
                recipe2.displayMenu();
            } else{// ファイル形式選択メニューで "2"以外を入力した場合
                DataHandler csv2 = new CSVDataHandler();
                RecipeUI recipe3 = new RecipeUI(csv2);
                recipe3.displayMenu();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}