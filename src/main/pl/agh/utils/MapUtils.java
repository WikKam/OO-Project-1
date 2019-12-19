package pl.agh.utils;

import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.mapElement.MapElement;
import pl.agh.movementUtils.Vector2d;

import java.util.Collections;
import java.util.HashSet;

public class MapUtils {

    public static Animal getFirstAnimalFromPos(Vector2d pos, WorldMap map){
        if(!containsAtPos(pos,Animal.class,map ))return null;
        Animal ret = (Animal) Collections.max(map.getElements().get(pos));
        return ret;
    }
    public static boolean containsAtPos(Vector2d position, Class a, WorldMap map){
        HashSet<MapElement> elementsAtPos = map.getElements().get(position);
        if(elementsAtPos == null)return false;
        for(MapElement el : elementsAtPos){
            if(a.isInstance(el))return true;
        }
        return false;
    }

    public static int getDescendantNo(Animal picked, WorldMap map) {
        return   map
                .getAnimals()
                .stream()
                .filter(animal -> picked.equals(animal.getPraParent()))
                .mapToInt(animal -> 1)
                .sum();
    }
}
