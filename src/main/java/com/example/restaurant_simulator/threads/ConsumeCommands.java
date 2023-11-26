package com.example.restaurant_simulator.threads;

import com.example.restaurant_simulator.models.ChefMonitor;

import java.util.Observable;

public class ConsumeCommands  extends Observable implements Runnable{
    private ChefMonitor chefMonitor;

    public ConsumeCommands(ChefMonitor chefMonitor) {
        this.chefMonitor = chefMonitor;
    }

    @Override
    public void run() {
        while(true){
            this.chefMonitor.makeTheOrder();
            setChanged();
            notifyObservers("3");
        }
    }
}
