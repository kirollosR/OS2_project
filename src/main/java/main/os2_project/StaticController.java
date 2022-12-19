package main.os2_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Float.parseFloat;

public class StaticController implements Initializable {
    private Account[] accounts;
    private Customer[] customers;
    private Monitor monitor;
    @FXML
    private Button startBtn;

    private List<Transfer> transfers= new ArrayList<>();
    @FXML
    void onClickRunAllTests(ActionEvent event) {
        transfers.parallelStream().forEach(transfer -> {
            try {
                transfer.t.start();
                transfer.t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int min = 1000;
        int max = 10000;

        int numOfCustomers = 10;

        accounts = new Account[numOfCustomers];
        for(int i = 0; i < accounts.length;i++){
            accounts[i] = new Account(i, (int)(Math.random() * (max - min + 1) + min));
        }

        monitor = new Monitor(numOfCustomers,accounts);
        customers = new Customer[numOfCustomers];
        for (int i = 0; i < numOfCustomers; i++) {
            customers[i] = new Customer(i);
            Transfer transfer = new Transfer((int)(Math.random() * (numOfCustomers - i)), i, 500, monitor);
            transfers.add(transfer);
        }
    }
}
