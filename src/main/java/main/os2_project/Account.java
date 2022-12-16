package main.os2_project;

public class Account {
    private boolean inTransfer;
    private String name;
    private int id;
    private float balance;
    private int SleepTime = 1000;


    public Account(int id, float balance){
        this.id = id;
        this.balance = balance;
        this.name = "Account #"+id;
    }

    public synchronized float getBalance(){
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

    public synchronized boolean minusBalance(float amount){
        if(balance < amount) {
            System.out.println("Insufficient funds!");
            return false;
        }
        balance -= amount;
        try {
            Thread.sleep(SleepTime);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("after withdrawl balance = $" + balance);
        return true;
    }

    public synchronized void addBalance(float amount){
        balance += amount;
        try {
            Thread.sleep(SleepTime);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("after deposit balance = $" + balance);
    }
}
