package com.example.restaurant_simulator.models;

import com.example.restaurant_simulator.models.enums.DinerState;
import com.example.restaurant_simulator.models.enums.TableState;

import java.util.Arrays;

public class Restaurant {
    private Table[]tables;

    public Restaurant() {
        this.tables = new Table[20];
        for (int i = 0; i < this.tables.length; i++) {
            this.tables[i] = new Table(null);
        }
    }

    public boolean isFull(){
        System.out.println(Arrays.toString(this.tables));
        for (Table table : this.tables) {
            if (TableState.EMPTY.equals(table.getState()))
                return false;
        }
        return true;
    }

    public void setData(Diner diner){
        for (int i = 0; i < this.tables.length; i++) {
            if(TableState.EMPTY.equals(this.tables[i].getState())) {
                diner.setTableId(i);
                this.tables[i].setDiner(diner);
                this.tables[i].setState(TableState.BUSY);
                return;
            }
        }
    }

    public Diner getDinnerByState(DinerState state){
        for (int i = 0; i < this.tables.length; i++) {
            if(TableState.BUSY.equals(this.tables[i].getState())) {
                if(this.tables[i].getDiner().getState().equals(state)){
                    System.out.println("encontre1: "+this.tables[i].toString());
                    return  this.tables[i].getDiner();
                }
            }
        }
        return null;
    }

    public void removeDinnerByTableId(int id){
        this.tables[id].setDiner(null);
        this.tables[id].setState(TableState.EMPTY);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "tables=" + Arrays.toString(tables) +
                '}';
    }

}
