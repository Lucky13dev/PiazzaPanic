package Sprites;

import Ingredients.*;
import Recipe.BurgerRecipe;
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

/**
 * Chef class extends {@link Sprite} and represents a chef in the game.
 * It has fields for the world it exists in, a Box2D body, the initial X and Y
 * positions, a wait timer, collision flag, various textures for different skins,
 * state (UP, DOWN, LEFT, RIGHT), skin needed, fixture of what it is touching, ingredient
 * and recipe in hand, control flag, circle sprite, chef notification X, Y, width and height,
 * and completed dish station.
 */

public class Chef extends Sprite{
    private static class Identifier{
        public Sprite sprite;

        private Identifier(){
            Texture identifierTexture = new Texture("Chef/chefIdentifier.png");
            this.sprite = new Sprite(identifierTexture);
            this.setOrientation(Orientation.DOWN);
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

    }
    private final Identifier identifier;

    public World world;
    public Body b2body;

    public Vector2 startVector;
    private float waitTimer;

    private float putDownWaitTimer;
    public boolean chefOnChefCollision;
    private final Texture normalChef = new Texture("Chef/Chef_normal.png");
    private final Texture bunsChef = new Texture("Chef/Chef_holding_buns.png");
    private final Texture bunsToastedChef = new Texture("Chef/Chef_holding_buns_toasted.png");
    private final Texture burgerChef = new Texture("Chef/Chef_holding_burger.png");
    private final Texture lettuceChef = new Texture("Chef/Chef_holding_lettuce.png");
    private final Texture onionChef = new Texture("Chef/Chef_holding_onion.png");
    private final Texture tomatoChef = new Texture("Chef/Chef_holding_tomato.png");
    private final Texture choppedLettuceChef = new Texture("Chef/Chef_holding_chopped_lettuce.png");
    private final Texture choppedOnionChef = new Texture("Chef/Chef_holding_chopped_onion.png");
    private final Texture choppedTomatoChef = new Texture("Chef/Chef_holding_chopped_tomato.png");
    private final Texture pattyChef = new Texture("Chef/Chef_holding_patty.png");
    private final Texture completedBurgerChef = new Texture("Chef/Chef_holding_front.png");
    private final Texture meatChef = new Texture("Chef/Chef_holding_meat.png");
    private final Texture saladChef = new Texture("Chef/Chef_holding_salad.png");

    public enum Orientation {UP, DOWN, LEFT, RIGHT}

    public Orientation currentOrientation;

    private Texture currentTexture;

    private Fixture whatTouching;

    private Ingredient inHandsIng;
    private Recipe inHandsRecipe;

    private Boolean isSelectedChef;
    private float identifierX;
    private float identifierY;

    private CompletedDishStation completedStation;

    public int nextOrderAppearTime;
    public Recipe previousInHandRecipe;

    /**
     * Chef class constructor that initializes all the fields
     * @param world the world the chef exists in
     * @param startX starting X position
     * @param startY starting Y position
     */

    public Chef(World world, float startX, float startY) {
        currentTexture = normalChef;

        this.world = world;
        this.currentOrientation = Orientation.DOWN;

        float initialX = startX / MainGame.PPM;
        float initialY = startY / MainGame.PPM;
        this.defineChef(initialX, initialY);

        float chefWidth = 13 / MainGame.PPM;
        float chefHeight = 20 / MainGame.PPM;
        setBounds(0, 0, chefWidth, chefHeight);
        chefOnChefCollision = false;
        waitTimer = 0;
        putDownWaitTimer = 0;
        startVector = new Vector2(0, 0);
        whatTouching = null;
        inHandsIng = null;
        inHandsRecipe = null;
        isSelectedChef = true;

        this.identifier = new Identifier();
        this.identifier.setOrientation(Orientation.DOWN);

        nextOrderAppearTime = 3;
        completedStation = null;
    }


