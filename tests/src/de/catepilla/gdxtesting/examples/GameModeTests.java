package de.catepilla.gdxtesting.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.team13.piazzapanic.MainGame;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class GameModeTests {

    /**
     * Tests on the Game Modes.
     * Currently not possible to test due to MainGame.numOfOrders and MainGame.gameMode having private access.
     */
//    @Test
//    public void setGameModeTest() {
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
//            assertEquals(MainGame.numOfOrders,1);
//            assertEquals(MainGame.gameMode,"setMode");
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
//            assertEquals(MainGame.numOfOrders,2);
//            assertEquals(MainGame.gameMode,"setMode");
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
//            assertEquals(MainGame.numOfOrders,3);
//            assertEquals(MainGame.gameMode,"setMode");
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
//            assertEquals(MainGame.numOfOrders,4);
//            assertEquals(MainGame.gameMode,"setMode");
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)){
//            assertEquals(MainGame.numOfOrders,5);
//            assertEquals(MainGame.gameMode,"setMode");
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
//            assertEquals(MainGame.numOfOrders,0);
//            assertEquals(MainGame.gameMode,"endless");
//        }
//    }
        //REMOVE LATER: line376 onwards on PlayScreen might be testable
}
