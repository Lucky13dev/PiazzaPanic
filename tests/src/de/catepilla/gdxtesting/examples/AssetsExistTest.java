package de.catepilla.gdxtesting.examples;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import de.catepilla.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class AssetsExistTest {

	//ASSETS/CHEF FOLDER
	@Test
	public void ChefHoldingBunsFileExists() {
		assertTrue("This test will only pass when the Chef_holding_buns.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_buns.png").exists());
	}
	@Test
	public void ChefHoldingBunsToastedFileExists() {
		assertTrue("This test will only pass when the Chef_holding_buns_toasted.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_buns_toasted.png").exists());
	}
	@Test
	public void ChefHoldingBurgerFileExists() {
		assertTrue("This test will only pass when the Chef_holding_burger.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_burger.png").exists());
	}
	@Test
	public void ChefHoldingChoppedLettuceFileExists() {
		assertTrue("This test will only pass when the Chef_holding_chopped_lettuce.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_chopped_lettuce.png").exists());
	}
	@Test
	public void ChefHoldingChoppedOnionFileExists() {
		assertTrue("This test will only pass when the Chef_holding_chopped_onion.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_chopped_onion.png").exists());
	}
	@Test
	public void ChefHoldingChoppedTomatoFileExists() {
		assertTrue("This test will only pass when the Chef_holding_chopped_tomato.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_chopped_tomato.png").exists());
	}
	@Test
	public void ChefHoldingFrontFileExists() {
		assertTrue("This test will only pass when the Chef_holding_front.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_front.png").exists());
	}
	@Test
	public void ChefHoldingLettuceFileExists() {
		assertTrue("This test will only pass when the Chef_holding_lettuce.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_lettuce.png").exists());
	}
	@Test
	public void ChefHoldingMeatFileExists() {
		assertTrue("This test will only pass when the Chef_holding_meat.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_meat.png").exists());
	}
	@Test
	public void ChefHoldingOnionFileExists() {
		assertTrue("This test will only pass when the Chef_holding_onion.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_onion.png").exists());
	}
	@Test
	public void ChefHoldingPattyFileExists() {
		assertTrue("This test will only pass when the Chef_holding_patty.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_patty.png").exists());
	}
	@Test
	public void ChefHoldingSaladFileExists() {
		assertTrue("This test will only pass when the Chef_holding_salad.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_salad.png").exists());
	}
	@Test
	public void ChefHoldingTomatoFileExists() {
		assertTrue("This test will only pass when the Chef_holding_tomato.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_holding_tomato.png").exists());
	}
	@Test
	public void ChefNormalFileExists() {
		assertTrue("This test will only pass when the Chef_normal.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/Chef_normal.png").exists());
	}
	@Test
	public void ChefIdentifierFileExists() {
		assertTrue("This test will only pass when the chefIdentifier.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Chef/chefIdentifier.png").exists());
	}

	//ASSETS/FOOD FOLDER
	@Test
	public void BurgerFileExists() {
		assertTrue("This test will only pass when the Burger.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Burger.png").exists());
	}
	@Test
	public void BurgerBunsFileExists() {
		assertTrue("This test will only pass when the Burger_buns.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Burger_buns.png").exists());
	}
	@Test
	public void BurgerRecipeFileExists() {
		assertTrue("This test will only pass when the Burger_recipe.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Burger_recipe.png").exists());
	}
	@Test
	public void ChoppedLettuceFileExists() {
		assertTrue("This test will only pass when the Chopped_lettuce.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Chopped_lettuce.png").exists());
	}
	@Test
	public void ChoppedOnionFileExists() {
		assertTrue("This test will only pass when the Chopped_onion.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Chopped_onion.png").exists());
	}
	@Test
	public void ChoppedTomatoFileExists() {
		assertTrue("This test will only pass when the Chopped_tomato.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Chopped_tomato.png").exists());
	}
	@Test
	public void CookedPattyFileExists() {
		assertTrue("This test will only pass when the Cooked_patty.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Cooked_patty.png").exists());
	}
	@Test
	public void LettuceFileExists() {
		assertTrue("This test will only pass when the Lettuce.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Lettuce.png").exists());
	}
	@Test
	public void MeatFileExists() {
		assertTrue("This test will only pass when the Meat.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Meat.png").exists());
	}
	@Test
	public void OnionFileExists() {
		assertTrue("This test will only pass when the Onion.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Onion.png").exists());
	}
	@Test
	public void PattyFileExists() {
		assertTrue("This test will only pass when the Patty.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Patty.png").exists());
	}
	@Test
	public void SaladFileExists() {
		assertTrue("This test will only pass when the Salad.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Salad.png").exists());
	}
	@Test
	public void SaladRecipeFileExists() {
		assertTrue("This test will only pass when the Salad_recipe.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Salad_recipe.png").exists());
	}
	@Test
	public void ToastedBurgerBunsFileExists() {
		assertTrue("This test will only pass when the Toasted_burger_buns.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Toasted_burger_buns.png").exists());
	}
	@Test
	public void TomatoFileExists() {
		assertTrue("This test will only pass when the Tomato.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Food/Tomato.png").exists());
	}

	//ASSETS FOLDER
	@Test
	public void KitchenFilesExist() {
		assertTrue("This test will only pass when the Kitchen.tmx file coming with a new project setup has not been deleted.", Gdx.files.internal("Kitchen.tmx").exists());
		assertTrue("This test will only pass when the Kitchen.tsx file coming with a new project setup has not been deleted.", Gdx.files.internal("Kitchen.tsx").exists());
		assertTrue("This test will only pass when the KitchenTileSet.tsx file coming with a new project setup has not been deleted.", Gdx.files.internal("KitchenTileSet.tsx").exists());
		assertTrue("This test will only pass when the OldKitchen.tmx file coming with a new project setup has not been deleted.", Gdx.files.internal("OldKitchen.tmx").exists());
	}

	@Test
	public void StartImageFileExists() {
		assertTrue("This test will only pass when the startImage.png file coming with a new project setup has not been deleted.", Gdx.files.internal("startImage.png").exists());
	}
	@Test
	public void TileSetFilesExist() {
		assertTrue("This test will only pass when the Tile_set.png file coming with a new project setup has not been deleted.", Gdx.files.internal("Tile_set.png").exists());
		assertTrue("This test will only pass when the TileSet.png file coming with a new project setup has not been deleted.", Gdx.files.internal("TileSet.png").exists());
	}
}
