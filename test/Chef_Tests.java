import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import core\src\Sprites\Chef.java

public class Chef_Tests {
    
    public World world = new World(new Vector2(0,0), true);

    @Test
    public void chefPosition() {

        chef = new Chef(world, 0, 0)
        x, y = chef.getPosition()
        assertEquals(x,0)
        assertEquals(y,0)
    }
}
