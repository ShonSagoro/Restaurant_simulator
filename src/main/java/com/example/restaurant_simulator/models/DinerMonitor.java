package com.example.restaurant_simulator.models;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class DinerMonitor {
    private Deque<Diner> queue_wait;
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

    public DinerMonitor(int total){
        this.queue_wait= new LinkedList<Diner>();
        this.total=total;
        this.id=1;
    }

    public synchronized void generateDinersWait(){
        while (total == queue_wait.size() ) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Diner diner= new Diner(1);
        this.id++;
        queue_wait.add(diner);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(4000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notifyAll();
        System.out.println(this.toString());
    }

    public synchronized void extractDinersWait(){
        while (queue_wait.size() == 0 ) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.enter=this.queue_wait.remove();
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notifyAll();
        System.out.println(enter.toString());

    }


    public Diner getEnter() {
        return enter;
    }

    public Diner getNewDiner() {
        return queue_wait.getLast();
    }


}
