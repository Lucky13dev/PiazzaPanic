package de.catepilla.gdxtesting.examples;
import Ingredients.Bun;
import Ingredients.Ingredient;
import Ingredients.Onion;
import com.badlogic.gdx.graphics.Texture;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


@RunWith (GdxTestRunner.class)
public class IngredientTests {

    /**
     * Testing Ingredient.java
     *
     * NB: -Private method findCorrectSkin() is for implementation only and does not need to be tested.
     *     -Onion is used as a placeholder for any ingredient
     */

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

    @Test
    public void IngredientCreateTexture(){
        //TODO This method uses the create() method that uses draw(). Currently unsure how to proceed with this.
    }

    @Test
    public void IngredientCreatePosAndSize(){
        //TODO This method uses the create() method that uses draw(). Currently unsure how to proceed with this.
    }



}
