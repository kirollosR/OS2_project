package main.os2_project;

public class Transfer implements Runnable {
    private int currentId;
    private int transferToId;
    private float transferAmount;
    public Thread t;
    private int timesToEat = 1;
    private int SleepTime = 2000;
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
        int count = 1;
        while(count <= timesToEat){
            monitor.pickUp(currentId, transferToId, transferAmount);
            System.out.format("Philosopher %d eating (%d times)\n", currentId, count);
            // Sleep a bit.
//            try {
//                Thread.sleep(SleepTime);
//            } catch (InterruptedException e) {}
            monitor.putDown(currentId, transferToId);
            System.out.format("Philosopher %d thinking (%d times)\n", currentId, count);
            ++count;
        }
    }
}
