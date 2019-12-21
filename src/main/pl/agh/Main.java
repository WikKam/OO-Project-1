package pl.agh;

import pl.agh.animal.Animal;
import pl.agh.animalMap.MapStatistics;
import pl.agh.animalMap.WorldMap;
import pl.agh.gui.Menu;
import pl.agh.input.InputHandler;
import pl.agh.input.InputInterpreter;
import pl.agh.output.StatWriter;
import pl.agh.utils.BreedUtils;
import pl.agh.utils.FeedingUtils;
import pl.agh.movementUtils.Direction;
import pl.agh.utils.MovementUtils;
import pl.agh.movementUtils.Vector2d;
import pl.agh.timeManager.TimeManager;

import java.util.Arrays;

public class Main {

    public static void main(String[] args)  {
        Menu menu = new Menu();
        menu.show();
    }
}
