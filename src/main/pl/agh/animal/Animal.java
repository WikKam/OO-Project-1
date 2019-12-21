package pl.agh.animal;

import pl.agh.mapElement.MapElement;
import pl.agh.animalMap.WorldMap;
import pl.agh.movementUtils.Direction;
import pl.agh.utils.MovementUtils;
import pl.agh.movementUtils.Vector2d;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

import static pl.agh.utils.MovementUtils.moveCost;
import static pl.agh.utils.MovementUtils.startEnergy;

public class Animal implements MapElement {
    private Vector2d position;
    private Direction direction;
    private Genotype genotype;
    private WorldMap map;
    private int currentEnergy;
    private PropertyChangeSupport support;
    private int lifespan=0;
    private int childrenNo = 0;
    private int descendantNo = 0;
    private Animal praParent = null;
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
        this.currentEnergy = startEnergy;
    }
    public Animal(Animal parent1, Animal parent2){
        this.map = parent1.map;
        this.support = new PropertyChangeSupport(this);
        this.addPropertyChangeListener(map);
        this.direction = Direction.getRandomDirection();
        this.position = parent1.position;
        this.currentEnergy = parent1.getCurrentEnergy()/4 + parent2.getCurrentEnergy()/4;
        this.genotype = new Genotype(parent1, parent2);
        parent1.addChild();
        parent2.addChild();
        parent1.addDescendant();
        parent2.addDescendant();
        if(parent1.getPraParent() != null){
            parent1.getPraParent().addDescendant();
        }
        if(parent2.getPraParent() != null){
            parent2.getPraParent().addDescendant();
        }
    }
    public Animal(Animal parent1, Animal parent2, Animal praParent){
        this(parent1,parent2);
        this.praParent = praParent;
    }
    void move(int deg){
        deg = deg >0?deg : 360 + deg;
        this.direction = this.direction.rotateRight(deg);
        Vector2d newPos = position.add(this.direction.toUnitVector());
        if(map.canMoveTo(position.add(newPos))){
            PropertyChangeEvent ret = new PropertyChangeEvent(this,"move",this.position,newPos);
            this.support.firePropertyChange(ret);
            this.position = newPos;
        }
        else{
            newPos = MovementUtils.getCorrectPos(newPos,this.map);
            PropertyChangeEvent ret = new PropertyChangeEvent(this,"move",this.position,newPos);
            this.support.firePropertyChange(ret);
            this.position = newPos;
        }
        looseEnergy(moveCost);
        this.lifespan++;
    }
    public void makeRandomMove(){
        Random random = new Random();
        move(45*random.nextInt(8));
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
    public boolean isDead(){
        return this.currentEnergy <= 0;
    }
    public void die(){
        PropertyChangeEvent ret = new PropertyChangeEvent(this,"death",this.position,null);
        this.support.firePropertyChange(ret);
    }
    public void gainEnergy(int gain){
        this.currentEnergy+=gain;
        if(this.currentEnergy>100)this.currentEnergy = startEnergy;
    }
    public void looseEnergy(int lost){
        this.currentEnergy -= lost;
    }
    public int getLifespan(){
        return this.lifespan;
    }
    public int getChildrenNo(){
        return this.childrenNo;
    }
    private void addChild(){
        this.childrenNo++;
    }
    private Animal getPraParent(){
        return this.praParent;
    }

    public void setChildrenNo(int i) {
        this.childrenNo = i;
    }

    public int getDescendantNo() {
        return descendantNo;
    }
    public void addDescendant(){
        this.descendantNo++;
    }

    public void setDescendantNo(int descendantNo) {
        this.descendantNo = descendantNo;
    }
}
