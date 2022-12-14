package main.os2_project;

public class Account {
    private boolean inTransfer;
    private String name;
    private int id;
    private float balance;

    public Account(int id, float balance){
        this.id = id;
        this.balance = balance;
        this.name = "Account #"+id;
    }

    public float getBalance(){
        return balance;
    }

    public boolean isInTransfer(){
        return inTransfer;
    }

    public void take(){
//        System.out.println(name + " is in use");
        this.inTransfer = true;
    }

    public void release(){
//        System.out.println(name + " is released");
        this.inTransfer = false;
    }

    public String getName(){
        return name;
    }

    public boolean checkBalance(float amount){
        if(balance >= amount){
            return true;
        }
        return false;
    }

    public void minusBalance(float amount){
        balance -= amount;

    }

    public void addBalance(float amount){
        balance += amount;
    }
}
