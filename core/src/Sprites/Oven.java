package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Oven is a concrete class that extends the {@link InteractiveTileObject} class.
 * Oven is a class extending InteractiveTileObject representing an oven in the game where the chef can bake potatoes
 * and cook pizzas.
 */

public class Oven extends InteractiveTileObject{

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked() {
        this.unlocked = true;
    }

    // boolean variable to determine the state of the workstation
    private boolean unlocked;

    public Oven(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
        this.unlocked = false;
    }

    public float getX(){
        return super.bdefNew.position.x;
    }

    public float getY(){
        return super.bdefNew.position.y;
    }
}
