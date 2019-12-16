package pl.agh.animalMap;
import pl.agh.animal.Animal;
import pl.agh.mapElement.Grass;
import pl.agh.mapElement.MapElement;
import pl.agh.movementUtils.Vector2d;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class WorldMap implements PropertyChangeListener {
    public final Vector2d jungleStart;
    public final Vector2d jungleEnd;
    public final Vector2d steppeStart;
    public final Vector2d steppeEnd;
    /***************wszystkie pozycje********/
    private ArrayList<Vector2d> positions;
    private ArrayList<Animal> animals = new ArrayList<>();
    public MapStatisticts stats = new MapStatisticts();
    private int grassNo = 0;
    private HashMap<Vector2d, HashSet<MapElement>> elements = new HashMap<>();
    public WorldMap(Vector2d jungleStart, Vector2d jungleEnd, Vector2d steppeStart, Vector2d steppeEnd){
        this.steppeStart = steppeStart;
        this.steppeEnd = steppeEnd;
        this.jungleStart = jungleStart;
        this.jungleEnd = jungleEnd;
        initialisePositions();
    }
    public boolean canMoveTo(Vector2d add) {
        return false;
    }
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        MapElement a = (MapElement) e.getSource();
        Vector2d newPos = (Vector2d)e.getNewValue();
        Vector2d oldPos = (Vector2d)e.getOldValue();
        if(e.getPropertyName() =="move"){
            elements.get(oldPos).remove(a);
        //tutaj cos moze sie wywalic kiedys xdd
        if(elements.containsKey(newPos)){
            elements.get(newPos).add(a);
        }
        else{
            elements.put(newPos, new HashSet<>());
            elements.get(newPos).add(a);
        }}
        else if(e.getPropertyName().equals("death")){
            elements.get(oldPos).remove(a);
            //animals.remove(a);
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
        }else{
            grassNo++;
        }
    }
    public HashMap<Vector2d,HashSet<MapElement>> getElements(){
        return this.elements;
    }
    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }
    public void removeGrass(Vector2d pos){
        Grass a = new Grass(pos,this);
        this.elements.get(pos).remove(a);
        grassNo--;
    }
    /*********************/
    private void initialisePositions(){
        this.positions = new ArrayList<>();
        for(int i = steppeStart.x; i<=steppeEnd.x;i++){
            for(int j = steppeStart.y; j<=steppeEnd.y;j++){
                positions.add(new Vector2d(i,j));
            }
        }
    }
    /**********************/

    public ArrayList<Vector2d> getPositions() {
        return positions;
    }
    public boolean containsAtPos(Vector2d position, Class a){
        HashSet<MapElement> elementsAtPos = elements.get(position);
        if(elementsAtPos == null)return false;
        for(MapElement el : elementsAtPos){
            if(a.isInstance(el))return true;
        }
        return false;
    }
    public int getGrassNo(){
        return this.grassNo;
    }
    public Animal getFirstAnimalFromPos(Vector2d pos){
        if(!containsAtPos(pos,Animal.class))return null;
        Animal ret = (Animal) Collections.max(elements.get(pos));
        return ret;
    }
}
