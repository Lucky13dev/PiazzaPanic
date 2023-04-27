package Sprites;

import Ingredients.*;
import Recipe.*;
import Recipe.Recipe;
import Recipe.SaladRecipe;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.team13.piazzapanic.MainGame;

//import jdk.internal.util.SystemProps;

/**
 * Chef class extends {@link Sprite} and represents a chef in the game.
 * It has fields for the world it exists in, a Box2D body, the initial X and Y
 * positions, a wait timer, collision flag, various textures for different skins,
 * state (UP, DOWN, LEFT, RIGHT), skin needed, fixture of what it is touching, ingredient
 * and recipe in hand, control flag, circle sprite, chef notification X, Y, width and height,
 * and completed dish station.
 */

public class Chef extends Sprite{
    private final static Texture TEXTURE_NORMAL_CHEF = new Texture("Chef/Chef_normal.png");
    private final static Texture TEXTURE_BUNS_CHEF = new Texture("Chef/Chef_holding_buns.png");
    private final static Texture TEXTURE_BUNS_TOASTED_CHEF = new Texture("Chef/Chef_holding_buns_toasted.png");
    private final static Texture TEXTURE_BURGER_CHEF = new Texture("Chef/Chef_holding_burger.png");
    private final static Texture TEXTURE_LETTUCE_CHEF = new Texture("Chef/Chef_holding_lettuce.png");
    private final static Texture TEXTURE_ONION_CHEF = new Texture("Chef/Chef_holding_onion.png");
    private final static Texture TEXTURE_TOMATO_CHEF = new Texture("Chef/Chef_holding_tomato.png");
    private final static Texture TEXTURE_CHOPPED_LETTUCE_CHEF = new Texture("Chef/Chef_holding_chopped_lettuce.png");
    private final static Texture TEXTURE_CHOPPED_ONION_CHEF = new Texture("Chef/Chef_holding_chopped_onion.png");
    private final static Texture TEXTURE_CHOPPED_TOMATO_CHEF = new Texture("Chef/Chef_holding_chopped_tomato.png");
    private final static Texture TEXTURE_PATTY_CHEF = new Texture("Chef/Chef_holding_patty.png");
    private final static Texture TEXTURE_COMPLETED_BURGER_CHEF = new Texture("Chef/Chef_holding_front.png");
    private final static Texture TEXTURE_MEAT_CHEF = new Texture("Chef/Chef_holding_meat.png");
    private final static Texture TEXTURE_SALAD_CHEF = new Texture("Chef/Chef_holding_salad.png");
    private final static Texture TEXTURE_PIZZA_BASE_CHEF = new Texture("Chef/Chef_holding_pizza_base.png");
    private final static Texture TEXTURE_CHEESE_CHEF = new Texture("Chef/Chef_holding_cheese.png");
    private final static Texture TEXTURE_POTATO_CHEF = new Texture("Chef/Chef_holding_potato.png");
    private final static Texture TEXTURE_RAW_PIZZA_CHEF = new Texture("Chef/Chef_holding_raw_pizza.png");
    private final static Texture TEXTURE_COOKED_PIZZA_CHEF = new Texture("Chef/Chef_holding_cooked_pizza.png");
    private final static Texture TEXTURE_SLICED_POTATO_CHEF = new Texture("Chef/Chef_holding_sliced_potato.png");
    private final static Texture TEXTURE_COOKED_POTATO_CHEF = new Texture("Chef/Chef_holding_cooked_potato.png");
    private final static Texture TEXTURE_JACKET_POTATO_CHEF = new Texture("Chef/Chef_holding_baked_potato.png");
    private static class Identifier{
        public Sprite sprite;
        private Identifier(Orientation orientation){
            Texture identifierTexture = new Texture("Chef/chefIdentifier.png");
            this.sprite = new Sprite(identifierTexture);
            this.setOrientation(orientation);
        }
        private void setOrientation(Orientation orientation) {

            float width = 0;
            float height = 0;
            switch (orientation) {
                case LEFT:
                case RIGHT:
                    width = 1.5f / MainGame.PPM;
                    height = 1.5f / MainGame.PPM;
                    break;
                case UP:
                    width = 4 / MainGame.PPM;
                    height = 4 / MainGame.PPM;
                    break;
                case DOWN:
                    width = 2 / MainGame.PPM;
                    height = 2 / MainGame.PPM;
                    break;
            }
            this.sprite.setBounds(this.sprite.getX(), this.sprite.getY(), width, height);
        }
        private void setPosition(float x, float y){
            this.sprite.setPosition(x, y);
        }

    }
    private final Identifier identifier;

