package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.mapElement.Grass;
import pl.agh.movementUtils.Vector2d;
import pl.agh.timeManager.TimeManager;
import pl.agh.utils.MovementUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MapVisualiser {
    private JFrame frame = new JFrame();
    private JPanel mapVisualisation = new JPanel();
    private StatPanel statPanel;
    private WorldMap map;
    private ArrayList<SquarePanel> fields = new ArrayList<>();
    private TimeManager manager;
    private void init(){
        GridLayout layout = new GridLayout(map.steppeEnd.x-map.steppeStart.x+1,map.steppeEnd.y-map.steppeStart.y+1,2,2);
        mapVisualisation.setLayout(layout);
        map.getPositions().forEach(position -> {
            SquarePanel field = new SquarePanel(position,this);
            setColor(field);
            mapVisualisation.add(field);
            fields.add(field);
        });
        frame.add(mapVisualisation,BorderLayout.WEST);
        frame.add(statPanel,BorderLayout.EAST);
        frame.add(new PauseButton(manager,this));
    }

    private Color getAnimalColor(Vector2d position) {
        Animal first = (Animal) Collections.max(map.getElements().get(position));
        int energy = first.getCurrentEnergy();
        Color base = new Color(91,33,50);
        for(int i=0;i<MovementUtils.startEnergy; i+= energy + 1){
            base = base.brighter();
        }
        return base;
    }

    public MapVisualiser(WorldMap map,TimeManager manager){
        this.map = map;
        this.manager = manager;
        this.statPanel = new StatPanel(map.stats);
        init();
    }
    public void update(){
        fields.forEach(this::setColor);
        statPanel.update();
    }
    public void show(){
        frame.pack();
        frame.setVisible(true);
    }
    private void setColor(SquarePanel field){
        if(MovementUtils.isVectorInJungle(field.position,map))field.setBackground(new Color(144,238,144));
        else field.setBackground(new Color(103,83,74));
        if(map.containsAtPos(field.position, Grass.class))field.setBackground(Color.green);
        if(map.containsAtPos(field.position, Animal.class))field.setBackground(getAnimalColor(field.position));
    }
    public ArrayList<SquarePanel> getFields(){
        return this.fields;
    }

    public void disableFields() {
        fields.forEach(field -> field.setEnabled(false));
    }

    public void activateFields() {
        fields.forEach(field -> {
            if(map.containsAtPos(field.position,Animal.class))field.setEnabled(true);
        });
    }
    public WorldMap getMap(){
        return this.map;
    }
    public StatPanel getStatPanel(){
        return  this.statPanel;
    }
    public void highlightAnimalsWithDominatingGene(){
        fields.forEach(field -> {
            Animal current = map.getFirstAnimalFromPos(field.position);
            if(current != null&&current.getGenotype().equals(statPanel.getStats().getDominatingGene()))field.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));//field.setBackground(Color.yellow);
        });
    }
    public void clearHighlight(){
        fields.forEach(field -> field.setBorder(null));
    }
}
