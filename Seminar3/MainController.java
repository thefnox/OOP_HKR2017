package seminar3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static String resource = "main.fxml";

    public static String name = "Seminar 3";

    public static int width = 300;

    public static int height = 275;

    @FXML
    private Button task1Btn;
    @FXML
    private Button task2Btn;
    @FXML
    private Button task3Btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert task1Btn != null && task2Btn != null && task3Btn != null : "Error loading main FXML file";

        task1Btn.setOnAction(event -> {
            Main.loadScene(getClass().getResource(Task1Controller.resource), Task1Controller.title, Task1Controller.width, Task1Controller.height);
        });
        task2Btn.setOnAction(event -> {
            Main.loadScene(getClass().getResource(Task2Controller.resource), Task2Controller.title, Task2Controller.width, Task2Controller.height);
        });
        task3Btn.setOnAction(event -> {
            Main.loadScene(getClass().getResource(Task3Controller.resource), Task3Controller.title, Task3Controller.width, Task3Controller.height);
        });
    }
}
