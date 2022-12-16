package main.os2_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
    private int numberOfCustomers;
    final Lock lock;
//    private enum States {HUNGRY, THINKING, EATING}
    private enum States {WANT_TO_TRANSFER, TRANSFERING, SLEEPING}
    private States[] state;
    final Condition cond;
    private Account[] accounts;


    public Monitor(int numberOfCustomers, Account[] c){
        this.numberOfCustomers = numberOfCustomers;
        lock = new ReentrantLock(true); // can send boolean with true for the fair to make a queue 3ashan law kaza 7ad 3ayz yem3l lock yestano fe queue
        state = new States[numberOfCustomers];
//        cond = new Condition();
        cond = lock.newCondition();
        accounts = new Account[numberOfCustomers];

        for(int i = 0; i < numberOfCustomers; i++){
            state[i] = States.SLEEPING;
//            cond[i] = lock.newCondition();
        }

        this.accounts = c;
    }

    public void test(int id, int transferToId){
//        if((state[(i+numberOfCustomers-1)%numberOfCustomers] != States.EATING) &&
//                (state[(i+1)%numberOfCustomers] != States.EATING) && state[i] == States.HUNGRY){
//            state[i] = States.EATING;
//            cond[i].signal();
//        }
        if(!accounts[id].isInTransfer() && !accounts[transferToId].isInTransfer() && state[id] == States.WANT_TO_TRANSFER && state[transferToId] != States.TRANSFERING){
//            cond[id].signal();
            state[id] = States.TRANSFERING;
//            cond[transferToId].signal();
            state[transferToId] = States.TRANSFERING;
        }
    }
    public void pickUp(int id, int transferToId, float transferAmount){
        lock.lock();
        try{
            state[id] = States.WANT_TO_TRANSFER;
            test(id, transferToId);
            if(state[id] != States.TRANSFERING){
//                syncQueue.add(cond[id]);
                cond.await();
            } else if (state[transferToId] != States.TRANSFERING) {
//                syncQueue.add(cond[transferToId]);
                cond.await();
            }
            transfer(id, transferToId, transferAmount);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
    private synchronized void transfer(int id, int transferToId, float transferAmount){
//        state[i] = States.EATING;
        accounts[id].take();
        accounts[transferToId].take();
        accounts[id].minusBalance(transferAmount);
        accounts[transferToId].addBalance(transferAmount);
    }
    public void putDown(int id, int transferToId){
        System.out.println(id + " PutDown");
        lock.lock();
        try {
            sleep(id, transferToId);
//            syncQueue.forEach(Condition::signal);
            cond.signalAll();
            // Tell the right neighbor about the possibility to eat.
//            int account1 = (id + numberOfCustomers - 1)%numberOfCustomers;
//            int account2 = (id + numberOfCustomers - 2)%numberOfCustomers;
//            test(account1);
//            test(account2);
        }
        finally {
            lock.unlock();
        }
    }

    public void sleep(int id, int transferToId){
        state[id] = States.SLEEPING;
        state[transferToId] = States.SLEEPING;
        accounts[id].release();
        accounts[transferToId].release();
    }

    public States accountState(int i){
        return state[i];
    }
}
