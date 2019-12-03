package pl.agh.animal;

import pl.agh.breedUtils.BreedUtils;
import pl.agh.mapElement.MapElement;
import pl.agh.animalMap.WorldMap;
import pl.agh.movementUtils.Direction;
import pl.agh.movementUtils.MovementUtils;
import pl.agh.movementUtils.Vector2d;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Animal implements MapElement {
    private Vector2d position;
    private Direction direction;
    private Genotype genotype;
    private WorldMap map;
    private int currentEnergy;
    private PropertyChangeSupport support;
    public void addPropertyChangeListener(PropertyChangeListener ls){
        support.addPropertyChangeListener(ls);
    }
    public Genotype getGenotype(){
        return this.genotype;
    }
    public Animal(Vector2d position, Direction direction, WorldMap map){
        this.support = new PropertyChangeSupport(this);
        this.map = map;
        this.addPropertyChangeListener(map);
        this.position = position;
        this.direction = direction;
        this.genotype = new Genotype();
        this.currentEnergy = 100;
    }
    public Animal(Animal parent1, Animal parent2){
        this.map = parent1.map;
        this.support = new PropertyChangeSupport(this);
        this.direction = Direction.getRandomDirection();
        this.position = BreedUtils.getNearbyPosition(map, parent1.position);
        this.currentEnergy = parent1.getCurrentEnergy()/4 + parent2.getCurrentEnergy()/4;
        this.genotype = new Genotype(parent1, parent2);

    }
    public void move(int deg){
        deg = deg >0?deg : 360 + deg;
        this.direction = this.direction.rotateRight(deg);
        Vector2d newPos = position.add(this.direction.toUnitVector());
        if(map.canMoveTo(position.add(newPos))){
            PropertyChangeEvent ret = new PropertyChangeEvent(this,"position",this.position,newPos);
            this.support.firePropertyChange(ret);
            this.position = newPos;
        }
        else{
            newPos = MovementUtils.getCorrectPos(newPos,this.map);
            PropertyChangeEvent ret = new PropertyChangeEvent(this,"position",this.position,newPos);
            this.support.firePropertyChange(ret);
            this.position = newPos;
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public int getCurrentEnergy(){
        return this.currentEnergy;
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof  Animal))return 1;
        Animal a2 = (Animal)o;
        return this.getCurrentEnergy() - a2.getCurrentEnergy();
    }
    public void breed(Animal other){

    }
}
