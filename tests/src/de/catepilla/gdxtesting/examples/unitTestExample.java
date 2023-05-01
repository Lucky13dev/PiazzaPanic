

package de.catepilla.gdxtesting.examples;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import Sprites.Chef;
import Tools.B2WorldCreator;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.team13.piazzapanic.GameState;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.team13.piazzapanic.MainGame;
import de.catepilla.gdxtesting.GdxTestRunner;
import jdk.tools.jmod.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.team13.piazzapanic.PlayScreen;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
/** NB:
 * Private method setOrientation() is for implementation only and does not need to be tested.
 */

@RunWith(GdxTestRunner.class)
public class unitTestExample {

	/**
	 * Tests on the Chef constructor class's fields.
	 */
	@Test
	public void ChefWidthTest() {
		Chef chef = new Chef( 0, 0);
		assertEquals(chef.getWidth(),13 / MainGame.PPM, 0);
	}
	@Test
	public void ChefHeightTest() {
		Chef chef = new Chef( 0, 0);
		assertEquals(chef.getHeight(),20 / MainGame.PPM, 0);
	}

	//Not sure on how to compare the getter value to the chef instance

	@Test
	public void ChefSetBoundsTest() {
		Chef chef = new Chef(0,0);
		chef.getBoundingRectangle();

		assertEquals(chef.getBoundingRectangle().x, 0,0);
		assertEquals(chef.getBoundingRectangle().x, 0,0);
		assertEquals(chef.getBoundingRectangle().y,0,0);
		assertEquals(chef.getBoundingRectangle().height,chef.getHeight(),0);
		assertEquals(chef.getBoundingRectangle().width,chef.getWidth(),0);

	}
	@Test
	public void ChefOnChefCollisionTest() {
		Chef chef = new Chef( 0, 0);

		assertEquals(chef.isChefOnChefCollision(), false);
	}
	@Test
	public void ChefV2Test() { //TODO fix later
		Chef chef = new Chef(0, 0);

		assertEquals(chef.startVector, new Vector2(0, 0));
	}
	@Test
	public void ChefTouchingFixtureTest() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.getTouchingFixture(),null);
	}
	@Test
	public void ChefInHandsIngredientTest() {
		Chef chef = new Chef( 0, 0);

		assertEquals(chef.getInHandsIngredient(),null);
	}
	@Test
	public void ChefInHandsRecipeTest() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.getInHandsRecipe(),null);
	}
	@Test
	public void ChefIsControllableTest() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.isControllable(), true);
	}
}