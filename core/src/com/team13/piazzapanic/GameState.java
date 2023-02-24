package com.team13.piazzapanic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import Sprites.*;
import java.util.*;

/**
 * GameState is a class that stores the current state of the game so it can be saved later.
 */
public class GameState {

    private List<Chef> chefs;
    private int controlledChef;
    private final int MAX_CHEF_COUNT = 3;

    public int getControlledChef() {
        return controlledChef;
    }

    /**
     *
     * @param controlledChef The index of the controlledChef in chefs
     */
    public void setControlledChef(int controlledChef) {
        this.controlledChef = controlledChef;
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

}
