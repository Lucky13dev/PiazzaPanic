package de.catepilla.gdxtesting.examples;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team13.piazzapanic.GameState;
import com.team13.piazzapanic.MainGame;
import com.team13.piazzapanic.PlayScreen;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the FR_FINISH requirement.
 */
@RunWith(GdxTestRunner.class)
public class ScenarioCompletionTest {

    @Test
    public void zeroOrdersRemainingTest(){
        MainGame game = new MainGame();
        PlayScreen playScreen = new PlayScreen(game);
        GameState gameState = new GameState();

        if(playScreen.ordersArray.size()==0) {
            assertEquals(gameState.getScenarioStatus(), GameState.scenarioState.COMPLETED);
        }
    }
    @Test
    public void OrdersRemainingTest(){
        MainGame game = new MainGame();
        PlayScreen playScreen = new PlayScreen(game);
        GameState gameState = new GameState();

        if(playScreen.ordersArray.size()==1) {
            assertEquals(gameState.getScenarioStatus(), GameState.scenarioState.LIVE);
        }
    }
}
