package de.catepilla.gdxtesting.examples;


import Ingredients.*;
import Recipe.Recipe;
import Recipe.BurgerRecipe;
import Recipe.RawPizzaRecipe;
import Recipe.SaladRecipe;
import Recipe.JacketPotatoRecipe;
import Recipe.CookedPizzaRecipe;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Tests the FR_RECIPE requirement.
 */
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

    @Test
    public void  RawPizzaRecipeTest(){
        Recipe recipe = new RawPizzaRecipe();
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<Ingredient> testIngredients = new ArrayList<>();
        testIngredients.add(new PizzaBase(0,0,0));
        testIngredients.add(new Tomato(0,0,0));
        testIngredients.add(new Cheese(0,0,0));
        assertEquals(testIngredients, ingredients);
    }

    @Test
    public void  SaladRecipeTest(){
        Recipe recipe = new SaladRecipe();
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<Ingredient> testIngredients = new ArrayList<>();
        testIngredients.add(new Lettuce(0,0,0));
        testIngredients.add(new Tomato(0,0,0));
        testIngredients.add(new Onion(0,0,0));
        assertEquals(testIngredients, ingredients);
    }

    @Test
    public void  JacketPotatoRecipeTest(){
        Recipe recipe = new JacketPotatoRecipe();
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<Ingredient> testIngredients = new ArrayList<>();
        ingredients.add(new CookedPotato(0, 0,0));
        ingredients.add(new Cheese(0, 0,0));
        assertEquals(testIngredients, ingredients);
    }

    @Test
    public void  CookedPizzaRecipeTest(){
        Recipe recipe = new CookedPizzaRecipe();
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<Ingredient> testIngredients = new ArrayList<>();
        ingredients.add(new RawPizza(0,0,0));
        assertEquals(testIngredients, ingredients);
    }

}
