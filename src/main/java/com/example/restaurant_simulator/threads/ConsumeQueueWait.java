package com.example.restaurant_simulator.threads;

import com.example.restaurant_simulator.models.DinerMonitor;
import com.example.restaurant_simulator.models.Restaurant;

import java.util.ArrayList;
import java.util.Observable;

public class ConsumeQueueWait  extends Observable implements Runnable{
    private DinerMonitor dinerMonitor;

    public ConsumeQueueWait(DinerMonitor dinerMonitor){
        this.dinerMonitor=dinerMonitor;
    }
    @Override
    public void run() {
        while (true){
            System.out.println("ESTOY AQUI");
            this.dinerMonitor.extractDinersWait();
            setChanged();
            notifyObservers("2");
        }
    }
}