    public World world;
    public Body b2body;

    public Vector2 startVector;
    private float waitTimer;

    private float putDownWaitTimer;

    private boolean chefOnChefCollision;

    public enum Orientation {UP, DOWN, LEFT, RIGHT}

    private Orientation orientation;

    private Texture currentTexture;

    private Fixture touchingFixture;

    private Ingredient inHandsIngredient;
    private Recipe inHandsRecipe;

    private Boolean isControllable;

    private CompletedDishStation completedStation;

    public Recipe previousInHandRecipe;

    /**
     * Chef class constructor that initializes all the fields
     * @param world the world the chef exists in
     * @param startX starting X position
     * @param startY starting Y position
     */

    public Chef(float startX, float startY) {
        this.currentTexture = TEXTURE_NORMAL_CHEF;

        this.world = world;
        this.orientation = Orientation.DOWN;

        //this.defineChef(world, startX/MainGame.PPM, startY/MainGame.PPM);

        float chefWidth = 13 / MainGame.PPM;
        float chefHeight = 20 / MainGame.PPM;
        this.setBounds(0, 0, chefWidth, chefHeight);
        this.chefOnChefCollision = false;
        this.waitTimer = 0;
        this.putDownWaitTimer = 0;
        this.startVector = new Vector2(0, 0);
        this.touchingFixture = null;
        this.inHandsIngredient = null;
        this.inHandsRecipe = null;
        this.isControllable = true;

        this.identifier = new Identifier(this.orientation);

        this.completedStation = null;
    }


