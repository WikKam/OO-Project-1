package pl.agh.mapElement;

import pl.agh.movementUtils.Vector2d;

public class Grass implements MapElement {
    private Vector2d position;

    @Override
    public Vector2d getPosition() {
       return this.position;
    }
    public Grass(Vector2d position){
        this.position = position;
    }
    public Grass(int x, int y){
        this(new Vector2d(x,y));
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
