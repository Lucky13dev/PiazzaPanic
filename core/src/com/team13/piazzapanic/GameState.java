package com.team13.piazzapanic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import Sprites.*;

import java.io.*;
import java.util.*;

/**
 * GameState is a class that stores the current state of the game, so it can be saved later.
 */
public class GameState implements Serializable {
    // VARIABLES
    private List<Chef> chefs;
    private Chef controlledChef;
    private static final int MAX_CHEF_COUNT = 5;

    private float time;

    private HUD hud;


    // METHODS

    public GameState(){
        this.chefs = new ArrayList<>();
        this.time = 0;
    }

    public static int getMAX_CHEF_COUNT() {
        // Useful for changing chefs
        return MAX_CHEF_COUNT;
    }

    public Chef getControlledChef() {
        return controlledChef;
    }

    /**
     *
     * @param controlledChef The index of the controlledChef in chefs
     */
    public void setControlledChef(int controlledChef) {
        if(controlledChef >=0 && controlledChef < this.chefs.size())
            this.controlledChef = this.chefs.get(controlledChef);
    }

    /**
     * @param chef An instance of the chef to add to the list,
     * Add a chef to the chef list,
     * there must be less than 3 chefs,
     * returns the index of the chef
     */
    public int addChef(Chef chef){

        if(this.chefs.size() < this.MAX_CHEF_COUNT){
            this.chefs.add(chef);
            return this.chefs.indexOf(chef);
        }
        else{
            // Error, too many chefs
            return -1;
        }
    }

    public List<Chef> getChefs(){
        return this.chefs;
    }

    public float getTime() {
        return time;
    }

    /**
     * Increment the game time.
     * @param dt delta time, the amount of time to increment by.
     */
    public void incrementTime(float dt) {
        this.time += dt;
    }

    public HUD getHud() {
        return hud;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }

    /**
     * saves the game state to a binary file
     * @param saveName the name of the game save.
     * @return true if the game has saved, false otherwise
     */
    public boolean save(String saveName){
        try{
            FileOutputStream fileOutputStream
                    = new FileOutputStream(saveName+".pza");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        }
        catch (IOException e){
            return false;
        }
    }

    /**
     * Loads a save game
     * @param saveName name of the save to load
     * @return a new GameState if the save can be loaded, null otherwise
     */
    public static GameState load(String saveName){
        try {
            FileInputStream fileInputStream
                    = new FileInputStream(saveName + ".pza");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            GameState loaded = (GameState) objectInputStream.readObject();
            objectInputStream.close();
            return loaded;
        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }
    }
}