    /**
     * Update the position and region of the chef and set the notification position based on the chef's current state.
     *
     * @param dt The delta time.
     */
    public void update(float dt) {
        this.setPosition(this.b2body.getPosition().x - getWidth() / 2, this.b2body.getPosition().y - getHeight() / 2);

        //Update orientation based on the current velocity
        if (b2body.getLinearVelocity().y > 0)
            this.orientation = Orientation.UP;
        if (b2body.getLinearVelocity().y < 0)
            this.orientation = Orientation.DOWN;
        if (b2body.getLinearVelocity().x > 0)
            this.orientation = Orientation.RIGHT;
        if (b2body.getLinearVelocity().x < 0)
            this.orientation = Orientation.LEFT;

        TextureRegion currentSkin = this.getSkin();
        this.setRegion(currentSkin);

        //update the C identifier on chef
        float identifierX, identifierY;
        float chefX = this.b2body.getPosition().x;
        float chefY = this.b2body.getPosition().y;
        boolean emptyHands = (this.inHandsIngredient == null && this.inHandsRecipe == null);

        switch (this.orientation) {
            case UP:
                if (emptyHands) {
                    identifierX = chefX-(1.75f/MainGame.PPM);
                    identifierY = chefY-(7.7f/MainGame.PPM);
                } else {
                    identifierX = chefX-(0.67f/MainGame.PPM);
                    identifierY = chefY-(7.2f/MainGame.PPM);
                }
                break;
            case DOWN:
                if (emptyHands) {
                    identifierX = chefX+(0.95f/MainGame.PPM);
                    identifierY = chefY-(5.015f/MainGame.PPM);
                } else {
                    identifierX = chefX+(0.55f/MainGame.PPM);
                    identifierY = chefY-(5.3f/MainGame.PPM);
                }
                break;
            case LEFT:
                if (emptyHands) {
                    identifierX = chefX;
                    identifierY = chefY-(5.015f/MainGame.PPM);
                } else {
                    identifierX = chefX-(1.92f/MainGame.PPM);
                    identifierY = chefY-(4.6f/MainGame.PPM);
                }
                break;
            case RIGHT:
                if (emptyHands) {
                    identifierX = chefX+(0.5f / MainGame.PPM);
                    identifierY = chefY-(5.015f / MainGame.PPM);
                } else {
                    identifierX = chefX+(0.17f / MainGame.PPM);
                    identifierY = chefY-(4.63f / MainGame.PPM);
                }
                break;
            default: //this should never happen
                identifierX = this.identifier.sprite.getX();
                identifierY = this.identifier.sprite.getY();
        }
        this.identifier.setPosition(identifierX, identifierY);
        this.identifier.setOrientation(this.orientation);

        //handle the chef when it is not controllable
        if (!this.isControllable){
            this.waitTimer += dt;

            //handle chefs colliding
            if (this.chefOnChefCollision) {
                this.b2body.setLinearVelocity(new Vector2(startVector.x * -1, startVector.y * -1));
                if (this.waitTimer > 0.3f) { //end the collision
                    this.b2body.setLinearVelocity(new Vector2(0, 0));
                    this.chefOnChefCollision = false;
                    this.isControllable = true;
                    this.waitTimer = 0;
                    if (inHandsIngredient != null) {
                        this.setChefSkin(inHandsIngredient);
                    }
                }

            //handle preparing ingredients
            } else if (this.inHandsIngredient.prepareTime > 0) {
                if (this.waitTimer > this.inHandsIngredient.prepareTime) { //the ingredient preparation is finished
                    this.inHandsIngredient.prepareTime = 0;
                    this.inHandsIngredient.setPrepared();
                    this.isControllable = true;
                    this.waitTimer = 0;
                    this.setChefSkin(inHandsIngredient);
                }

            //handle cooking ingredients
            } else if (this.inHandsIngredient.isPrepared() && this.inHandsIngredient.cookTime > 0) {
                if (this.waitTimer > this.inHandsIngredient.cookTime) {
                    this.inHandsIngredient.cookTime = 0;
                    this.inHandsIngredient.setCooked();
                    this.isControllable = true;
                    this.waitTimer = 0;
                    this.setChefSkin(inHandsIngredient);

                }
            }
            //handle baking ingredients
            else if (this.inHandsIngredient.bakeTime > 0 && this.inHandsIngredient.isCooked()) {
                if (this.waitTimer > this.inHandsIngredient.bakeTime) { //the ingredient preparation is finished
                    this.inHandsIngredient.bakeTime = 0;
                    this.inHandsIngredient.setBaked();
                    this.isControllable = true;
                    this.waitTimer = 0;
                    this.setChefSkin(inHandsIngredient);
                    // Chain recipies
                    if(this.inHandsIngredient instanceof RawPizza){
                        this.setInHandsIngredient(null);
                        this.setInHandsRecipe(new CookedPizzaRecipe());
                        this.setChefSkin(this.inHandsRecipe);
                        System.out.println("Pizza complete");
                    }
                }
            }
        }
    }

    /**
     Draws a notification to help the user understand what chef they are controlling.
     The notification is a sprite that looks like at "C" on the controlled chef.
     @param batch The sprite batch that the notification should be drawn with.
     */
    public void drawIdentifier(SpriteBatch batch) {
        if (this.isControllable) {
            this.identifier.sprite.draw(batch);
        }
    }

    /**
     * Get the texture region for the current state of the player.
     *
     * @return the texture region for the player's current state
     */

