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
import pl.agh.utils.BreedUtils;

import java.util.ArrayList;

public class BreedUtilsTest {
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
    public void isBreedingPossibleTest(){
        Main.initConstants(100,5,20, 50);
        map.getElements().get(new Vector2d(9,9)).forEach(System.out::println);
       // Assert.assertTrue(BreedUtils.isBreedingPossible(new Vector2d(9,9),map));
        Assert.assertFalse(BreedUtils.isBreedingPossible(new Vector2d(1,1),map));
        Grass g = new Grass(new Vector2d(9,9),map);
        Assert.assertTrue(BreedUtils.isBreedingPossible(new Vector2d(9,9),map));
        Animal a = new Animal(new Vector2d(1,1), Direction.N,map);
        Assert.assertFalse(BreedUtils.isBreedingPossible(new Vector2d(1,1),map));
        Grass g2 = new Grass(new Vector2d(1,1),map);
        Assert.assertFalse(BreedUtils.isBreedingPossible(new Vector2d(1,1),map));
        Grass g3 = new Grass(new Vector2d(2,2),map);
        Assert.assertFalse(BreedUtils.isBreedingPossible(new Vector2d(2,2),map));
    }
    @Test
    public void getAnimalsForBreedingTest(){
        init();
        animal1.looseEnergy(5);
        animal2.gainEnergy(5);
        ArrayList<Animal> breedPair = BreedUtils.getAnimalsForBreeding(animal1.getPosition(),map);
        Assert.assertTrue(breedPair.contains(animal2));
        Assert.assertTrue(breedPair.contains(animal3));
        Assert.assertTrue(breedPair.indexOf(animal3) == 0);
        Assert.assertTrue(breedPair.indexOf(animal2) == 1);
        Assert.assertFalse(breedPair.contains(animal1));
    }

}
