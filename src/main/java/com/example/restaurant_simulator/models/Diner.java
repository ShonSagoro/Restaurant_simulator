package com.example.restaurant_simulator.models;
import javafx.scene.paint.Color;

public class Diner {

    private int id;
    private Color color;
    private int time;

    public Diner(int id){
        this.id=id;
        this.time=0;
        this.color=GenerateColorRandom();
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

    @Override
    public String toString() {
        return "Diner{" +
                "id=" + id +
                ", color=" + color +
                ", time=" + time +
                '}';
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
