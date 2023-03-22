package de.catepilla.gdxtesting.examples;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.team13.piazzapanic.GameState;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class SaveLoadTests {

	//ASSETS/CHEF FOLDER
	@Test
	public void CanSave() {
		GameState a = new GameState();
		assertTrue(a.save("Save"));
	}

	@Test
	public void CanLoad() {
		GameState a = new GameState();
		a.save("Save");
		assertTrue(GameState.load("Save") != null);
	}

	@Test
	public void LoadsCorrectly() {
		GameState a = new GameState();
		a.save("Save");
		GameState b = GameState.load("Save");
		assertEquals(a.getTime(), b.getTime(), 0.01);
		assertEquals(a.getControlledChef(), b.getControlledChef());
		assertEquals(a.getHud(), b.getHud());
		assertEquals(a.getChefs(), b.getChefs());
	}
}
