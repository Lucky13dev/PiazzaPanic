package com.team13.piazzapanic;

import Ingredients.Ingredient;
import Recipe.Recipe;
import Sprites.*;
import Recipe.Order;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The PlayScreen class is responsible for displaying the game to the user and handling the user's interactions.
 * The PlayScreen class implements the Screen interface which is part of the LibGDX framework.
 *
 * The PlayScreen class contains several important fields, including the game instance, stage instance, viewport instance,
 * and several other helper classes and variables. The game instance is used to access the global game configuration and
 * to switch between screens. The stage instance is used to display the graphics and handle user interactions, while the
 * viewport instance is used to manage the scaling and resizing of the game window.
 *
 * The PlayScreen class also contains several methods for initializing and updating the game state, including the
 * constructor, show(), render(), update(), and dispose() methods. The constructor sets up the stage, viewport, and
 * other helper classes and variables. The show() method is called when the PlayScreen becomes the active screen. The
 * render() method is called repeatedly to render the game graphics and update the game state. The update() method is
 * called to update the game state and handle user inputs. The dispose() method is called when the PlayScreen is no longer
 * needed and is used to clean up resources and prevent memory leaks.
 */


public class PlayScreen implements Screen {

    private final MainGame game;
    private final OrthographicCamera gamecam;
    private final Viewport gameport;

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final World world;
    private final GameState gameState;

    //private Chef controlledChef;

    public ArrayList<Order> ordersArray;

    public PlateStation plateStation;


    public Boolean scenarioComplete;

    public static float trayX;
    public static float trayY;

    /**
     * PlayScreen constructor initializes the game instance, sets initial conditions for scenarioComplete and createdOrder,
     * creates and initializes game camera and viewport,
     * creates and initializes HUD and orders hud, loads and initializes the map,
     * creates and initializes world, creates and initializes chefs and sets them, sets contact listener for world, and initializes ordersArray.
     * @param game The MainGame instance that the PlayScreen will be a part of.
     */

