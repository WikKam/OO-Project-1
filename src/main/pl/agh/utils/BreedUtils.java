package pl.agh.utils;

import javafx.util.Pair;
import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.mapElement.Grass;
import pl.agh.mapElement.MapElement;
import pl.agh.movementUtils.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class BreedUtils {
    public static int minBreedEnergy;
    public static ArrayList<Animal> getAnimalsForBreeding(Vector2d pos, WorldMap map) throws IllegalArgumentException{

        if(map.getElements().containsKey(pos)){
            HashSet<MapElement> elements = map.getElements().get(pos);
            Animal a1 = (Animal)Collections.max(elements);
            elements.remove(a1);
            Animal a2 = (Animal)Collections.max(elements);
            elements.add(a1);
            ArrayList<Animal>ret = new ArrayList<>();
            ret.add(a1);
            ret.add(0,a2);
            return ret;
        }
        throw new IllegalArgumentException("pozycja jest pusta!");
    }
    public static boolean isBreedingPossible(Vector2d key,WorldMap map){
        HashSet elements = map.getElements().get(key);
        if(elements == null) return false;
        if(elements.size()<=1)return false;
        ArrayList<Animal> breedPair;
        try{
            breedPair = getAnimalsForBreeding(key,map);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }
        if(breedPair.get(0).getCurrentEnergy() < minBreedEnergy)return false;
        return true;
    }

}
