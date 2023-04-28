package com.team13.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;

public class MainGame extends Game {

	/**
	 * MainGame class is the central class of the game that creates and manages the two screens, PlayScreen and StartScreen.
	 *
	 * Class Members:
	 *     V_WIDTH (int): Width of the view.
	 *     V_HEIGHT (int): Height of the view.
	 *     TILE_SIZE (int): Size of the tile.
	 *     PPM (float): Pixels per meter.
	 *     batch (SpriteBatch): Instance of SpriteBatch.
	 *     isPlayScreen (bool): Flag indicating whether the PlayScreen is displayed or not.
	 *     playScreen (PlayScreen): Instance of PlayScreen.
	 *     startScreen (StartScreen): Instance of StartScreen.
	 *
	 * Methods:
	 *     __init__: Initializes the MainGame class.
	 *     create: Creates the instances of StartScreen and PlayScreen and initializes the SpriteBatch instance.
	 *     render: Renders the StartScreen or PlayScreen based on the value of isPlayScreen flag.
	 * 	   dispose: Releases resources used by the MainGame class.
	 */
	public static final int V_WIDTH = 160;
	public static final int V_HEIGHT = 160;
	public static final int TILE_SIZE = 16;

	public static final float PPM = 100;
	public SpriteBatch batch;
	public boolean isPlayScreen;
	private PlayScreen playScreen;
	private StartScreen startScreen;
	private int numOfOrders;
	private String gameMode = "";

	public MainGame(){
		isPlayScreen = false;
	}
	@Override
	public void create() {
		batch = new SpriteBatch();
		startScreen = new StartScreen(this);
		playScreen = new PlayScreen(this);
	}

	@Override
	public void render() {
		super.render();
		// Open/close the menu
		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
			isPlayScreen = !isPlayScreen;
			// if the game mode is not set...
			if(this.gameMode.equals("")){
				// Default gameMode
				this.gameMode = "setMode";
				this.numOfOrders = 5;
				playScreen.setMode(this.gameMode, this.numOfOrders);
			}
		}

		// Set the game mode
		if(this.gameMode.equals("")) {
			// set the number of orders
			if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
				this.gameMode = "setMode";
				this.numOfOrders = 1;
				playScreen.setMode(this.gameMode, this.numOfOrders);
				isPlayScreen = !isPlayScreen;
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
				this.gameMode = "setMode";
				this.numOfOrders = 2;
				playScreen.setMode(this.gameMode, this.numOfOrders);
				isPlayScreen = !isPlayScreen;
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
				this.gameMode = "setMode";
				this.numOfOrders = 3;
				playScreen.setMode(this.gameMode, this.numOfOrders);
				isPlayScreen = !isPlayScreen;
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
				this.gameMode = "setMode";
				this.numOfOrders = 4;
				playScreen.setMode(this.gameMode, this.numOfOrders);
				isPlayScreen = !isPlayScreen;
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)){
				this.gameMode = "setMode";
				this.numOfOrders = 5;
				playScreen.setMode(this.gameMode, this.numOfOrders);
				isPlayScreen = !isPlayScreen;
			}
			// set the game mode to endless with a difficulty
			else if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
				this.gameMode = "easy";
				this.numOfOrders = 0;
				playScreen.setMode(this.gameMode, this.numOfOrders);
				isPlayScreen = !isPlayScreen;
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.N)){
				this.gameMode = "normal";
				this.numOfOrders = 0;
				playScreen.setMode(this.gameMode, this.numOfOrders);
				isPlayScreen = !isPlayScreen;
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.H)){
				this.gameMode = "hard";
				this.numOfOrders = 0;
				playScreen.setMode(this.gameMode, this.numOfOrders);
				isPlayScreen = !isPlayScreen;
			}
		}

		if (isPlayScreen) {
			setScreen(playScreen);
		} else {
			setScreen(startScreen);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}