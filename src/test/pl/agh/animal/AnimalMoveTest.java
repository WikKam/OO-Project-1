package pl.agh.animal;
import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pl.agh.animalMap.WorldMap;
import pl.agh.movementUtils.Direction;
import pl.agh.movementUtils.Vector2d;

public class AnimalMoveTest {
    Vector2d steppeStart = new Vector2d(0,0);
    Vector2d steppeEnd = new Vector2d(10,10);
    Vector2d jungleStart = new Vector2d(2,2);
    Vector2d jungleEnd = new Vector2d(4,4);
    WorldMap map = new WorldMap(jungleStart,jungleEnd,steppeStart,steppeEnd);
    Animal animal = new Animal(new Vector2d(9,9), Direction.N,map);
    Vector2d expected1 = new Vector2d(9,0);
    Vector2d expected2 = new Vector2d(0,1);
    @Test
    public void moveTest(){
        map.place(animal);
        animal.move(0);
        animal.move(0);
        Assert.assertEquals(animal.getPosition(),expected1);
        Assert.assertTrue(map.getElements().get(expected1).contains(animal));
        animal.move(90);
        animal.move(-45);
        Assert.assertEquals(animal.getPosition(),expected2);
        Assert.assertTrue(map.getElements().get(expected2).contains(animal));
    }
}