    /**
     * Update the position and region of the chef and set the notification position based on the chef's current state.
     *
     * @param dt The delta time.
     */
    public void update(float dt) {
        this.setPosition(this.b2body.getPosition().x - getWidth() / 2, this.b2body.getPosition().y - getHeight() / 2);

        TextureRegion currentSkin = this.getSkin();
        this.setRegion(currentSkin);

        switch (currentOrientation) {
            case UP:
                if (this.inHandsIng == null && this.inHandsRecipe == null) {
                    this.identifierX = b2body.getPosition().x - (1.75f / MainGame.PPM);
                    this.identifierY = b2body.getPosition().y - (7.7f / MainGame.PPM);
                } else {
                    this.identifierX = b2body.getPosition().x - (0.67f / MainGame.PPM);
                    this.identifierY = b2body.getPosition().y - (7.2f / MainGame.PPM);
                }
                break;
            case DOWN:
                if (this.inHandsIng == null && this.inHandsRecipe == null) {
                    this.identifierX = b2body.getPosition().x + (0.95f / MainGame.PPM);
                    this.identifierY = b2body.getPosition().y - (5.015f / MainGame.PPM);
                } else {
                    this.identifierX = b2body.getPosition().x + (0.55f / MainGame.PPM);
                    this.identifierY = b2body.getPosition().y - (5.3f / MainGame.PPM);
                }
                break;
            case LEFT:
                if (this.inHandsIng == null && this.inHandsRecipe == null) {
                    this.identifierX = b2body.getPosition().x;
                    this.identifierY = b2body.getPosition().y - (5.015f / MainGame.PPM);
                } else {
                    this.identifierX = b2body.getPosition().x - (1.92f / MainGame.PPM);
                    this.identifierY = b2body.getPosition().y - (4.6f / MainGame.PPM);
                }
                break;
            case RIGHT:
                if (this.inHandsIng == null && this.inHandsRecipe == null) {
                    this.identifierX = b2body.getPosition().x + (0.5f / MainGame.PPM);
                    this.identifierY = b2body.getPosition().y - (5.015f / MainGame.PPM);
                } else {
                    this.identifierX = b2body.getPosition().x + (0.17f / MainGame.PPM);
                    this.identifierY = b2body.getPosition().y - (4.63f / MainGame.PPM);
                }
                break;
        }


        if (!isSelectedChef && chefOnChefCollision) {
            waitTimer += dt;
            b2body.setLinearVelocity(new Vector2(startVector.x * -1, startVector.y * -1));
            if (waitTimer > 0.3f) {
                b2body.setLinearVelocity(new Vector2(0, 0));
                chefOnChefCollision = false;
                isSelectedChef = true;
                waitTimer = 0;
                if (inHandsIng != null) {
                    setChefSkin(inHandsIng);
                }
            }
        } else if (!isSelectedChef && getInHandsIng().prepareTime > 0) {
            waitTimer += dt;
            if (waitTimer > inHandsIng.prepareTime) {
                inHandsIng.prepareTime = 0;
                inHandsIng.setPrepared();
                isSelectedChef = true;
                waitTimer = 0;
                setChefSkin(inHandsIng);
            }
        } else if (!isSelectedChef && !chefOnChefCollision && getInHandsIng().isPrepared() && inHandsIng.cookTime > 0) {
            waitTimer += dt;
            if (waitTimer > inHandsIng.cookTime) {
                inHandsIng.cookTime = 0;
                inHandsIng.setCooked();
                isSelectedChef = true;
                waitTimer = 0;
                setChefSkin(inHandsIng);
            }
        }
    }

    /**
     Draws a notification to help the user understand what chef they are controlling.
     The notification is a sprite that looks like at "C" on the controlled chef.
     @param batch The sprite batch that the notification should be drawn with.
     */
    public void drawIdentifier(SpriteBatch batch) {
        if (this.getIsSelectedChef()) {
            this.identifier.sprite.setX(identifierX);
            this.identifier.sprite.setY(identifierY);
            this.identifier.setOrientation(this.getOrientation());
            this.identifier.sprite.draw(batch);
        }
    }

    /**
     * Get the texture region for the current state of the player.
     *
     * @return the texture region for the player's current state
     */

    private TextureRegion getSkin() {
        currentOrientation = getOrientation();

        switch (currentOrientation) {
            case UP:
                return new TextureRegion(currentTexture, 0, 0, 33, 46);
            case DOWN:
                return new TextureRegion(currentTexture, 33, 0, 33, 46);
            case LEFT:
                return new TextureRegion(currentTexture, 64, 0, 33, 46);
            case RIGHT:
                return new TextureRegion(currentTexture, 96, 0, 33, 46);
            default:
                return null;
        }
    }


    /**
     Returns the current state of the player based on the controlled chef's velocity.
     @return current state of the player - UP, DOWN, LEFT, or RIGHT
     */
    public Orientation getOrientation() {
        if (b2body.getLinearVelocity().y > 0)
            return Orientation.UP;
        if (b2body.getLinearVelocity().y < 0)
            return Orientation.DOWN;
        if (b2body.getLinearVelocity().x > 0)
            return Orientation.RIGHT;
        if (b2body.getLinearVelocity().x < 0)
            return Orientation.LEFT;
        else
            return this.currentOrientation;
    }

    /**
     * Define the body and fixture of the chef object.*
     * This method creates a dynamic body definition and sets its position with the `initialX` and `initialY`
     * variables, then creates the body in the physics world. A fixture definition is also created and a
     * circle shape is set with a radius of `4.5f / MainGame. PPM` and a position shifted by `(0.5f / MainGame.PPM)`
     * in the x-axis and `-(5.5f / MainGame.PPM)` in the y-axis. The created fixture is then set as the user data
     * of the chef object.
     */