    public PlayScreen(MainGame game){
        this.gameState = new GameState();
        this.game = game;
        scenarioComplete = Boolean.FALSE;
        gamecam = new OrthographicCamera();
        // FitViewport to maintain aspect ratio whilst scaling to screen size
        gameport = new FitViewport(MainGame.V_WIDTH / MainGame.PPM, MainGame.V_HEIGHT / MainGame.PPM, gamecam);
        // create HUD for score & time
        this.gameState.setHud(new HUD(game.batch));
        // create orders hud
        Orders orders = new Orders(game.batch);
        // create map
        TmxMapLoader mapLoader = new TmxMapLoader(new InternalFileHandleResolver());
        map = mapLoader.load("Kitchen.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MainGame.PPM);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,0), true);
        new B2WorldCreator(world, map, this);

        // Instantiate 3 chefs and set the controlled chef
        this.gameState.addChef(new Chef(this.world, 31.5F,65));
        this.gameState.addChef(new Chef(this.world, 128,65));
        this.gameState.addChef(new Chef(this.world, 144, 32));
        this.gameState.setControlledChef(0);

        // Chefs are gotten by their index
        //chef1 = this.gameState.getChefs().get(0);
        //chef2 = this.gameState.getChefs().get(1);
        //chef3 = this.gameState.getChefs().get(2);

        world.setContactListener(new WorldContactListener());

        ordersArray = new ArrayList<>();

    }

    @Override
    public void show(){

    }


    /**
     * The handleInput method is responsible for handling the input events of the game such as movement and interaction with objects.
     *
     * It checks if the 'R' key is just pressed and both chefs have the user control, if so,
     * it switches the control between the two chefs.
     *
     * If the controlled chef does not have the user control,
     * the method checks if chef1 or chef2 have the user control and sets the control to that chef.
     *
     * If the controlled chef has the user control,
     * it checks if the 'W', 'A', 'S', or 'D' keys are pressed and sets the velocity of the chef accordingly.
     *
     * If the 'E' key is just pressed and the chef is touching a tile,
     * it checks the type of tile and sets the chef's in-hands ingredient accordingly.
     *
     * The method also sets the direction of the chef based on its linear velocity.
     *
     * @param dt is the time delta between the current and previous frame.
     */

    public void handleInput(float dt){
        int controlledChefIndex = this.gameState.getChefs().indexOf(this.gameState.getControlledChef());
        // Switch between chefs
        if ((Gdx.input.isKeyJustPressed(Input.Keys.R))) {
            do {
                controlledChefIndex = (controlledChefIndex + 1) % this.gameState.getChefs().size();
                if (gameState.getChefs().get(controlledChefIndex).isControllable()) {
                    this.gameState.getControlledChef().b2body.setLinearVelocity(0, 0);
                    this.gameState.setControlledChef(controlledChefIndex);
                }
            } while (!this.gameState.getChefs().get(controlledChefIndex).isControllable());
        }
        // If the controlled chef is busy and not colliding with another chef
        if (!this.gameState.getControlledChef().isControllable() && !this.gameState.getControlledChef().isChefOnChefCollision()) {
            controlledChefIndex = this.gameState.getChefs().indexOf(this.gameState.getControlledChef());
            // Check the next chef is controllable
            do {
                controlledChefIndex = (controlledChefIndex + 1) % this.gameState.getChefs().size();
                if (this.gameState.getChefs().get(controlledChefIndex).isControllable()) {
                    this.gameState.getControlledChef().b2body.setLinearVelocity(0, 0);
                    this.gameState.setControlledChef(controlledChefIndex);
                }
            } while(!this.gameState.getChefs().get(controlledChefIndex).isControllable());
        }
        if (this.gameState.getControlledChef().isControllable()) {
                float xVelocity = 0;
                float yVelocity = 0;

                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    yVelocity += 0.5f;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    xVelocity -= 0.5f;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                    yVelocity -= 0.5f;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    xVelocity += 0.5f;
                }
                this.gameState.getControlledChef().b2body.setLinearVelocity(xVelocity, yVelocity);
            }
            else {
                this.gameState.getControlledChef().b2body.setLinearVelocity(0, 0);
            }


        if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                if(this.gameState.getControlledChef().getTouchingFixture() != null){
                    InteractiveTileObject tile = (InteractiveTileObject) this.gameState.getControlledChef().getTouchingFixture().getUserData();
                    String tileName = tile.getClass().getName();
                    if (this.gameState.getControlledChef().getInHandsIngredient() == null && this.gameState.getControlledChef().getInHandsRecipe() == null) {
                        switch (tileName) {
                            case "Sprites.TomatoStation":
                                TomatoStation tomatoTile = (TomatoStation) tile;
                                this.gameState.getControlledChef().setInHandsIngredient(tomatoTile.getIngredient());
                                this.gameState.getControlledChef().setChefSkin(this.gameState.getControlledChef().getInHandsIngredient());
                                break;
                            case "Sprites.BunsStation":
                                BunsStation bunTile = (BunsStation) tile;
                                this.gameState.getControlledChef().setInHandsIngredient(bunTile.getIngredient());
                                this.gameState.getControlledChef().setChefSkin(this.gameState.getControlledChef().getInHandsIngredient());
                                break;
                            case "Sprites.OnionStation":
                                OnionStation onionTile = (OnionStation) tile;
                                this.gameState.getControlledChef().setInHandsIngredient(onionTile.getIngredient());
                                this.gameState.getControlledChef().setChefSkin(this.gameState.getControlledChef().getInHandsIngredient());
                                break;
                            case "Sprites.SteakStation":
                                SteakStation steakTile = (SteakStation) tile;
                                this.gameState.getControlledChef().setInHandsIngredient(steakTile.getIngredient());
                                this.gameState.getControlledChef().setChefSkin(this.gameState.getControlledChef().getInHandsIngredient());
                                break;
                            case "Sprites.LettuceStation":
                                LettuceStation lettuceTile = (LettuceStation) tile;
                                this.gameState.getControlledChef().setInHandsIngredient(lettuceTile.getIngredient());
                                this.gameState.getControlledChef().setChefSkin(this.gameState.getControlledChef().getInHandsIngredient());
                                break;
                            case "Sprites.PlateStation":
                                if(plateStation.getPlate().size() > 0 || plateStation.getCompletedRecipe() != null){
                                    this.gameState.getControlledChef().pickUpItemFrom(tile);

                                }

                        }
                    } else {
                        switch (tileName) {
                            case "Sprites.Bin":
                                this.gameState.getControlledChef().setInHandsRecipe(null);
                                this.gameState.getControlledChef().setInHandsIngredient(null);
                                this.gameState.getControlledChef().setChefSkin(null);
                                break;

                            case "Sprites.ChoppingBoard":
                                if(this.gameState.getControlledChef().getInHandsIngredient() != null){
                                    if(this.gameState.getControlledChef().getInHandsIngredient().prepareTime > 0){
                                        this.gameState.getControlledChef().setIsControllable(false);
                                    }
                                }
                               break;
                            case "Sprites.PlateStation":
                                if (this.gameState.getControlledChef().getInHandsRecipe() == null){
                                this.gameState.getControlledChef().dropItemOn(tile, this.gameState.getControlledChef().getInHandsIngredient());
                                this.gameState.getControlledChef().setChefSkin(null);
                            }
                                break;
                            case "Sprites.Pan":
                                if(this.gameState.getControlledChef().getInHandsIngredient() != null) {
                                    if (this.gameState.getControlledChef().getInHandsIngredient().isPrepared() && this.gameState.getControlledChef().getInHandsIngredient().cookTime > 0){
                                        this.gameState.getControlledChef().setIsControllable(false);
                                    }
                                }

                                break;
                            case "Sprites.CompletedDishStation":
                                if (this.gameState.getControlledChef().getInHandsRecipe() != null){
                                    if(this.gameState.getControlledChef().getInHandsRecipe().getClass().equals(ordersArray.get(0).recipe.getClass())){
                                        this.gameState.getControlledChef().dropItemOn(tile);
                                        ordersArray.get(0).orderComplete = true;
                                        this.gameState.getControlledChef().setChefSkin(null);
                                        if(ordersArray.size()==1){
                                            scenarioComplete = Boolean.TRUE;
                                        }
                                    }
                                }
                                break;
                        }
                    }

                }
            }
        }

    /**
     * The update method updates the game elements, such as camera and characters,
     * based on a specified time interval "dt".
     * @param dt time interval for the update
    */
    public void update(float dt){
        handleInput(dt);

        //Increment the time
        this.gameState.incrementTime(dt);

        int currentTimeInSeconds = (int) this.gameState.getTime();

        if(currentTimeInSeconds == 5 && ordersArray.size() == 0){
            this.createOrder();
        }

        //update the state of the HUD
        if (this.scenarioComplete){
            this.gameState.getHud().showScenarioComplete();
        }
        this.gameState.getHud().updateTime(currentTimeInSeconds);

        gamecam.update();
        renderer.setView(gamecam);
        // Update the chefs
        for(Chef chef : this.gameState.getChefs()) {
            chef.update(dt);
        }
        world.step(1/60f, 6, 2);

    }

    /**
     * Creates the orders randomly and adds to an array, updates the HUD.
     */
    public void createOrder() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        Texture burger_recipe = new Texture("Food/burger_recipe.png");
        Texture salad_recipe = new Texture("Food/salad_recipe.png");
        Order order;

        for(int i = 0; i<5; i++){
            if(randomNum==1) {
                order = new Order(PlateStation.burgerRecipe, burger_recipe);
            }
            else {
                order = new Order(PlateStation.saladRecipe, salad_recipe);
            }
            ordersArray.add(order);
            randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        }
        this.gameState.getHud().updateOrder(Boolean.FALSE, 1);
    }

    /**
     * Updates the orders as they are completed, or if the game scenario has been completed.
     */
    public void updateOrder(){
        if(scenarioComplete==Boolean.TRUE) {
            this.gameState.getHud().updateScore(Boolean.TRUE, (6 - ordersArray.size()) * 35, (int)this.gameState.getTime());
            this.gameState.getHud().updateOrder(Boolean.TRUE, 0);
            return;
        }
        if(ordersArray.size() != 0) {
            if (ordersArray.get(0).orderComplete) {
                this.gameState.getHud().updateScore(Boolean.FALSE, (6 - ordersArray.size()) * 35, (int)this.gameState.getTime());
                ordersArray.remove(0);
                this.gameState.getHud().updateOrder(Boolean.FALSE, 6 - ordersArray.size());
                return;
            }
            ordersArray.get(0).create(trayX, trayY, game.batch);
        }
    }

    /**

     The render method updates the screen by calling the update method with the given delta time, and rendering the graphics of the game.

     It updates the HUD time, clears the screen, and renders the renderer and the hud.

     Additionally, it checks the state of the game and draws the ingredients, completed recipes, and notifications on the screen.

     @param delta The time in seconds since the last frame.
     */
    @Override
    public void render(float delta){
        update(delta);

        Gdx.gl.glClear(1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        game.batch.setProjectionMatrix(this.gameState.getHud().stage.getCamera().combined);
        this.gameState.getHud().stage.draw();
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        updateOrder();
        gameState.getChefs().get(0).draw(game.batch);
        gameState.getChefs().get(1).draw(game.batch);
        gameState.getChefs().get(2).draw(game.batch);
        this.gameState.getControlledChef().drawIdentifier(game.batch);
        if (plateStation.getPlate().size() > 0){
            for(Object ing : plateStation.getPlate()){
                Ingredient ingNew = (Ingredient) ing;
                ingNew.create(plateStation.getX(), plateStation.getY(),game.batch);
            }
        } else if (plateStation.getCompletedRecipe() != null){
            Recipe recipeNew = plateStation.getCompletedRecipe();
            recipeNew.create(plateStation.getX(), plateStation.getY(), game.batch);
        }

        for(int i = 0; i < this.gameState.getChefs().size(); i++){
            if (!this.gameState.getChefs().get(i).isControllable()) {
                if (this.gameState.getChefs().get(i).getTouchingFixture() != null && this.gameState.getChefs().get(i).getInHandsIngredient() != null){
                    if (this.gameState.getChefs().get(i).getTouchingFixture().getUserData() instanceof InteractiveTileObject){
                        this.gameState.getChefs().get(i).displayIngStatic(game.batch);
                    }
                }
            }

            if (this.gameState.getChefs().get(i).previousInHandRecipe != null){
                this.gameState.getChefs().get(i).displayIngDynamic(game.batch);
            }
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height){
        gameport.update(width, height);
    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){
        
    }

    @Override
    public void hide(){

    }

    @Override
    public void dispose(){
        map.dispose();
        renderer.dispose();
        world.dispose();
        this.gameState.getHud().dispose();
    }
}
