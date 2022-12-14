package main.os2_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    private Button btnDynamic;

    @FXML
    private Button btnStatic;

    @FXML
    void onClickDynamic(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnStatic.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dynamic-page.fxml")));
        primaryStage.setTitle("Dynamic Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void onClickStatic(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnStatic.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("static-page.fxml")));
        primaryStage.setTitle("Static Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
