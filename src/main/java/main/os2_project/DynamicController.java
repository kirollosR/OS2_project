package main.os2_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DynamicController implements Initializable {

    @FXML
    private Button btnTransfer;

    @FXML
    private Button btnTransfer1;

    @FXML
    private Button btnTransfer11;
    @FXML
    private Label profileName1;

    @FXML
    private ComboBox<?> comboBoxAccounts;

    @FXML
    private ComboBox<?> comboBoxAccounts1;

    @FXML
    private ComboBox<?> comboBoxAccounts2;

    @FXML
    private Label labelTransferStatus;

    @FXML
    private Label labelTransferStatus1;

    @FXML
    private Label labelTransferStatus11;

    @FXML
    private TextField textFieldAmount;

    @FXML
    private TextField textFieldAmount1;

    @FXML
    private TextField textFieldAmount11;


    @FXML
    void btnTransferOnClick(ActionEvent event) {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileName1.setText("Kirollos Rafik");
    }

}
