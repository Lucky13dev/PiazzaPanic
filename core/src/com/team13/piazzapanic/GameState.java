package com.team13.piazzapanic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import Sprites.*;
import java.util.*;

/**
 * GameState is a class that stores the current state of the game so it can be saved later.
 */
public class GameState {
    // VARIABLES
    private List<Chef> chefs;
    private Chef controlledChef;
    private final int MAX_CHEF_COUNT = 3;

    private float time;

    private HUD hud;


    // METHODS

    public GameState(){
        this.chefs = new ArrayList<>();
        this.time = 0;
    }

    public int getMAX_CHEF_COUNT() {
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

}
