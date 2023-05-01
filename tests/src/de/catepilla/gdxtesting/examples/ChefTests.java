package de.catepilla.gdxtesting.examples;

import static org.junit.Assert.assertEquals;

import Sprites.Chef;
import com.badlogic.gdx.math.Vector2;
import com.team13.piazzapanic.MainGame;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/** NB:
 * Private method setOrientation() is for implementation only and does not need to be tested.
 */

@RunWith(GdxTestRunner.class)
public class ChefTests {

	/**
	 * Tests on the Chef constructor class's fields.
	 */
	@Test
	public void ChefWidthTest() {
		Chef chef = new Chef(0, 0);
		assertEquals(chef.getWidth(), 13 / MainGame.PPM, 0);
	}

	@Test
	public void ChefHeightTest() {
		Chef chef = new Chef(0, 0);
		assertEquals(chef.getHeight(), 20 / MainGame.PPM, 0);
	}

	@Test
	public void ChefSetBoundsTest() {
		Chef chef = new Chef(0, 0);
		chef.getBoundingRectangle();

		assertEquals(chef.getBoundingRectangle().x, 0, 0);
		assertEquals(chef.getBoundingRectangle().x, 0, 0);
		assertEquals(chef.getBoundingRectangle().y, 0, 0);
		assertEquals(chef.getBoundingRectangle().height, chef.getHeight(), 0);
		assertEquals(chef.getBoundingRectangle().width, chef.getWidth(), 0);
	}

	@Test
	public void ChefOnChefCollisionTest() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.isChefOnChefCollision(), false);
	}

	@Test
	public void ChefV2Test() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.startVector, new Vector2(0, 0));
	}

	@Test
	public void ChefTouchingFixtureTest() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.getTouchingFixture(), null);
	}

	@Test
	public void ChefInHandsIngredientTest() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.getInHandsIngredient(), null);
	}

	@Test
	public void ChefInHandsRecipeTest() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.getInHandsRecipe(), null);
	}

	@Test
	public void ChefIsControllableTest() {
		Chef chef = new Chef(0, 0);

		assertEquals(chef.isControllable(), true);
	}

//	@Test
//	public void SetPositionTest() {
//		Chef chef = new Chef(0,0);
//		int dt = 0;
//		//chef.update(dt); //need to access the update method,
//		chef.setPosition(0,0);
//		assertEquals(chef.b2body.getPosition().x,chef.b2body.getPosition().x - chef.getWidth() / 2,0);
//		assertEquals(chef.b2body.getPosition().y,chef.b2body.getPosition().y - chef.getWidth() / 2,0);
//	}

//	@Test
//	public void OrientationUP(){
//		Chef chef = new Chef(0,0);
//		//update(dt);
//		assertEquals(chef.);
//	}

	//Orientation is private

//	@Test
//	public void EmptyHandsTest() {
//		Chef chef = new Chef(0, 0);
//		if(chef.getInHandsIngredient() == null && chef.getInHandsRecipe() == null) {
//			boolean emptyHands = true;
//		}
//
	//inhandsingredient is private

//	@Test
//	public void DropItemOnTest() {
//		InteractiveTileObject station = new InteractiveTileObject();
//		int prepareTime = 1;
//		int cookTime = 1;
//		int bakeTime = 1;
//		Onion ing = new Onion(prepareTime, cookTime, bakeTime);
//		Chef.dropItemOn(station, ing);
//	};
	 //needs

//	@Test
//	public void Test() {
//		Chef chef = new Chef(0, 0);
//
//		assertEquals();
//	}
//
}

