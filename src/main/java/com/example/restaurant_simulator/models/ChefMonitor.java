package com.example.restaurant_simulator.models;

import com.example.restaurant_simulator.models.enums.DinerState;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class ChefMonitor {
    private Deque<Diner> commands;

    private Deque<Diner> orders;

    private Restaurant restaurant;

    private int TOTAL;

    public ChefMonitor(Restaurant restaurant){
        this.commands=new LinkedList<Diner>();
        this.orders=new LinkedList<Diner>();
        this.restaurant=restaurant;
        this.TOTAL=20;

    }
    public synchronized void makeTheOrder() {
        while (commands.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("chambeando");

        Diner diner=commands.remove();
                diner.setState(DinerState.EAT);
                diner.setTime(getRandomEatTime());
                orders.add(diner);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        System.out.println("Listo perro");

        this.notifyAll();

    }

    public int getRandomEatTime() {
        return  (int)(Math.random() * (10 - 5)) + 5;
    }

    public synchronized void generatedCommand(){
        System.out.println("genero comanda");
        while (commands.size()==TOTAL) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Diner dinerOrder=restaurant.getDinnerByState(DinerState.SIT_WITHOUT_ORDER);
        System.out.println("Busco diner: "+ dinerOrder);
        System.out.println("restaurant:"+restaurant.toString());
        if(dinerOrder!=null){
            System.out.println("que siempre si hago la comanda");
            dinerOrder.setState(DinerState.WAIT_ORDER);
            commands.add(dinerOrder);
        }
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notifyAll();
    }

    public Deque<Diner> getOrders() {
        return orders;
    }

    public void setOrders(Deque<Diner> orders) {
        this.orders = orders;
    }
}
