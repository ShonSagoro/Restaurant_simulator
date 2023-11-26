package com.example.restaurant_simulator.threads;

import com.example.restaurant_simulator.models.Diner;
import com.example.restaurant_simulator.models.ExitMonitor;
import com.example.restaurant_simulator.models.enums.DinerState;

public class CounterToEat implements Runnable {
    private ExitMonitor exitMonitor;
    private Diner diner;

    public CounterToEat(ExitMonitor exitMonitor, Diner diner) {
        this.exitMonitor = exitMonitor;
        this.diner = diner;
    }

    @Override
    public void run() {
        while (diner.getTime()>0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            diner.decrementTime();
        }
        diner.setState(DinerState.EAT_FINISH);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        exitMonitor.passToExitQueue();
    }
}
