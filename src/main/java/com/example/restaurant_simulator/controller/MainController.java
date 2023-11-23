package com.example.restaurant_simulator.controller;

import com.example.restaurant_simulator.models.Diner;
import com.example.restaurant_simulator.models.DinerMonitor;
import com.example.restaurant_simulator.threads.ProduceQueueWait;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import java.util.Observer;


public class MainController implements Observer{
    private DinerMonitor dinerMonitor;

    @FXML
    private HBox queue_wait;

    @FXML
    private Rectangle enter_diner;


    @FXML
    void onClickedStart(MouseEvent event) {
        this.dinerMonitor=new DinerMonitor(10);
        ProduceQueueWait cons =  new ProduceQueueWait(dinerMonitor);
        cons.addObserver(this);
        Thread h1 = new Thread(cons);
        h1.start();
    }

    @Override
    public void update(Observable observable, Object o) {
        switch (Integer.valueOf(String.valueOf(o))) {
            case 1:
                Diner popDiner=dinerMonitor.getNewDiner();
                Rectangle square = new Rectangle(50, 50, popDiner.getColor());
                Platform.runLater(()->{queue_wait.getChildren().add(square);});
                System.out.println(popDiner.toString());
                break;
        }
    }


}
