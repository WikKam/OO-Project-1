package pl.agh.animalMap;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pl.agh.animalMap.WorldMap;
import pl.agh.movementUtils.Direction;
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
        Assert.assertEquals(map.getCorrectPos(vec1),test1);
        Assert.assertEquals(map.getCorrectPos(vec2),test2);
        Assert.assertEquals(map.getCorrectPos(vec3),test3);
        Assert.assertEquals(map.getCorrectPos(vec4),test4);
    }
}
