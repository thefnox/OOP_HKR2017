package seminar3;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kamai on 3/2/2017.
 */
public abstract class TaskController implements Initializable {

    public static int width = 600;

    public static int height = 480;

    public static String resource;

    public static String title = "Task";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init(location, resources);
    }

    public abstract void init(URL location, ResourceBundle resources);

    public void showError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void showNotify(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("");
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
