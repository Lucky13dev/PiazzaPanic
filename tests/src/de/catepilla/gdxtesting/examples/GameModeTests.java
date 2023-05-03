package de.catepilla.gdxtesting.examples;


import com.team13.piazzapanic.GameMode;
import com.team13.piazzapanic.MainGame;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

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

        gameMode.setGameType(GameMode.GAME_TYPE.SET);
        gameMode.setNumOfOrders(1);
        assertEquals(gameMode.getGameType(), GameMode.GAME_TYPE.SET);
        assertEquals(gameMode.getNumOfOrders(), 1);
    }


    @Test
    public void setGameMode2Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameType(GameMode.GAME_TYPE.SET);
        gameMode.setNumOfOrders(2);
        assertEquals(gameMode.getGameType(), GameMode.GAME_TYPE.SET);
        assertEquals(gameMode.getNumOfOrders(), 2);
    }

    @Test
    public void setGameMode3Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameType(GameMode.GAME_TYPE.SET);
        gameMode.setNumOfOrders(3);
        assertEquals(gameMode.getGameType(), GameMode.GAME_TYPE.SET);
        assertEquals(gameMode.getNumOfOrders(), 3);
    }

    @Test
    public void setGameMode4Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameType(GameMode.GAME_TYPE.SET);
        gameMode.setNumOfOrders(4);
        assertEquals(gameMode.getGameType(), GameMode.GAME_TYPE.SET);
        assertEquals(gameMode.getNumOfOrders(), 4);
    }

    @Test
    public void setGameMode5Test() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameType(GameMode.GAME_TYPE.SET);
        gameMode.setNumOfOrders(5);
        assertEquals(gameMode.getGameType(), GameMode.GAME_TYPE.SET);
        assertEquals(gameMode.getNumOfOrders(), 5);
    }

    @Test
    public void setGameModeEndlessEasyTest() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameType(GameMode.GAME_TYPE.EASY);
        gameMode.setNumOfOrders(0);
        assertEquals(gameMode.getGameType(), GameMode.GAME_TYPE.EASY);
        assertEquals(gameMode.getNumOfOrders(), 0);
    }

    @Test
    public void setGameModeEndlessNormalTest() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameType(GameMode.GAME_TYPE.NORMAL);
        gameMode.setNumOfOrders(0);
        assertEquals(gameMode.getGameType(), GameMode.GAME_TYPE.NORMAL);
        assertEquals(gameMode.getNumOfOrders(), 0);
    }

    @Test
    public void setGameModeEndlessHardTest() {
        MainGame mainGame = new MainGame();
        GameMode gameMode = new GameMode();

        gameMode.setGameType(GameMode.GAME_TYPE.HARD);
        gameMode.setNumOfOrders(0);
        assertEquals(gameMode.getGameType(), GameMode.GAME_TYPE.HARD);
        assertEquals(gameMode.getNumOfOrders(), 0);
    }

}