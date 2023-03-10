

package de.catepilla.gdxtesting.examples;

import static org.junit.Assert.assertEquals;

import com.team13.piazzapanic.GameState;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.Test;

public class unitTestExample {

	@Test
	public void TestMaxChefs() {
		GameState gameState = new GameState();
		int max_count = GameState.getMAX_CHEF_COUNT();
		int expected_max_count = 5;
		assertEquals(expected_max_count, max_count);
	}
}
