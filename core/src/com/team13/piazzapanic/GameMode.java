package com.team13.piazzapanic;

public class GameMode {

    public enum GAME_TYPE {SET, EASY, NORMAL, HARD;}
    private GAME_TYPE gameMode;
    private int numOfOrders;

    public void setGameType(GAME_TYPE gm){
        this.gameMode = gm;
    }

    public GAME_TYPE getGameType(){
        return this.gameMode;
    }

    public void setNumOfOrders(int n){
        this.numOfOrders = n;
    }

    public int getNumOfOrders(){
        return this.numOfOrders;
    }

}
