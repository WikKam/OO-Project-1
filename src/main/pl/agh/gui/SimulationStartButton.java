package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.input.InputHandler;
import pl.agh.input.InputInterpreter;
import pl.agh.movementUtils.Direction;
import pl.agh.movementUtils.Vector2d;
import pl.agh.timeManager.TimeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationStartButton extends JButton implements ActionListener {
    public SimulationStartButton(String title){
        super(title);
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        InputHandler ax = new InputHandler();
        String[] table = ax.getParameters();
        InputInterpreter i = new InputInterpreter(table);
        WorldMap map = new WorldMap(i.jungleStart,i.jungleEnd,i.steppeStart,i.steppeEnd);
        TimeManager manager = new TimeManager(map);
        Animal a = new Animal(new Vector2d(2,2), Direction.N,map);
        Animal b = new Animal(new Vector2d(3,3),Direction.N,map);
        map.place(a);
        map.place(b);
        manager.start();

    }
}
