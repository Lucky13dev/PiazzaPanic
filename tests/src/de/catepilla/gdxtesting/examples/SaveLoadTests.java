package de.catepilla.gdxtesting.examples;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.team13.piazzapanic.GameMode;
import com.team13.piazzapanic.GameState;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the FR_SAVE and FR_LOAD requirements.
 */
@RunWith(GdxTestRunner.class)
public class SaveLoadTests {

	//ASSETS/CHEF FOLDER
	@Test
	public void CanSave() {
		GameState a = new GameState(new GameMode());
		assertTrue(a.save("Save"));
	}

	@Test
	public void CanLoad() {
		GameState a = new GameState(new GameMode());
		a.save("Save");
		assertTrue(a.load("Save"));
	}

	@Test
	public void TimeLoadCorrectly() {
		GameState a = new GameState(new GameMode());
		a.save("Save");
		GameState b = new GameState(new GameMode());
		b.load("Save");

		assertEquals(a.getTime(), b.getTime(), 0.01);
	}
}