    private TextureRegion getSkin() {
        switch (this.orientation) {
            case UP:
                return new TextureRegion(this.currentTexture, 0, 0, 33, 46);
            case DOWN:
                return new TextureRegion(this.currentTexture, 33, 0, 33, 46);
            case LEFT:
                return new TextureRegion(this.currentTexture, 64, 0, 33, 46);
            case RIGHT:
                return new TextureRegion(this.currentTexture, 96, 0, 33, 46);
            default:
                return null;
        }
    }

    /**
     * Define the body and fixture of the chef object.*
     * This method creates a dynamic body definition and sets its position with the `initialX` and `initialY`
     * variables, then creates the body in the physics world. A fixture definition is also created and a
     * circle shape is set with a radius of `4.5f / MainGame. PPM` and a position shifted by `(0.5f / MainGame.PPM)`
     * in the x-axis and `-(5.5f / MainGame.PPM)` in the y-axis. The created fixture is then set as the user data
     * of the chef object.
     */

    public void defineChef(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        CircleShape shape = new CircleShape();
        shape.setRadius(4.5f / MainGame.PPM);
        shape.setPosition(new Vector2(shape.getPosition().x + (0.5f / MainGame.PPM), shape.getPosition().y - (5.5f / MainGame.PPM)));

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        this.b2body = world.createBody(bodyDef);
        this.b2body.createFixture(fixtureDef).setUserData(this);
    }


    /**
     * Method to set the skin of the chef character based on the item the chef is holding.
     *
     * @param item the item that chef is holding*
     * The skin is set based on the following cases:
     * - if item is null, then the skin is set to normalChef
     * - if item is a Lettuce, then the skin is set to
     *    - choppedLettuceChef if the lettuce is prepared
     *    - lettuceChef if the lettuce is not prepared
     * - if item is a Steak, then the skin is set to
     *    - burgerChef if the steak is prepared and cooked
     *    - pattyChef if the steak is prepared but not cooked
     *    - meatChef if the steak is not prepared
     * - if item is an Onion, then the skin is set to
     *    - choppedOnionChef if the onion is prepared
     *    - onionChef if the onion is not prepared
     * - if item is a Tomato, then the skin is set to
     *    - choppedTomatoChef if the tomato is prepared
     *    - tomatoChef if the tomato is not prepared
     * - if item is a Bun, then the skin is set to
     *    - bunsToastedChef if the bun is cooked
     *    - bunsChef if the bun is not cooked
     * - if item is a BurgerRecipe, then the skin is set to completedBurgerChef
     * - if item is a SaladRecipe, then the skin is set to saladChef
     */

