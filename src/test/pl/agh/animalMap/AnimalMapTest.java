package pl.agh.animalMap;
import org.junit.Assert;
import org.junit.Test;
import pl.agh.utils.MovementUtils;
import pl.agh.movementUtils.Vector2d;
public class AnimalMapTest {
    Vector2d steppeStart = new Vector2d(0,0);
    Vector2d steppeEnd = new Vector2d(10,10);
    Vector2d jungleStart = new Vector2d(2,2);
    Vector2d jungleEnd = new Vector2d(4,4);
    WorldMap map = new WorldMap(jungleStart,jungleEnd,steppeStart,steppeEnd);
    Vector2d vec1 = new Vector2d(11,9);
    Vector2d vec2 = new Vector2d(11,11);
    Vector2d vec3 = new Vector2d(-10,10);
    Vector2d vec4 = new Vector2d(11,-10);
    Vector2d test1 = new Vector2d(0,9);
    Vector2d test2 = new Vector2d(0,0);
    Vector2d test3 = new Vector2d(10,10);
    Vector2d test4 = new Vector2d(0,10);

    @Test
    public void getCorrectPosTest(){
        Assert.assertEquals(MovementUtils.getCorrectPos(vec1,map),test1);
        Assert.assertEquals(MovementUtils.getCorrectPos(vec2,map),test2);
        Assert.assertEquals(MovementUtils.getCorrectPos(vec3,map),test3);
        Assert.assertEquals(MovementUtils.getCorrectPos(vec4,map),test4);
    }
}
