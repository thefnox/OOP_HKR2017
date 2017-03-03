package seminar3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by kamai on 3/2/2017.
 */
public class Task2Controller extends TaskController {

    public static String resource = "task2.fxml";

    @FXML
    public Button mainBtn;

    @FXML
    public Button withdrawBtn;

    @FXML
    public Button depositBtn;

    @FXML
    public TextField amountField;

    @FXML
    public TextField numberField;

    @FXML
    public TextField ownerField;

    private BankAccount account;

    @Override
    public void init(URL location, ResourceBundle resources) {
        assert withdrawBtn != null && depositBtn != null && mainBtn != null && amountField != null && numberField != null && ownerField != null : "Error loading FXML file";
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("task2.txt"));
            account = (BankAccount) in.readObject();
            ownerField.setText(account.ownerName);
            numberField.setText("" + account.accountNumber);
            in.close();
        } catch (FileNotFoundException e) {
            account = new BankAccount();
        } catch (IOException e) {
            account = new BankAccount();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            account = new BankAccount();
            e.printStackTrace();
        }
        finally
        {
            amountField.setText(account.amount + " SEK");
        }
        mainBtn.setOnAction(event -> {
            Main.loadScene(getClass().getResource(MainController.resource), MainController.name, MainController.width, MainController.height);
        });
        withdrawBtn.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("0");
            dialog.setTitle("Withdrawals");
            dialog.setHeaderText("");
            dialog.setContentText("Enter amount to withdraw (Max:" + account.amount + "):");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(amount -> {
                if (amount != "" && !amount.matches("\\d+")) {
                    showError("Amount must be a number!");
                }
                else{
                    int n = Integer.parseInt(amount);
                    if (n <= 0){
                        showError("Amount must be greater than 0!");
                    }
                    else if (n > account.amount){
                        showError("Amount must be less than " + account.amount + "!");
                    }
                    else{
                        account.amount += n;
                        saveAccount();
                    }
                }
            });
        });
        depositBtn.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("0");
            dialog.setTitle("Deposits");
            dialog.setHeaderText("");
            dialog.setContentText("Enter amount to deposit:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(amount -> {
                if (amount != "" && !amount.matches("\\d+")) {
                    showError("Amount must be a number!");
                }
                else{
                    int n = Integer.parseInt(amount);
                    if (n <= 0){
                        showError("Amount must be greater than 0!");
                    }
                    else{
                        account.amount += n;
                        saveAccount();
                    }
                }
            });
        });
        ownerField.focusedProperty().addListener((arg, oldValue, newValue) -> {
            if (!newValue){
                //Focus lost
                String text = ownerField.getText();
                if (text != "" && !text.matches("[a-zA-Z]+")) {
                    showError("Input must be a name!");
                    ownerField.setText("");
                }
                account.ownerName = ownerField.getText();
            }
        });
        numberField.focusedProperty().addListener((arg, oldValue, newValue) -> {
            if (!newValue){
                //Focus lost
                String text = numberField.getText();
                if (text != "" && !text.matches("\\d+")) {
                    showError("Input must be a number!");
                    numberField.setText("");
                }
                account.accountNumber = Integer.parseInt(numberField.getText());
            }
        });
    }

    private void saveAccount(){
        amountField.setText(account.amount + " SEK");
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("task2.txt"));
            writer.writeObject(account);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
