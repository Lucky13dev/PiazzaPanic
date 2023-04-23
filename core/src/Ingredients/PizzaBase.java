package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class PizzaBase extends Ingredient{
    /**
     * Constructs a new Ingredient object with the specified preparation and cooking times.
     *
     * @param prepareTime The time required to prepare the ingredient.
     * @param cookTime    The time required to cook the ingredient.
     */
    public PizzaBase(float prepareTime, float cookTime, float bakeTime) {
        super(prepareTime, cookTime,bakeTime);
        super.tex = new ArrayList<>();
        // Need a list of length 3 to avoid an IndexOutOfBoundsException
        super.tex.add(new Texture("Food/pizza_base.png"));
        super.tex.add(new Texture("Food/pizza_base.png"));
        super.tex.add(new Texture("Food/pizza_base.png"));
        this.setPrepared();
        this.setCooked();
        this.setBaked();
    }
}
