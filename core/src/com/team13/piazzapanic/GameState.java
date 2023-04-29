package com.team13.piazzapanic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import Sprites.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import javax.naming.spi.NamingManager;
import java.io.*;
import java.util.*;

/**
 * GameState is a class that stores the current state of the game, so it can be saved later.
 */
public class GameState implements Serializable {
    private class GameSave implements Serializable {
        private float time;
        private List<Vector2> chefLocations;
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
         * Loads and updates a save game
         * @param saveName name of the save to load
         * @return true if provided save name is valid, false otherwise
         */
        public boolean load(String saveName){
            try {
                FileInputStream fileInputStream
                        = new FileInputStream(saveName + ".pza");
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(fileInputStream);
                GameSave loaded = (GameSave) objectInputStream.readObject();
                objectInputStream.close();

                this.time = loaded.time;
                this.chefLocations = loaded.chefLocations;

                return true;
            }
            catch (IOException | ClassNotFoundException e){
                return false;
            }
        }
    }
    // VARIABLES
    transient List<Chef> chefs;
    transient Chef controlledChef;
    private static final int MAX_CHEF_COUNT = 5;
    private float time;
    transient HUD hud;

    private Difficulty gameDifficulty;
    public enum Difficulty {EASY, MEDIUM, HARD};
    private int reputation;
    private int money;
    // CREATE VARAIBLE FOR CURRENT ORDERS

    // METHODS

    public GameState(){
        this.chefs = new ArrayList<>();
        this.time = 0;
        this.reputation = 0;
        this.money = 0;
    }

    public int getMoney() {
        return this.money;
    }
    public void setMoney(int newBalance){
        this.money = newBalance;
        this.getHud().updateMoney(newBalance);
    }
    public void giveMoney(int amount){
        this.setMoney(this.getMoney()+amount);
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

  
     /**
     * Set the game time.
     * @param time, the time to set the gamestate to.
     */
    public void setTime(float time){this.time = time;}
    
    /**
     * Decrement the game time.
     * @param dt delta time, the amount of time to decrement by.
     */
    public void decrementTime(float dt) { this.time -= dt;}

    public HUD getHud() {
        return hud;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }

    public boolean load(String saveName){
        GameSave save = new GameSave();
        boolean loadSuccess = save.load(saveName);
        if (loadSuccess) {
            this.setTime(save.time);
            //setup chefs
            for (int i = 0; i<this.chefs.size(); i++){
                this.chefs.get(i).b2body.setTransform(save.chefLocations.get(i), 0);
            }
        }
        return loadSuccess;
    }
    public boolean save(String saveName){
        GameSave save = new GameSave();

        //create a save with information from this GameState
        save.time = this.getTime();
        save.chefLocations = new ArrayList<>();
        for (Chef chef : chefs){
            save.chefLocations.add(chef.b2body.getPosition());
        }
        return save.save(saveName);
    }

}
