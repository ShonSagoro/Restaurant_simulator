package com.example.restaurant_simulator.models;
import com.example.restaurant_simulator.models.enums.DinerState;
import javafx.scene.paint.Color;

public class Diner {
    private DinerState state;
    private int id;
    private  int tableId;
    private Color color;
    private int time;

    public Diner(int id){
        this.id=id;
        this.time=0;
        this.color=GenerateColorRandom();
        this.state=DinerState.WAIT;
        this.tableId=-1;
    }

    private Color GenerateColorRandom(){
        int rangR = (int)(Math.random() * (255 - 130)) + 130;
        int rangG = (int)(Math.random() * (255 - 130)) + 130;
        int rangB = (int)(Math.random() * (255 - 130)) + 130;
        return Color.rgb(rangR, rangG, rangB);
    }

    public Color getColor() {
        return color;
    }

    public int getTime() {
        return time;
    }

    public DinerState getState() {
        return state;
    }

    public void setState(DinerState state) {
        this.state = state;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "Diner{" +
                "state=" + state +
                ", id=" + id +
                ", tableId=" + tableId +
                ", color=" + color +
                ", time=" + time +
                '}';
    }

    public void decrementTime(){
        this.time--;
    }
}
