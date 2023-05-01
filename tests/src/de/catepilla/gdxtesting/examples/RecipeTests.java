package de.catepilla.gdxtesting.examples;


import Ingredients.Bun;
import Ingredients.Ingredient;
import Ingredients.Steak;
import Recipe.Recipe;
import Recipe.BurgerRecipe;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class RecipeTests {

    @Test
    public void RecipeCreateTest(){
        //TODO This method uses the create() method that uses draw(). Currently unsure how to proceed with this.
    }

    /*
    It is possible to test the values of individual recipes but not to create and test the recipe parent class.
     */

    @Test
    public void  BurgerRecipeTest(){
        Recipe recipe = new BurgerRecipe();
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<Ingredient> testIngredients = new ArrayList<>();
        testIngredients.add(new Bun(0,0,0));
        testIngredients.add(new Steak(0,0,0));
        assertEquals(testIngredients, ingredients);
    }
}
