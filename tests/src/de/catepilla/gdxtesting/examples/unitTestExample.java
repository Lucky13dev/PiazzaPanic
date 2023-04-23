

package de.catepilla.gdxtesting.examples;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import Ingredients.Ingredient;
import Recipe.Recipe;
import Sprites.Chef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.team13.piazzapanic.GameState;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.team13.piazzapanic.MainGame;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/** NB:
 * Private method setOrientation() is for implementation only and does not need to be tested.
 */

//@RunWith(GdxTestRunner.class)
public class unitTestExample {

	@Test
	public void TestMaxChefs() {
		GameState gameState = new GameState();
		int max_count = GameState.getMAX_CHEF_COUNT();
		int expected_max_count = 5;
		assertEquals(expected_max_count, max_count);
	}

	/**
	 * Tests on the Chef constructor class's fields.
	 */




	/**
	 * TODO:Currently not working due to World not being initialised.
	 */
	@Test
	public void ChefWidthTest() {
		World world;
		world =new World(new Vector2(0,0), true);
		Chef chef = new Chef(world, 0, 0);

		assertEquals(chef.getWidth(),13 / MainGame.PPM);
	}
	@Test
	public void ChefHeightTest() {
		World world;
		world =new World(new Vector2(0,0), true);
		Chef chef = new Chef(world, 0, 0);

		assertEquals(chef.getHeight(),20 / MainGame.PPM);
	}

	//Not sure on how to compare the getter value to the chef instance

//	@Test
//	public void ChefSetBoundsTest() {
//		World world;
//		world =new World(new Vector2(0,0), true);
//		Chef chef = new Chef(world, 0, 0);
//
//		assertArrayEquals(chef.getBoundingRectangle(),(0, 0, chef.getWidth(), chef.getHeight()));
//	}
	@Test
	public void ChefOnChefCollisionTest() {
		World world;
		world =new World(new Vector2(0,0), true);
		Chef chef = new Chef(world, 0, 0);

		assertEquals(chef.isChefOnChefCollision(), false);
	}
	@Test
	public void ChefV2Test() {
		World world;
		world =new World(new Vector2(0,0), true);
		Chef chef = new Chef(world, 0, 0);

		assertEquals(chef.getV2(), new Vector2(0, 0));
	}
	@Test
	public void ChefTouchingFixtureTest() {
		World world;
		world =new World(new Vector2(0,0), true);
		Chef chef = new Chef(world, 0, 0);

		assertEquals(chef.getTouchingFixture(),null);
	}
	@Test
	public void ChefInHandsIngredientTest() {
		World world;
		world =new World(new Vector2(0,0), true);
		Chef chef = new Chef(world, 0, 0);

		assertEquals(chef.getInHandsIngredient(),null);
	}
	@Test
	public void ChefInHandsRecipeTest() {
		World world;
		world =new World(new Vector2(0,0), true);
		Chef chef = new Chef(world, 0, 0);

		assertEquals(chef.getInHandsRecipe(),null);
	}
	@Test
	public void ChefIsControllableTest() {
		World world;
		world =new World(new Vector2(0,0), true);
		Chef chef = new Chef(world, 0, 0);

		assertEquals(chef.isControllable(), true);
	}
}