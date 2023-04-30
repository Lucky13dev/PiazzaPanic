package com.team13.piazzapanic;

public class GameMode {

    private GameTypes gameMode;
    private int numOfOrders;

    public void setGameMode(GameTypes gm){
        this.gameMode = gm;
    }

    public GameTypes getGameMode(){
        return this.gameMode;
    }

    public void setNumOfOrders(int n){
        this.numOfOrders = n;
    }

    public int getNumOfOrders(){
        return this.numOfOrders;
    }

}
