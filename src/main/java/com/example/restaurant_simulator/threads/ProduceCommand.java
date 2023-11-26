package com.example.restaurant_simulator.threads;

import com.example.restaurant_simulator.models.ChefMonitor;
import java.util.concurrent.ThreadLocalRandom;

public class ProduceCommand implements Runnable{
    private ChefMonitor chefMonitor;

    public ProduceCommand(ChefMonitor chefMonitor) {
        this.chefMonitor = chefMonitor;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            this.chefMonitor.generatedCommand();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
