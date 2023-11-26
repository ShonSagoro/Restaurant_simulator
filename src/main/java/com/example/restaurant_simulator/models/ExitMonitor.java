package com.example.restaurant_simulator.models;

import com.example.restaurant_simulator.models.enums.DinerState;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class ExitMonitor {
    private Deque<Diner> exitQueue;
    private Restaurant restaurant;
    private Diner exit;

    public ExitMonitor( Restaurant restaurant) {
        this.exitQueue = new LinkedList<Diner>();
        this.restaurant=restaurant;
    }
    public synchronized void extractDinersExit(){
        while (exitQueue.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.exit=this.exitQueue.getFirst();
        this.exit.setState(DinerState.EXIT);
        restaurant.removeDinnerByTableId(exit.getTableId());
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notifyAll();
    }

    public synchronized void passToExitQueue(){
        while (exitQueue.size()==20) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            Diner dinerOrder=restaurant.getDinnerByState(DinerState.EAT_FINISH);
            if(dinerOrder!=null){
                dinerOrder.setState(DinerState.WAIT_ORDER);
                this.exitQueue.add(dinerOrder);
            }
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notifyAll();
    }

    public Diner removeFromExitQueue(){
        return this.exitQueue.remove();
    }
}