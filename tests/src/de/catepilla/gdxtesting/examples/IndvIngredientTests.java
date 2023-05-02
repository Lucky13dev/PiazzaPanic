package de.catepilla.gdxtesting.examples;

import Ingredients.*;
import de.catepilla.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class IndvIngredientTests {

    @Test
    public void BunsTest(){
        Ingredient ingredient = new Bun(0,0,0);
        ingredient.setBaked();
        assertEquals(true, ingredient.isBaked());
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
    }

    @Test
    public void CheeseTest(){
        Ingredient ingredient = new Cheese(0,0,0);
        ingredient.setBaked();
        assertEquals(true, ingredient.isBaked());
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
        ingredient.setCooked();
        assertEquals(true, ingredient.isCooked());
    }

    @Test
    public void CookedPotatoTest(){
        Ingredient ingredient = new CookedPotato(0,0,0);
        ingredient.setBaked();
        assertEquals(true, ingredient.isBaked());
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
        ingredient.setCooked();
        assertEquals(true, ingredient.isCooked());
    }

    @Test
    public void LettuceTest(){
        Ingredient ingredient = new Lettuce(0,0,0);
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
    }

    @Test
    public void OnionTest(){
        Ingredient ingredient = new Onion(0,0,0);
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
    }

    @Test
    public void PizzaBaseTest(){
        Ingredient ingredient = new PizzaBase(0,0,0);
        ingredient.setBaked();
        assertEquals(true, ingredient.isBaked());
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
        ingredient.setCooked();
        assertEquals(true, ingredient.isCooked());
    }

    @Test
    public void PotatoTest(){
        Ingredient ingredient = new Potato(0,0,0);
        ingredient.setBaked();
        assertEquals(true, ingredient.isBaked());
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
        ingredient.setCooked();
        assertEquals(true, ingredient.isCooked());
    }

    @Test
    public void RawPizzaTest(){
        Ingredient ingredient = new RawPizza(0,0,0);
        ingredient.setCooked();
        assertEquals(true, ingredient.isCooked());
    }

    @Test
    public void SteakTest(){
        Ingredient ingredient = new Steak(0,0,0);
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
        ingredient.setCooked();
        assertEquals(true, ingredient.isCooked());
    }

    @Test
    public void TomatoTest(){
        Ingredient ingredient = new Tomato(0,0,0);
        ingredient.setPrepared();
        assertEquals(true, ingredient.isPrepared());
    }



}
