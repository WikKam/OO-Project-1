package pl.agh.movementUtils;

import java.util.Random;
import java.util.stream.Stream;

public enum Direction {
    N(0),
    NE(45),
    E(90),
    SE(135),
    S(180),
    SW(225),
    W(270),
    NW(315);
    private final int degrees;
    private Direction(int degrees) {
        this.degrees = degrees;
    }
    public int getDegrees() {
        return this.degrees;
    }
    public Direction rotateRight(int degrees) {
        return Direction.getByDegree((this.degrees + degrees)%360);
    }
    public static Direction getRandomDirection(){
        Random random = new Random();
        int rand = random.nextInt(8);
        return getByDegree(45*rand);
    }
    public static Direction getByDegree(int degree) {
        return Stream.of(values())
                .filter(direction -> direction.degrees == degree)
                .findAny()
                .orElse(null);
    }
    public Vector2d toUnitVector(){
        switch(this){
            case N:
                return new Vector2d(0,1);
            case NE:
                return new Vector2d(1,1);
            case E:
                return new Vector2d(1,0);
            case SE:
                return new Vector2d(1,-1);
            case S:
                return new Vector2d(0,-1);
            case SW:
                return new Vector2d(-1,-1);
            case W:
                return new Vector2d(-1,0);
            case NW:
                return new Vector2d(-1,1);
        }
        return null;
    }
}
