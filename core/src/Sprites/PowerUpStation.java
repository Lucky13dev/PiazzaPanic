package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import PowerUps.*;

import java.util.Random;

public class PowerUpStation extends InteractiveTileObject{
    /**
     * Constructor for the class, initialises b2bodies.
     * The power up stations lets the player pickup 1 of 5 power ups.
     * @param world     The playable world.
     * @param map       The tiled map.
     * @param bdef      The body definition of a tile.
     * @param rectangle Rectangle shape.
     */
    public PowerUpStation(World world, TiledMap map, BodyDef bdef, Rectangle rectangle) {
        super(world, map, bdef, rectangle);
        fixture.setUserData(this);
    }

    /**
     * Generates a random number to determine which of the 5 power ups to pick up.
     * @return A random power up.
     */
    public PowerUp getPowerUp(){
        Random rand = new Random();
        int num = rand.nextInt(5);
        switch(num){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
}
