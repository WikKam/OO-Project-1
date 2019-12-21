package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.input.InputHandler;
import pl.agh.input.InputInterpreter;
import pl.agh.movementUtils.Direction;
import pl.agh.movementUtils.Vector2d;
import pl.agh.timeManager.TimeManager;
import pl.agh.utils.BreedUtils;
import pl.agh.utils.FeedingUtils;
import pl.agh.utils.MovementUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationStartButton extends JButton implements ActionListener {
    SimulationStartButton(String title){
        super(title);
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        InputHandler ax = new InputHandler();
        String[] table = ax.getParameters();
        InputInterpreter i = new InputInterpreter(table);
        initConstants(i.startEnergy,i.moveEnergy,i.energyGainedFromGrass,i.startEnergy/2);
        WorldMap map = new WorldMap(i.jungleStart,i.jungleEnd,i.steppeStart,i.steppeEnd);
        TimeManager manager = new TimeManager(map);
        Vector2d start1 = MovementUtils.getRandomVector(map.getPositions());
        Vector2d start2 = MovementUtils.getRandomVector(map.getPositions());
        Animal a = new Animal(start1, Direction.getRandomDirection(),map);
        Animal b = new Animal(start2,Direction.getRandomDirection(),map);
        map.place(a);
        map.place(b);
        manager.start();

    }
    private void initConstants(int startEnergy,int moveCost, int energyGainedFromGrass, int minBreedEnergy){
        MovementUtils.startEnergy = startEnergy;
        MovementUtils.moveCost = moveCost;
        FeedingUtils.energyGainedFromGrass = energyGainedFromGrass;
        BreedUtils.minBreedEnergy = minBreedEnergy;
    }
}
