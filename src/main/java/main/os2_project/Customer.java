package main.os2_project;

public class Customer implements Runnable{
    private int timesToEat = 1;
    private int SleepTime = 1000;
    final Monitor monitor;
    private int id;
    private int transferToId;
    private float transferAmount;
    public Thread t;

    private Account myAccount;
    private Account theOtherAccount;

    public Customer(int id, int transferToId, float transferAmount ,Monitor m){
        this.id = id;
        this.monitor = m;
//        this.myAccount = myAccount;
//        this.theOtherAccount = theOtherAccount;
        this.transferToId = transferToId;
        this.transferAmount = transferAmount;
        t = new Thread(this);
        t.start();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return "Customer #"+id;
    }

    public void run(){
        int count = 1;
        while(count <= timesToEat){
            monitor.pickUp(id, transferToId, transferAmount);
            System.out.format("Philosopher %d eating (%d times)\n", id, count);
            // Sleep a bit.
            try {
                Thread.sleep(SleepTime);
            } catch (InterruptedException e) {}
            monitor.putDown(id, transferToId);
            System.out.format("Philosopher %d thinking (%d times)\n", id, count);
            ++count;
        }
    }
}
