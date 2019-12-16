package pl.agh.utils;

import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.mapElement.Grass;
import pl.agh.mapElement.MapElement;
import pl.agh.movementUtils.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class FeedingUtils{
    public static int energyGainedFromGrass;
    public static boolean canAnimalsFeed(Vector2d key, WorldMap map){
        Grass test = new Grass(key,map);
        HashSet<MapElement> elements = map.getElements().get(key);
        if(elements.contains(test)){
            if(elements.size()>1)return true;
        }
        return false;
    }

    public static ArrayList<Animal> getAnimalsToFeed(Vector2d key, WorldMap map){
        HashSet<MapElement> elements = map.getElements().get(key);
        Animal strongest =(Animal)Collections.max(elements);
        int maxEnergy = strongest.getCurrentEnergy();
        ArrayList<Animal> ret = new ArrayList<>();
        elements.forEach(element ->{
            if(element instanceof Animal){
                Animal check = (Animal) element;
                if(check.getCurrentEnergy() == maxEnergy)ret.add(check);
            }
        });
        return ret;
    }

}
