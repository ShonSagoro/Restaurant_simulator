package com.example.restaurant_simulator.models;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class DinerMonitor {
    private Deque<Diner> queue_wait;
    private Diner enter;
    private int total;

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
    }

    public  Diner generateDinersWait(){
//        while (total == queue_wait.size() ) {
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
        if (total==queue_wait.size()){
            return null;
        }
        Diner diner= new Diner();
        queue_wait.add(diner);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        this.notify();
        System.out.println(this.toString());
        return diner;
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
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notify();
        System.out.println(enter.toString());

    }


    public Diner getEnter() {
        return enter;
    }

    public Diner getNewDiner() {
        return queue_wait.getLast();
    }


}
