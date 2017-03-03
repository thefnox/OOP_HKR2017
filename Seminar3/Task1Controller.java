package seminar3;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kamai on 3/2/2017.
 */
public class Task1Controller extends TaskController {

    public static String resource = "task1_1.fxml";

    @FXML
    public TextArea textArea;

    @FXML
    public Button mainBtn;

    @FXML
    public Button okBtn;

    @FXML
    public Button showBtn;

    @FXML
    public Button backBtn;

    @FXML
    public TextField idField;

    @FXML
    public TextField timeField;

    @Override
    public void init(URL location, ResourceBundle resources) {
        if (okBtn != null){
            okBtn.setOnAction(event -> {
                if (idField.getText().length() < 1 || timeField.getText().length() < 1){
                    showError("ID or time cannot be empty!");
                }
                else
                {
                    int id = Integer.parseInt(idField.getText());
                    String time = timeField.getText();
                    String line = String.format("Car %d,\t Time: %s\t - ", id, time);
                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter("task1.txt", true));
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        if (writer != null){
                            try {
                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    idField.setText("");
                    timeField.setText("");
                    showNotify("Car added successfully");
                }
            });
        }
        if (mainBtn != null){
            mainBtn.setOnAction(event -> {
                Main.loadScene(getClass().getResource(MainController.resource), MainController.name, MainController.width, MainController.height);
            });
        }
        if (backBtn != null){
            backBtn.setOnAction(event -> {
                Main.loadScene(getClass().getResource("task1_1.fxml"), title, width, height);
            });
        }
        if (showBtn != null){
            showBtn.setOnAction(event -> {
                Main.loadScene(getClass().getResource("task1_2.fxml"), "Task 1 Results", width, height);
            });
        }
        if (idField != null){
            idField.focusedProperty().addListener((arg, oldValue, newValue) -> {
                if (!newValue){
                    //Focus lost
                    String text = idField.getText();
                    if (text != "" && !text.matches("\\d+")) {
                        showError("Input must be a number!");
                        idField.setText("");
                    }
                }
            });
        }
        if (timeField != null){
            timeField.focusedProperty().addListener((arg, oldValue, newValue) -> {
                if (!newValue){
                    //Focus lost
                    String text = timeField.getText();
                    if (text != "" && !text.matches("[0-5][0-9]:[0-5][0-9]")) {
                        showError("Input must be a timestamp (00:00)!");
                        timeField.setText("");
                    }
                }
            });
        }
        if (textArea != null){
            String text = "";
            try {
                BufferedReader reader = new BufferedReader(new FileReader("task1.txt"));
                String line;
                while((line = reader.readLine()) != null){
                    text += line;
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (text == "") text = "No results.";
            textArea.setText(text);
        }
    }
}
