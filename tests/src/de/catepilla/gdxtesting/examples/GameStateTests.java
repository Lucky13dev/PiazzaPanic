package de.catepilla.gdxtesting.examples;


import Sprites.Chef;
import Tools.B2WorldCreator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.team13.piazzapanic.GameMode;
import com.team13.piazzapanic.GameState;
import com.team13.piazzapanic.MainGame;
import com.team13.piazzapanic.PlayScreen;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class GameStateTests {

    @Test
    public void TestMaxChefs() {
        GameState gameState = new GameState(new GameMode());
        int max_count = GameState.getMAX_CHEF_COUNT();
        int expected_max_count = 5;
        assertEquals(expected_max_count, max_count);
    }

    @Test
    public void TextChefIndex() {

        GameState gameState = new GameState(new GameMode());
        gameState.addChef(new Chef(31.5F,65));
        gameState.setControlledChef(1);
        Chef controlledChef = gameState.getControlledChef();
        assertEquals(1, controlledChef);

    }

}
