package pl.agh.mapElement;

import pl.agh.animalMap.WorldMap;
import pl.agh.movementUtils.Vector2d;

public class Grass implements MapElement {
    private Vector2d position;
    public int energyGain;
    private WorldMap map;
    @Override
    public Vector2d getPosition() {
       return this.position;
    }
    public Grass(Vector2d position, WorldMap m){
        this.position = position;
        this.map = m;
    }
    public Grass(int x, int y, WorldMap m){
        this(new Vector2d(x,y),m);
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Grass){
            Grass oGrass = (Grass) o;
            if(oGrass.position.equals(this.position))
                return true;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return 7 * this.position.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        return -1;
    }

}