    public void defineChef(float x, float y) {
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
            currentTexture = normalChef;
        } else if (item instanceof Lettuce) {
            if (inHandsIng.isPrepared()) {
                currentTexture = choppedLettuceChef;
            } else {
                currentTexture = lettuceChef;
            }
        } else if (item instanceof Steak) {
            if (inHandsIng.isPrepared() && inHandsIng.isCooked()) {
                currentTexture = burgerChef;
            } else if (inHandsIng.isPrepared()) {
                currentTexture = pattyChef;
            } else {
                currentTexture = meatChef;
            }
        } else if (item instanceof Onion) {
            if (inHandsIng.isPrepared()) {
                currentTexture = choppedOnionChef;
            } else {
                currentTexture = onionChef;
            }
        } else if (item instanceof Tomato) {
            if (inHandsIng.isPrepared()) {
                currentTexture = choppedTomatoChef;
            } else {
                currentTexture = tomatoChef;
            }
        } else if (item instanceof Bun) {
            if (inHandsIng.isCooked()) {
                currentTexture = bunsToastedChef;
            } else {
                currentTexture = bunsChef;
            }
        } else if (item instanceof BurgerRecipe) {
            currentTexture = completedBurgerChef;
        } else if (item instanceof SaladRecipe) {
            currentTexture = saladChef;
        }
    }

    /**
     * Method to display the ingredient on the specific interactive tile objects (ChoppingBoard/Pan)
     * @param batch the SpriteBatch used to render the texture.
     */

    public void displayIngStatic(SpriteBatch batch) {
        Gdx.app.log("", inHandsIng.toString());
        if (whatTouching != null && !chefOnChefCollision) {
            InteractiveTileObject tile = (InteractiveTileObject) whatTouching.getUserData();
            if (tile instanceof ChoppingBoard) {
                ChoppingBoard tileNew = (ChoppingBoard) tile;
                inHandsIng.create(tileNew.getX() - (0.5f / MainGame.PPM), tileNew.getY() - (0.2f / MainGame.PPM), batch);
                setChefSkin(null);
            } else if (tile instanceof Pan) {
                Pan tileNew = (Pan) tile;
                inHandsIng.create(tileNew.getX(), tileNew.getY() - (0.01f / MainGame.PPM), batch);
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
        if (putDownWaitTimer > nextOrderAppearTime) {
            previousInHandRecipe = null;
            putDownWaitTimer = 0;
        }
    }

    /**

      * This method updates the state of the chef when it is in a collision with another chef.
      * The method sets the userControlChef to false, meaning the user cannot control the chef while it's in collision.
      * It also sets the chefOnChefCollision to true, indicating that the chef is in collision with another chef.
      * Finally, it calls the setStartVector method to update the position of the chef.
     */
        public void chefsColliding () {
            isSelectedChef = false;
            chefOnChefCollision = true;
            setStartVector();
        }

    /**
     * Set the starting velocity vector of the chef
     * when the chef collides with another chef
     *
     */
    public void setStartVector () {
        startVector = new Vector2(b2body.getLinearVelocity().x, b2body.getLinearVelocity().y);
    }

    /**
     * Set the touching tile fixture
     *
     * @param obj fixture that the chef is touching
     */
    public void setTouchingTile (Fixture obj){
        this.whatTouching = obj;
    }

    /**
     * Get the fixture that the chef is touching
     *
     * @return the fixture that the chef is touching
     */
    public Fixture getTouchingTile () {
        if (whatTouching == null) {
            return null;
        } else {
            return whatTouching;
        }
    }

    /**
     * Get the ingredient that the chef is holding
     *
     * @return the ingredient that the chef is holding
     */
    public Ingredient getInHandsIng () {
        return inHandsIng;
    }

    /**
     * Get the recipe that the chef is holding
     *
     * @return the recipe that the chef is holding
     */
    public Recipe getInHandsRecipe () {
        return inHandsRecipe;
    }

    /**
     * Set the ingredient that the chef is holding
     *
     * @param ing the ingredient that the chef is holding
     */
    public void setInHandsIng (Ingredient ing){
        inHandsIng = ing;
        inHandsRecipe = null;
    }

    /**
     * Set the recipe that the chef is holding
     *
     * @param recipe the recipe that the chef is holding
     */
    public void setInHandsRecipe (Recipe recipe){
        inHandsRecipe = recipe;
        inHandsIng = null;
    }

    /**
     * Set the chef's control by the user
     *
     * @param value whether the chef is controlled by the user
     */
    public void setIsSelectedChef(boolean value){
        isSelectedChef = value;

    }

    /**

     * Returns a boolean value indicating whether the chef is under user control.
     * If not specified, returns false.
     *
     * @return userControlChef The boolean value indicating chef's control.
     */
    public boolean getIsSelectedChef() {
        if (this.isSelectedChef == null){
            return false;
        }
        return this.isSelectedChef;
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
        setInHandsRecipe(null);
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
            setInHandsRecipe(null);
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
                setInHandsIng((Ingredient) item);
                setChefSkin(item);
            } else if (item instanceof Recipe){
                setInHandsRecipe(((Recipe) item));
                setChefSkin(item);
            }
        }
    }
}



