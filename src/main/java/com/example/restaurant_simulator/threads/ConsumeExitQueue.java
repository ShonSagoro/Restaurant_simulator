package com.example.restaurant_simulator.threads;

import com.example.restaurant_simulator.models.ExitMonitor;

import java.util.Observable;

public class ConsumeExitQueue extends Observable implements Runnable{
    private ExitMonitor exitMonitor;

    public ConsumeExitQueue(ExitMonitor exitMonitor) {
        this.exitMonitor = exitMonitor;
    }

    @Override
    public void run() {
        while (true){
            exitMonitor.extractDinersExit();
            setChanged();
            notifyObservers("4");
        }

    }
}
