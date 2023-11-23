package com.example.restaurant_simulator.threads;

import com.example.restaurant_simulator.models.Diner;
import com.example.restaurant_simulator.models.DinerMonitor;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class ProduceQueueWait  extends Observable implements Runnable{

    private final DinerMonitor dinerMonitor;
    public ProduceQueueWait(DinerMonitor dinerMonitor){
        this.dinerMonitor=dinerMonitor;
    }
    @Override
    public void run() {
        while (true){
            Diner diner=this.dinerMonitor.generateDinersWait();
            if (diner==null)
                    return;
            setChanged();
            notifyObservers("1");
        }
    }
}
