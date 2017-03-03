package seminar3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        loadScene(getClass().getResource(MainController.resource), MainController.name, MainController.width, MainController.height);
    }

    public static void loadScene(URL resourceFile, String title, int width, int height){
        Parent root = null;
        try {
            root = FXMLLoader.load(resourceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
