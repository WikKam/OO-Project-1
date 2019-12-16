package pl.agh.utils;

import pl.agh.animalMap.WorldMap;
import pl.agh.movementUtils.Vector2d;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class MovementUtils {
    public static int moveCost;
    public static int startEnergy;
    public static Vector2d getCorrectPos(Vector2d newPos, WorldMap map) {
        int xPos = newPos.x > map.steppeEnd.x ? map.steppeStart.x :
                (newPos.x < map.steppeStart.x ? map.steppeEnd.x : newPos.x);
        int yPos = newPos.y > map.steppeEnd.y ? map.steppeStart.y :
                (newPos.y < map.steppeStart.y ? map.steppeEnd.y : newPos.y);
        return new Vector2d(xPos,yPos);
    }
    public static ArrayList<Vector2d> getFreeVectorsFromJungle(WorldMap map){
        ArrayList<Vector2d> ret = new ArrayList<>();
        map.getPositions().forEach(vector -> {
            HashSet field = map.getElements().get(vector);
            if((field == null ||field.isEmpty()) && isVectorInJungle(vector,map))
                ret.add(vector);
        });
        return ret;
    }
    public static ArrayList<Vector2d> getFreeVectorsFromSteppe(WorldMap map){
        ArrayList<Vector2d> ret = new ArrayList<>();
        map.getPositions().forEach(vector -> {
            HashSet field = map.getElements().get(vector);
            if((field == null ||field.isEmpty()) && isVectorInSteppe(vector,map))ret.add(vector);
        });
        return ret;
    }
    public static Vector2d getRandomVector(ArrayList<Vector2d> vectors){
        Random random = new Random();
        int index = random.nextInt(vectors.size());
        return vectors.get(index);
    }
    public static boolean isVectorInJungle(Vector2d vec, WorldMap map){
        return(vec.precedes(map.jungleEnd) && vec.follows(map.jungleStart));
    }
    public static boolean isVectorInSteppe(Vector2d vec, WorldMap map){
        return(!isVectorInJungle(vec,map)&&vec.precedes(map.steppeEnd) && vec.follows(map.steppeStart));
    }
}
