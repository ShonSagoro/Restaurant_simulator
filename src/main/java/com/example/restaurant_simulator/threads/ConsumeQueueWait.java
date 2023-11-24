package com.example.restaurant_simulator.threads;

import com.example.restaurant_simulator.models.Diner;
import com.example.restaurant_simulator.models.DinerMonitor;

import java.util.Observable;

public class ConsumeQueueWait  extends Observable implements Runnable{
    private DinerMonitor dinerMonitor;
    public ConsumeQueueWait(DinerMonitor dinerMonitor){
        this.dinerMonitor=dinerMonitor;
    }
    @Override
    public void run() {
        while (true){
            this.dinerMonitor.extractDinersWait();
            setChanged();
            notifyObservers("2");
        }
    }
}
