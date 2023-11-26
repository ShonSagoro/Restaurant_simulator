package com.example.restaurant_simulator.models;

import com.example.restaurant_simulator.models.enums.DinerState;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class ExitMonitor {
    private Deque<Diner> exitQueue;
    private Restaurant restaurant;
    private Diner exit;

    private Deque<Diner> eatingQueue;


    public ExitMonitor( Restaurant restaurant) {
        this.eatingQueue = new LinkedList<Diner>();
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
            System.out.println("SALIR"+this.exitQueue.toString());

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
