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

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

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
    private Label profileName2;
    @FXML
    private Label profileName3;
    @FXML
    private Label balance1;
    @FXML
    private Label balance2;
    @FXML
    private Label balance3;

    @FXML
    private ComboBox<String> comboBoxAccounts1;

    @FXML
    private ComboBox<String> comboBoxAccounts2;

    @FXML
    private ComboBox<String> comboBoxAccounts3;

    @FXML
    private Label labelTransferStatus1;

    @FXML
    private Label labelTransferStatus2;

    @FXML
    private Label labelTransferStatus3;

    @FXML
    private TextField textFieldAmount1;

    @FXML
    private TextField textFieldAmount2;

    @FXML
    private TextField textFieldAmount3;

    private Account[] accounts;
    private Customer[] customers;
    private Monitor monitor;

    @FXML
    void btn1TransferOnClick(ActionEvent event) throws InterruptedException {
        String selected = comboBoxAccounts1.getSelectionModel().getSelectedItem();
        int transferToId = parseInt(String.valueOf(selected.charAt(selected.length() - 1)));
//        labelTransferStatus1.setText(transferToId + "");
        String amount = textFieldAmount1.getText();
        float amountFloat = parseFloat(amount);
        customers[0] = new Customer(0, transferToId, amountFloat, monitor);
        customers[0].t.join();
        balance1.setText("Balance: " + accounts[0].getBalance());
    }

    @FXML
    void btn2TransferOnClick(ActionEvent event) throws InterruptedException {
        String selected = comboBoxAccounts2.getSelectionModel().getSelectedItem();
        int transferToId = parseInt(String.valueOf(selected.charAt(selected.length() - 1)));

//        labelTransferStatus1.setText(transferToId + "");
        customers[1] = new Customer(1, transferToId, 0, monitor);
        customers[1].t.join();
        balance2.setText("Balance: " + accounts[1].getBalance());
    }
    @FXML
    void btn3TransferOnClick(ActionEvent event) throws InterruptedException {
        String selected = comboBoxAccounts1.getSelectionModel().getSelectedItem();
        int transferToId = parseInt(String.valueOf(selected.charAt(selected.length() - 1)));
//        labelTransferStatus1.setText(transferToId + "");
        customers[2] = new Customer(2, transferToId, 0, monitor);
        customers[2].t.join();
        balance3.setText("Balance: " + accounts[2].getBalance());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        profileName1.setText("Kirollos Rafik");
        int min = 1000;
        int max = 10000;

        int numOfCustomers = 3;

        accounts = new Account[numOfCustomers];
        for(int i = 0; i < accounts.length;i++){
            accounts[i] = new Account(i, (int)(Math.random() * (max - min + 1) + min));
        }

        monitor = new Monitor(numOfCustomers,accounts);
        customers = new Customer[numOfCustomers];

//        for(int i = 0; i < numOfCustomers; i++)
//            customers[i] = new Customer(i,accounts[i],accounts[(i+numOfCustomers-1)%numOfCustomers],monitor);
//
//        for(int i = 0; i < numOfCustomers; i++)
//            try {
//                customers[i].t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        System.out.println("");
        System.out.println("Dinner is over!");

        //set profile name labels
        profileName1.setText("Customer #" + customers[0].getId());
        profileName2.setText("Customer #" + customers[1].getId());
        profileName3.setText("Customer #" + customers[2].getId());

        //set balance labels
        balance1.setText("Balance: " + accounts[0].getBalance());
        balance2.setText("Balance: " + accounts[1].getBalance());
        balance3.setText("Balance: " + accounts[2].getBalance());

        //set combo box items
        comboBoxAccounts1.getItems().addAll(customers[1].getName(),customers[2].getName());
        comboBoxAccounts2.getItems().addAll(customers[0].getName(),customers[2].getName());
        comboBoxAccounts3.getItems().addAll(customers[0].getName(),customers[1].getName());
    }

}