    public void setChefSkin(Object item) {
        if (item == null) {
            this.currentTexture = TEXTURE_NORMAL_CHEF;
        } else if (item instanceof Lettuce) {
            if (inHandsIngredient.isPrepared()) {
                this.currentTexture = TEXTURE_CHOPPED_LETTUCE_CHEF;
            } else {
                this.currentTexture = TEXTURE_LETTUCE_CHEF;
            }
        } else if (item instanceof Steak) {
            if (inHandsIngredient.isPrepared() && inHandsIngredient.isCooked()) {
                this.currentTexture = TEXTURE_BURGER_CHEF;
            } else if (inHandsIngredient.isPrepared()) {
                this.currentTexture = TEXTURE_PATTY_CHEF;
            } else {
                this.currentTexture = TEXTURE_MEAT_CHEF;
            }
        } else if (item instanceof Onion) {
            if (inHandsIngredient.isPrepared()) {
                this.currentTexture = TEXTURE_CHOPPED_ONION_CHEF;
            } else {
                this.currentTexture = TEXTURE_ONION_CHEF;
            }
        } else if (item instanceof Tomato) {
            if (inHandsIngredient.isPrepared()) {
                this.currentTexture = TEXTURE_CHOPPED_TOMATO_CHEF;
            } else {
                this.currentTexture = TEXTURE_TOMATO_CHEF;
            }
        } else if (item instanceof Bun) {
            if (inHandsIngredient.isCooked()) {
                this.currentTexture = TEXTURE_BUNS_TOASTED_CHEF;
            } else {
                this.currentTexture = TEXTURE_BUNS_CHEF;
            }
        } else if (item instanceof BurgerRecipe) {
            this.currentTexture = TEXTURE_COMPLETED_BURGER_CHEF;
        } else if (item instanceof SaladRecipe) {
            this.currentTexture = TEXTURE_SALAD_CHEF;
        } else if (item instanceof PizzaBase) {
            this.currentTexture = TEXTURE_PIZZA_BASE_CHEF;
        } else if (item instanceof Potato){
            if (inHandsIngredient.isBaked()){
               this.setInHandsIngredient(new CookedPotato(2, 0,0));
               this.currentTexture = TEXTURE_COOKED_POTATO_CHEF;
            }
            else this.currentTexture = TEXTURE_POTATO_CHEF;
        } else if(item instanceof Cheese){
            this.currentTexture = TEXTURE_CHEESE_CHEF;
        } else if (item instanceof RawPizza){
            this.currentTexture = TEXTURE_RAW_PIZZA_CHEF;
        } else if (item instanceof CookedPizzaRecipe) {
            this.currentTexture = TEXTURE_COOKED_PIZZA_CHEF;
        } else if (item instanceof CookedPotato){
            this.currentTexture = TEXTURE_SLICED_POTATO_CHEF;
        } else if (item instanceof JacketPotatoRecipe){
            this.currentTexture = TEXTURE_JACKET_POTATO_CHEF;
        }
    }

    /**
     * Method to display the ingredient on the specific interactive tile objects (ChoppingBoard/Pan)
     * @param batch the SpriteBatch used to render the texture.
     */

    public void displayIngStatic(SpriteBatch batch) {
        Gdx.app.log("", inHandsIngredient.toString());
        if (touchingFixture != null && !chefOnChefCollision) {
            InteractiveTileObject tile = (InteractiveTileObject) touchingFixture.getUserData();
            if (tile instanceof ChoppingBoard) {
                ChoppingBoard tileNew = (ChoppingBoard) tile;
                inHandsIngredient.create(tileNew.getX() - (0.5f / MainGame.PPM), tileNew.getY() - (0.2f / MainGame.PPM), batch);
                setChefSkin(null);
            } else if (tile instanceof Pan) {
                Pan tileNew = (Pan) tile;
                inHandsIngredient.create(tileNew.getX(), tileNew.getY() - (0.01f / MainGame.PPM), batch);
                setChefSkin(null);
            } else if (tile instanceof Oven){
                Oven tileNew = (Oven) tile;
                inHandsIngredient.create(tileNew.getX(), tileNew.getY() + (7.5f / MainGame.PPM), batch);
                setChefSkin(null);
            }
        }
    }

    /**
     * The method creates an instance of the recipe and sets its position on the completed station coordinates.
     * The method also implements a timer for each ingredient which gets removed from the screen after a certain amount of time.
     *
     * @param batch The batch used for drawing the sprite on the screen
     */

    public void displayIngDynamic(SpriteBatch batch){
        putDownWaitTimer += 1/60f;
        previousInHandRecipe.create(completedStation.getX(), completedStation.getY() - (0.01f / MainGame.PPM), batch);
        int nextOrderAppearTime = 3;
        if (putDownWaitTimer > nextOrderAppearTime) {
            previousInHandRecipe = null;
            putDownWaitTimer = 0;
        }
    }

