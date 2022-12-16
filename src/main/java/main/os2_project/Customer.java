package main.os2_project;

public class Customer {
    private int id;
    private Transfer transfer;
    private Account myAccount;
    private Account theOtherAccount;

    public Customer(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return "Customer #"+id;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }
}
