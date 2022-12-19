package main.os2_project;

public class Transfer implements Runnable {
    private int currentId;
    private int transferToId;
    private float transferAmount;
    public Thread t;
    final Monitor monitor;


    public Transfer(int id, int transferToId, float transferAmount, Monitor monitor) {
        this.currentId = id;
        this.transferToId = transferToId;
        this.transferAmount = transferAmount;
        this.monitor = monitor;
        t = new Thread(this);
        System.out.println("Transfer: " + this.currentId);
        System.out.println("Transfer To: " + this.transferToId);
    }

    @Override
    public void run() {
            monitor.pickUp(currentId, transferToId, transferAmount);
            monitor.putDown(currentId, transferToId);
    }
}
