package com.example.restaurant_simulator.models;

import com.example.restaurant_simulator.models.enums.DinerState;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class DinerMonitor {
    private Deque<Diner> queue_wait;
    private Restaurant restaurant;
    private Diner enter;
    private int total;
    private int id;

    @Override
    public String toString() {
        return "DinerMonitor{" +
                "queue_wait=" + queue_wait +
                ", total=" + total +
                '}';
    }

    public DinerMonitor(int total, Restaurant restaurant){
        this.queue_wait= new LinkedList<Diner>();
        this.restaurant=restaurant;
        this.enter=null;
        this.total=total;
        this.id=20;
    }

    public synchronized void generateDinersWait(){
        while (total == queue_wait.size()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Diner diner= new Diner(id);
        this.id++;
        queue_wait.add(diner);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notifyAll();
    }

    public synchronized void extractDinersWait(){
        while (queue_wait.size() == 0 || restaurant.isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.enter=this.queue_wait.getFirst();
        this.enter.setState(DinerState.SIT_WITHOUT_ORDER);
        this.restaurant.setData(this.getEnter());
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.queue_wait.removeFirst();
        this.notifyAll();
    }

    public Diner getEnter() { return this.enter; }

    public Deque<Diner> getQueue_wait() {return queue_wait;}

}
