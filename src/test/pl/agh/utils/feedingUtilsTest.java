package pl.agh.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.agh.Main;
import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.mapElement.Grass;
import pl.agh.movementUtils.Direction;
import pl.agh.movementUtils.Vector2d;
import pl.agh.utils.FeedingUtils;

public class feedingUtilsTest {
    Vector2d steppeStart = new Vector2d(0,0);
    Vector2d steppeEnd = new Vector2d(10,10);
    Vector2d jungleStart = new Vector2d(2,2);
    Vector2d jungleEnd = new Vector2d(4,4);
    WorldMap map = new WorldMap(jungleStart,jungleEnd,steppeStart,steppeEnd);
    Animal animal1 = new Animal(new Vector2d(9,9), Direction.N,map);
    Animal animal2 = new Animal(new Vector2d(9,9), Direction.N,map);
    Animal animal3 = new Animal(new Vector2d(9,9), Direction.N,map);
    @Before
    public void init(){
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        Main.initConstants(100,5,20, 50);
    }
    @Test
    public void removeGrassTest(){
        Grass grass = new Grass(new Vector2d(1,1),map);
        map.place(grass);
        Assert.assertTrue(map.getElements().get(new Vector2d(1,1)).contains(grass));
        map.removeGrass(new Vector2d(1,1));
        Assert.assertFalse(map.getElements().get(new Vector2d(1,1)).contains(grass));
    }
    @Test
    public void getAnimalsToFeedTest(){
        Assert.assertTrue(FeedingUtils.getAnimalsToFeed(animal1.getPosition(),map).contains(animal1));
        Assert.assertTrue(FeedingUtils.getAnimalsToFeed(animal1.getPosition(),map).contains(animal2));
        Assert.assertTrue(FeedingUtils.getAnimalsToFeed(animal1.getPosition(),map).contains(animal3));
        animal3.gainEnergy(10);
        animal1.looseEnergy(4);
        animal2.looseEnergy(2);
        Assert.assertFalse(FeedingUtils.getAnimalsToFeed(animal1.getPosition(),map).contains(animal1));
        Assert.assertFalse(FeedingUtils.getAnimalsToFeed(animal1.getPosition(),map).contains(animal2));
        Assert.assertTrue(FeedingUtils.getAnimalsToFeed(animal1.getPosition(),map).contains(animal3));
    }
    @Test
    public void canAnimalsEatTest(){
        Assert.assertFalse(FeedingUtils.canAnimalsFeed(animal1.getPosition(),map));
        Grass g = new Grass(9,9,map);
        map.place(g);
        Assert.assertTrue(FeedingUtils.canAnimalsFeed(animal1.getPosition(),map));
    }

}
