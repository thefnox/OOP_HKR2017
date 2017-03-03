package seminar3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kamai on 3/2/2017.
 */
public class Task3Controller extends TaskController{

    public static String resource = "task3.fxml";

    @FXML
    public Button mainBtn;

    @FXML
    public Button saveBtn;

    @FXML
    public Button loadBtn;

    @FXML
    public Button btn0;

    @FXML
    public Button btn1;

    @FXML
    public Button btn2;

    @FXML
    public Button btn3;

    @FXML
    public Button btn4;

    @FXML
    public Button btn5;

    @FXML
    public Button btn6;

    @FXML
    public Button btn7;

    @FXML
    public TextField field0;

    @FXML
    public TextField field1;

    @FXML
    public TextField field2;

    @FXML
    public TextField field3;

    @FXML
    public TextField field4;

    @FXML
    public TextField field5;

    @FXML
    public TextField field6;

    @FXML
    public TextField field7;

    @FXML
    public Label numberLabel;

    private int _byte = 0;

    @Override
    public void init(URL location, ResourceBundle resources) {
        assert saveBtn != null && loadBtn != null && numberLabel != null && mainBtn != null : "Error loading FXML file";
        mainBtn.setOnAction(event -> {
            Main.loadScene(getClass().getResource(MainController.resource), MainController.name, MainController.width, MainController.height);
        });
        saveBtn.setOnAction(event -> {
            BufferedOutputStream out = null;
            try {
                out = new BufferedOutputStream(new FileOutputStream("task3.bin"));
                out.write(_byte);
                out.close();
                updateNumber();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        loadBtn.setOnAction(event -> {
            try {
                BufferedInputStream in = new BufferedInputStream(new FileInputStream("task3.bin"));
                _byte = in.read();
                in.close();
                updateNumber();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btn0.setOnAction(event -> {
            _byte ^= 1;
            updateNumber();
        });
        btn1.setOnAction(event -> {
            _byte ^= 2;
            updateNumber();
        });
        btn2.setOnAction(event -> {
            _byte ^= 4;
            updateNumber();
        });
        btn3.setOnAction(event -> {
            _byte ^= 8;
            updateNumber();
        });
        btn4.setOnAction(event -> {
            _byte ^= 16;
            updateNumber();
        });
        btn5.setOnAction(event -> {
            _byte ^= 32;
            updateNumber();
        });
        btn6.setOnAction(event -> {
            _byte ^= 64;
            updateNumber();
        });
        btn7.setOnAction(event -> {
            _byte ^= 128;
            updateNumber();
        });
    }

    private void updateNumber(){
        numberLabel.setText("Showing Number: " + _byte + " ");
        field7.setText("" + ((_byte & 128) > 0 ? 1 : 0));
        field6.setText("" + ((_byte & 64) > 0 ? 1 : 0));
        field5.setText("" + ((_byte & 32) > 0 ? 1 : 0));
        field4.setText("" + ((_byte & 16) > 0 ? 1 : 0));
        field3.setText("" + ((_byte & 8) > 0 ? 1 : 0));
        field2.setText("" + ((_byte & 4) > 0 ? 1 : 0));
        field1.setText("" + ((_byte & 2) > 0 ? 1 : 0));
        field0.setText("" + ((_byte & 1) > 0 ? 1 : 0));
    }
}
