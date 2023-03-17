package de.catepilla.gdxtesting.examples;

import Ingredients.Ingredient;
import Ingredients.Onion;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IngredientTests {

    @Test
    public void IngredientPreparedStatus(){
        Ingredient ingredient = new Onion(1,0);

        assertEquals(false, ingredient.isPrepared());

        ingredient.setPrepared();

        assertEquals(false, ingredient.isPrepared(), );

    }
}