    /**

      * This method updates the state of the chef when it is in a collision with another chef.
      * The method sets the userControlChef to false, meaning the user cannot control the chef while it's in collision.
      * It also sets the chefOnChefCollision to true, indicating that the chef is in collision with another chef.
     */
    public void chefsColliding() {
        this.isControllable = false;
        this.chefOnChefCollision = true;
        this.startVector = new Vector2(b2body.getLinearVelocity().x, b2body.getLinearVelocity().y);
    }

    /**
     * Set the touching tile fixture
     *
     * @param fixture fixture that the chef is touching
     */
    public void setTouchingTile (Fixture fixture){
        this.touchingFixture = fixture;
    }

    /**
     * Get the fixture that the chef is touching
     *
     * @return the fixture that the chef is touching
     */
    public Fixture getTouchingFixture() {
        return this.touchingFixture;
    }

    /**
     * Get the ingredient that the chef is holding
     *
     * @return the ingredient that the chef is holding
     */
    public Ingredient getInHandsIngredient() {
        return this.inHandsIngredient;
    }

    /**
     * Get the recipe that the chef is holding
     *
     * @return the recipe that the chef is holding
     */
    public Recipe getInHandsRecipe () {
        return this.inHandsRecipe;
    }

    /**
     * Set the ingredient that the chef is holding
     *
     * @param ing the ingredient that the chef is holding
     */
    public void setInHandsIngredient(Ingredient ing){
        this.inHandsIngredient = ing;
        this.inHandsRecipe = null;
    }

    /**
     * Set the recipe that the chef is holding
     *
     * @param recipe the recipe that the chef is holding
     */
    public void setInHandsRecipe (Recipe recipe){
        this.inHandsRecipe = recipe;
        this.inHandsIngredient = null;
    }

    /**
     * Set the chef's control by the user
     *
     * @param controllable whether the chef is controlled by the user
     */
    public void setIsControllable(boolean controllable){
        this.isControllable = controllable;
    }

    /**

     * Returns a boolean value indicating whether the chef is controllable by the user.
     * If not specified, returns false.
     *
     * @return isControllable The boolean value indicating if the chef is controllable.
     */
    public boolean isControllable() {
        if (this.isControllable == null){
            return false;
        }
        return this.isControllable;
    }


    /**
      * Drops the given ingredient on a plate station.
      * @param station The plate station to drop the ingredient on.
      * @param ing The ingredient to be dropped.
     */

    public void dropItemOn (InteractiveTileObject station, Ingredient ing){
        if (station instanceof PlateStation) {
                ((PlateStation) station).dropItem(ing);
        }
        this.setInHandsRecipe(null);
    }

    /**
     * Drops the in-hand recipe on a completed dish station and saves the previous in-hand recipe.
     *
     * @param station The completed dish station to drop the recipe on.
     */
        public void dropItemOn (InteractiveTileObject station){
            if (station instanceof CompletedDishStation) {
                previousInHandRecipe = getInHandsRecipe();
                completedStation = (CompletedDishStation) station;
            }
            this.setInHandsRecipe(null);
        }

    /**
     * Picks up an item from a plate station and sets it as in-hand ingredient or recipe.
     *
     * @param station The plate station to pick up the item from.
     */
    public void pickUpItemFrom(InteractiveTileObject station){
        if (station instanceof PlateStation){
            PlateStation pStation = (PlateStation) station;
            Object item = pStation.pickUpItem();
            if (item instanceof Ingredient){
                this.setInHandsIngredient((Ingredient) item);
                this.setChefSkin(item);
            } else if (item instanceof Recipe){
                // Pizza is a chain recipe
                if (item instanceof RawPizzaRecipe){
                    RawPizza pizza = new RawPizza(0, 0, 2);
                    this.setInHandsIngredient(pizza);
                    this.setChefSkin(pizza);
                    System.out.println("Yoizza");
                }
                else {
                    this.setInHandsRecipe(((Recipe) item));
                    this.setChefSkin(item);
                }
            }
        }
    }

    public boolean isChefOnChefCollision() {
        return chefOnChefCollision;
    }
}