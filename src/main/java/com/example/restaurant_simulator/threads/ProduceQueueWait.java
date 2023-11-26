package com.example.restaurant_simulator.threads;

import com.example.restaurant_simulator.models.DinerMonitor;
import java.util.Observable;

public class ProduceQueueWait  extends Observable implements Runnable{

    private final DinerMonitor dinerMonitor;
    public ProduceQueueWait(DinerMonitor dinerMonitor){
        this.dinerMonitor=dinerMonitor;
    }
    @Override
    public void run() {
        while (true){
            this.dinerMonitor.generateDinersWait();
            setChanged();
            notifyObservers("1");
        }
    }
}
