package de.catepilla.gdxtesting.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.team13.piazzapanic.GameMode;
import com.team13.piazzapanic.GameTypes;
import com.team13.piazzapanic.MainGame;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * Tests the FR_DIFFICULTY requirement.
 */
@RunWith(GdxTestRunner.class)
public class GameModeTests {

    /**
     * Tests on the Game Modes and Difficulties.
     */

    @Test
    public void setGameMode1Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameMode(GameTypes.SET);
        gameMode.setNumOfOrders(1);
        assertEquals(gameMode.getGameMode(), GameTypes.SET);
        assertEquals(gameMode.getNumOfOrders(), 1);
    }


    @Test
    public void setGameMode2Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameMode(GameTypes.SET);
        gameMode.setNumOfOrders(2);
        assertEquals(gameMode.getGameMode(), GameTypes.SET);
        assertEquals(gameMode.getNumOfOrders(), 2);
    }

    @Test
    public void setGameMode3Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameMode(GameTypes.SET);
        gameMode.setNumOfOrders(3);
        assertEquals(gameMode.getGameMode(), GameTypes.SET);
        assertEquals(gameMode.getNumOfOrders(), 3);
    }

    @Test
    public void setGameMode4Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameMode(GameTypes.SET);
        gameMode.setNumOfOrders(4);
        assertEquals(gameMode.getGameMode(), GameTypes.SET);
        assertEquals(gameMode.getNumOfOrders(), 4);
    }

    @Test
    public void setGameMode5Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameMode(GameTypes.SET);
        gameMode.setNumOfOrders(5);
        assertEquals(gameMode.getGameMode(), GameTypes.SET);
        assertEquals(gameMode.getNumOfOrders(), 5);
    }

    @Test
    public void setGameModeEndlessEasyTest() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameMode(GameTypes.EASY);
        gameMode.setNumOfOrders(0);
        assertEquals(gameMode.getGameMode(), GameTypes.EASY);
        assertEquals(gameMode.getNumOfOrders(), 0);
    }

    @Test
    public void setGameModeEndlessNormalTest() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameMode(GameTypes.NORMAL);
        gameMode.setNumOfOrders(0);
        assertEquals(gameMode.getGameMode(), GameTypes.NORMAL);
        assertEquals(gameMode.getNumOfOrders(), 0);
    }

    @Test
    public void setGameModeEndlessHardTest() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameMode(GameTypes.HARD);
        gameMode.setNumOfOrders(0);
        assertEquals(gameMode.getGameMode(), GameTypes.HARD);
        assertEquals(gameMode.getNumOfOrders(), 0);
    }

}