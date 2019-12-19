package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.mapElement.Grass;
import pl.agh.movementUtils.Vector2d;
import pl.agh.timeManager.TimeManager;
import pl.agh.utils.MapUtils;
import pl.agh.utils.MovementUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MapVisualiser {
    private JFrame frame = new JFrame();
    private JPanel mapVisualisation = new JPanel();
    private StatPanel statPanel;
    private JPanel buttonPanel = new JPanel();
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
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(new PauseButton(manager,this));
        buttonPanel.add(new WriteStatsButton(map.stats));
        frame.add(mapVisualisation,BorderLayout.CENTER);
        frame.add(statPanel,BorderLayout.EAST);
        frame.add(buttonPanel,BorderLayout.SOUTH);
        //frame.add(new PauseButton(manager,this),BorderLayout.SOUTH);
        /******/
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /******/
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
        this.statPanel = new StatPanel(map.stats,this);
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
        if(MapUtils.containsAtPos(field.position, Grass.class,map ))field.setBackground(Color.green);
        if(MapUtils.containsAtPos(field.position, Animal.class,map ))field.setBackground(getAnimalColor(field.position));
        highlightPickedAnimal(field);
    }
    public ArrayList<SquarePanel> getFields(){
        return this.fields;
    }

    public void disableFields() {
        fields.forEach(field -> field.setEnabled(false));
    }

    public void activateFields() {
        fields.forEach(field -> {
            if(MapUtils.containsAtPos(field.position,Animal.class,map ))field.setEnabled(true);
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
            Animal current = MapUtils.getFirstAnimalFromPos(field.position,map);
            if(current != null&&current.getGenotype().equals(statPanel.getStats().getDominatingGene()))field.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));//field.setBackground(Color.yellow);
        });
    }
    public void clearHighlight(){
        fields.forEach(field -> field.setBorder(null));
    }
    public JFrame getFrame(){
        return this.frame;
    }
    public JPanel getButtonPanel(){
        return this.buttonPanel;
    }
    public TimeManager getManager(){
        return this.manager;
    }
    public void highlightPickedAnimal(SquarePanel field){
        Animal picked = statPanel.getPickedAnimal();
        if(picked == null)return;
       if(field.position.equals(picked.getPosition())){
           field.setIsAnimalPicked(true);
           field.setBorder(BorderFactory.createLineBorder(Color.white,3));
       }
       else {
           field.setIsAnimalPicked(false);
           field.setBorder(null);
       }
    }
}
