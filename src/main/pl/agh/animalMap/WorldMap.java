package pl.agh.animalMap;
import pl.agh.animal.Animal;
import pl.agh.mapElement.MapElement;
import pl.agh.movementUtils.Vector2d;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WorldMap implements PropertyChangeListener {
    public final Vector2d jungleStart;
    public final Vector2d jungleEnd;
    public final Vector2d steppeStart;
    public final Vector2d steppeEnd;
    private ArrayList<Animal> animals;
    private HashMap<Vector2d, HashSet<MapElement>> elements = new HashMap<>();
    public WorldMap(Vector2d jungleStart, Vector2d jungleEnd, Vector2d steppeStart, Vector2d steppeEnd){
        this.steppeStart = steppeStart;
        this.steppeEnd = steppeEnd;
        this.jungleStart = jungleStart;
        this.jungleEnd = jungleEnd;
    }
    public boolean canMoveTo(Vector2d add) {
        return false;
    }
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        MapElement a = (MapElement) e.getSource();
        Vector2d newPos = (Vector2d)e.getNewValue();
        Vector2d oldPos = (Vector2d)e.getOldValue();
        if(elements.containsKey(oldPos)) {
            elements.get(oldPos).remove(a);
        }
        if(elements.containsKey(newPos)){
            elements.get(newPos).add(a);
        }
        else{
            elements.put(newPos,new HashSet<MapElement>());
            elements.get(newPos).add(a);
        }
    }
    public void place(MapElement element){
        Vector2d pos = element.getPosition();
        if(!elements.containsKey(pos)){
            elements.put(pos, new HashSet<>());
        }
        elements.get(pos).add(element);
        if (element instanceof Animal){
            Animal animal = (Animal)element;
            animals.add(animal);
        }
    }
    public HashMap<Vector2d,HashSet<MapElement>> getElements(){
        return this.elements;
    }
}
