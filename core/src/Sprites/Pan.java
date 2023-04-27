package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Pan is a concrete class that extends the {@link InteractiveTileObject} class.
 * Pan is a class extending InteractiveTileObject representing a Pan in the game where the chef can cook steaks
 * and toast buns
 */

public class Pan extends InteractiveTileObject {
    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked() {
        this.unlocked = true;
    }

    // boolean variable to determine the state of the workstation
    private boolean unlocked;
    public Pan(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
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
