package Ingredients;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Cheese extends Ingredient{
    /**
     * Constructs a new Ingredient object with the specified preparation and cooking times.
     *
     * @param prepareTime The time required to prepare the ingredient.
     * @param cookTime    The time required to cook the ingredient.
     */
    public Cheese(float prepareTime, float cookTime, float bakeTime) {
        super(prepareTime, cookTime, bakeTime);
        super.tex = new ArrayList<>();
        super.tex.add(new Texture("Food/cheese.png"));
        super.tex.add(new Texture("Food/cheese.png"));
        super.tex.add(new Texture("Food/cheese.png"));
        this.setPrepared();
        this.setCooked();
        this.setBaked();
    }
}
