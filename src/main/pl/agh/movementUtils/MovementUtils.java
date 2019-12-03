package pl.agh.movementUtils;

import pl.agh.animalMap.WorldMap;

public class MovementUtils {
    public static Vector2d getCorrectPos(Vector2d newPos, WorldMap map) {
        int xPos = newPos.x > map.steppeEnd.x ? map.steppeStart.x :
                (newPos.x < map.steppeStart.x ? map.steppeEnd.x : newPos.x);
        int yPos = newPos.y > map.steppeEnd.y ? map.steppeStart.y :
                (newPos.y < map.steppeStart.y ? map.steppeEnd.y : newPos.y);
        return new Vector2d(xPos,yPos);
    }
}
