package com.team13.piazzapanic;

import Sprites.*;
import com.badlogic.gdx.math.Vector2;

import java.io.*;
import java.util.*;

/**
 * GameState is a class that stores the current state of the game, so it can be saved later.
 */
public class GameState implements Serializable {
    private class GameSave implements Serializable {
        private float time;
        private List<Vector2> chefLocations;
        private scenarioState scenarioStatus;
        private float reputation;
        private int money;
        private float chefSpeed;
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
                this.scenarioStatus = loaded.scenarioStatus;
                this.reputation = loaded.reputation;
                this.money = loaded.money;
                this.chefSpeed = loaded.chefSpeed;

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
    private float reputation;
    private int money;

    private float chefSpeed;

    public enum scenarioState {LIVE, COMPLETED, FAILED};
    private scenarioState scenarioStatus;
    // CREATE VARAIBLE FOR CURRENT ORDERS

    // METHODS

    public GameState(){
        this.chefs = new ArrayList<>();
        this.time = 0;
        this.reputation = 60;
        this.money = 0;
        this.chefSpeed = 0.5f;
        this.scenarioStatus = scenarioState.LIVE;
    }

    public scenarioState getScenarioStatus(){return this.scenarioStatus;}
    public void setScenarioStatus(scenarioState state){this.scenarioStatus = state;}
    public boolean isCompleted(){return this.scenarioStatus == scenarioState.COMPLETED;}
    public boolean isFailed(){return this.scenarioStatus == scenarioState.FAILED;}
    public boolean isFinished(){return (this.scenarioStatus == scenarioState.COMPLETED || this.scenarioStatus == scenarioState.FAILED);}
    public int getMoney() {
        return this.money;
    }
    public void setMoney(int newBalance){
        if(isFinished()){return;}
        this.money = newBalance;
        this.getHud().updateMoney(newBalance);
    }
    public void giveMoney(int amount){
        this.setMoney(this.getMoney()+amount);
    }

    public float getReputation() {
        return this.reputation;
    }
    public void setReputation(float newReputation){
        if(isFinished()){return;}
        this.reputation = newReputation;
        this.getHud().updateReputation((int) newReputation);
    }
    public void giveReputation(float amount){
        this.setReputation(this.getReputation()+amount);
    }

    public float getChefSpeed() {
        return this.chefSpeed;
    }
    public void setChefSpeed(float newSpeed){
        if(isFinished()){return;}
        this.chefSpeed = newSpeed;
    }

    public void multiplyChefSpeed(float multiplier){
        if(isFinished()){return;}
        this.chefSpeed *= multiplier;
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
        if(isFinished()){return;}
        this.setTime(this.getTime()+dt);
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
        if (!loadSuccess){
            this.getHud().addMessage("Failed to Load Game");
            return false;
        }
        this.setTime(save.time);
        this.setReputation(save.reputation);
        this.setMoney(save.money);
        this.setScenarioStatus(save.scenarioStatus);
        this.setChefSpeed(save.chefSpeed);
        //setup chefs
        for (int i = 0; i<this.chefs.size(); i++){
            this.chefs.get(i).b2body.setTransform(save.chefLocations.get(i), 0);
        }
        this.getHud().clearMessages();
        this.getHud().addMessage("Game Loaded");
        return true;
    }
    public boolean save(String saveName){
        GameSave save = new GameSave();

        //create a save with information from this GameState
        save.time = this.getTime();
        save.chefLocations = new ArrayList<>();
        save.scenarioStatus = this.getScenarioStatus();
        save.reputation = this.getReputation();
        save.money = this.getMoney();
        save.chefSpeed = this.chefSpeed;
        for (Chef chef : chefs){
            save.chefLocations.add(chef.b2body.getPosition());
        }
        boolean success = save.save(saveName);
        if (success = false){
            this.getHud().addMessage("Failed to Save");
            return false;
        } else {
            this.getHud().addMessage("Saved Game");
            return true;
        }
    }

}
