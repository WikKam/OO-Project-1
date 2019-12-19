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
    public static void initConstants(int startEnergy,int moveCost, int energyGainedFromGrass, int minBreedEnergy){
        MovementUtils.startEnergy = startEnergy;
        MovementUtils.moveCost = moveCost;
        FeedingUtils.energyGainedFromGrass = energyGainedFromGrass;
        BreedUtils.minBreedEnergy = minBreedEnergy;
    }
    public static void main(String[] args) throws InterruptedException {
        //initConstants(100,1,15, 50);
       /* Vector2d jungleStart = new Vector2d(3,3);
        Vector2d jungleEnd = new Vector2d(6,6);
        Vector2d steppeStart = new Vector2d(0,0);
        Vector2d steppeEnd = new Vector2d(10,10);*/
        InputHandler ax = new InputHandler();
        String[] table = ax.getParameters();
        InputInterpreter i = new InputInterpreter(table);
        initConstants(i.startEnergy,i.moveEnergy,i.energyGainedFromGrass,i.startEnergy/2);
        WorldMap map = new WorldMap(i.jungleStart,i.jungleEnd,i.steppeStart,i.steppeEnd);
        TimeManager manager = new TimeManager(map);
        Animal a = new Animal(new Vector2d(2,2), Direction.N,map);
        Animal b = new Animal(new Vector2d(3,3),Direction.N,map);
        map.place(a);
        map.place(b);
        map.getAnimals().forEach(System.out::println);
      // for(int i = 0; i<20000;i++){
           // manager.dayPassed();
            ///System.out.println(a.getPosition());
            //System.out.println(b.getPosition());
//            System.out.println(a.getCurrentEnergy());
//            System.out.println(b.getCurrentEnergy());
//            System.out.println(map.getAnimals().size());
            //System.out.println(manager.getDaysPassed());
        //   System.out.println(c.getPosition());
        //}
        //map.getAnimals().forEach(animal -> System.out.println(animal.getPosition()));
       // manager.start();
        Menu menu = new Menu();
        menu.show();
        //MapStatistics s = new MapStatistics();
       // StatWriter.writeStats(s);
    }
}
