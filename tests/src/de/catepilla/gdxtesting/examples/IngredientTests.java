package de.catepilla.gdxtesting.examples;

import Ingredients.Ingredient;
import Ingredients.Onion;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/** NB:
 * Private method findCorrectSkin() is for implementation only and does not need to be tested.
 */

@RunWith (GdxTestRunner.class)
public class IngredientTests {
    @Test
    public void IngredientPreparedStatus(){
        Ingredient ingredient = new Onion(1,0, 0);

        assertEquals(false, ingredient.isPrepared());

        ingredient.setPrepared();

        assertEquals(true, ingredient.isPrepared());

    }

    @Test
    public void IngredientCookedStatus(){
        Ingredient ingredient = new Onion(1,0, 0);

        assertEquals(false, ingredient.isCooked());

        ingredient.setCooked();

        assertEquals(true, ingredient.isCooked());

    }

    @Test
    public void IngredientBakedStatus(){
        Ingredient ingredient = new Onion(1,0,0);

        assertEquals(false, ingredient.isBaked());

        ingredient.setBaked();

        assertEquals(true, ingredient.isBaked());

    }

    public void IngredientCreateTexture(){

    }

    public void IngredientCreatePosAndSize(){

    }

}
