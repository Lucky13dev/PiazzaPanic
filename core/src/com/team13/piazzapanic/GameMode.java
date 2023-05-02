package com.team13.piazzapanic;

/**
 * A class used to store the information about the game mode.
 * Contains getters and setters for the game type and number of orders.
 */
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
