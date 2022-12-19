package main.os2_project;

import javafx.concurrent.Task;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private List<Transfer> transfers = new ArrayList<>();
    private Monitor monitor;

    @FXML
    void btn1TransferOnClick(ActionEvent event) throws InterruptedException {
        String selected = comboBoxAccounts1.getSelectionModel().getSelectedItem();
        int transferToId = parseInt(String.valueOf(selected.charAt(selected.length() - 1)));
        String amount = textFieldAmount1.getText();
        setBtnTransfer(0, transferToId, amount);
    }

    @FXML
    void btn2TransferOnClick(ActionEvent event) throws InterruptedException {
        String selected = comboBoxAccounts2.getSelectionModel().getSelectedItem();
        int transferToId = parseInt(String.valueOf(selected.charAt(selected.length() - 1)));
        String amount = textFieldAmount2.getText();
        setBtnTransfer(1, transferToId, amount);
    }
    @FXML
    void btn3TransferOnClick(ActionEvent event) throws InterruptedException {
        String selected = comboBoxAccounts3.getSelectionModel().getSelectedItem();
        int transferToId = parseInt(String.valueOf(selected.charAt(selected.length() - 1)));
        String amount = textFieldAmount3.getText();
        setBtnTransfer(2, transferToId, amount);
    }
    @FXML
    public void startTransferOnClick(ActionEvent actionEvent) {
        transfers.parallelStream().forEach(transfer -> {
            try {
                transfer.t.start();
                transfer.t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        transfers.clear();
        updateCustomerAmount(0);
        updateCustomerAmount(1);
        updateCustomerAmount(2);
    }
    private void setBtnTransfer(int customerId, int transferToId, String amount) throws InterruptedException {
        float amountFloat = parseFloat(amount);
        if(amountFloat < 0) {
            updateCustomerMessage(customerId, "Please enter amount more than 0");
            return;
        }
        Transfer transfer = new Transfer(customers[customerId].getId(), transferToId, amountFloat, monitor);
        transfers.add(transfer);
    }

    private void updateCustomerAmount(int customerId) {
        if(customerId == 0) {
            balance1.setText("Balance: " + accounts[0].getBalance());
        } else if(customerId == 1) {
            balance2.setText("Balance: " + accounts[1].getBalance());
        } else  {
            balance3.setText("Balance: " + accounts[2].getBalance());
        }
    }

    public void updateCustomerMessage(int customerId, String message) {
        if(customerId == 0) {
            labelTransferStatus1.setText(message);
        } else if(customerId == 1) {
            labelTransferStatus2.setText(message);
        } else  {
            labelTransferStatus3.setText(message);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int min = 1000;
        int max = 10000;

        int numOfCustomers = 3;

        accounts = new Account[numOfCustomers];
        for(int i = 0; i < accounts.length;i++){
            accounts[i] = new Account(i, (int)(Math.random() * (max - min + 1) + min));
        }

        monitor = new Monitor(numOfCustomers,accounts);
        customers = new Customer[numOfCustomers];
        for (int i = 0; i < numOfCustomers; i++) {
            customers[i] = new Customer(i);
        }

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
