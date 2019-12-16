package pl.agh.timeManager;

import pl.agh.animal.Animal;
import pl.agh.animalMap.WorldMap;
import pl.agh.utils.BreedUtils;
import pl.agh.utils.FeedingUtils;
import pl.agh.gui.MapVisualiser;
import pl.agh.mapElement.Grass;
import pl.agh.mapElement.MapElement;
import pl.agh.utils.MovementUtils;
import pl.agh.movementUtils.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TimeManager extends Thread {
    private WorldMap map;
    private int daysPassed = 0;
    private MapVisualiser visualiser;
    private boolean isSimulationPaused = false;
    public TimeManager(WorldMap map){

        this.map = map;
        this.visualiser = new MapVisualiser(map,this);
        visualiser.show();
    }
    private void removeDeadAnimals(){
        ArrayList<Animal> toRemove = new ArrayList<>();
        ArrayList<Animal> animals = map.getAnimals();
        animals.forEach(animal -> {
            if(animal.isDead()){
                animal.isDead();
                toRemove.add(animal);
                map.getElements().get(animal.getPosition()).remove(animal);
            }
        });
        animals.removeAll(toRemove);
    }
    private void movePhase(){
        ArrayList<Animal> animals = map.getAnimals();
        animals.forEach(Animal::makeRandomMove);
    }
    private void feedingPhase(){
        HashMap<Vector2d,HashSet<MapElement>> elements = map.getElements();
        elements.forEach((key,set) -> {
            if(FeedingUtils.canAnimalsFeed(key,map)){
                ArrayList<Animal>feeding = FeedingUtils.getAnimalsToFeed(key,map);
                int gain = FeedingUtils.energyGainedFromGrass/feeding.size();
                feeding.forEach(animal -> animal.gainEnergy(gain));
                map.removeGrass(key);
                //set.remove(new Grass(key,map));

            }
        });
    }
    private void breedingPhase(){
        HashMap<Vector2d,HashSet<MapElement>> elements = map.getElements();
        ArrayList<Animal> babies = new ArrayList<>();
        elements.forEach((key,set) -> {
            if(BreedUtils.isBreedingPossible(key,map)){
                ArrayList<Animal>breeding = BreedUtils.getAnimalsForBreeding(key,map);
                Animal parent1 = breeding.get(0);
                Animal parent2 = breeding.get(1);
                Animal animal = new Animal(parent1,parent2);
                babies.add(animal);
                parent1.looseEnergy(parent1.getCurrentEnergy()/4);
                parent2.looseEnergy(parent2.getCurrentEnergy()/4);
            }
        });
        babies.forEach(baby -> map.place(baby));
    }
    public void growingPhase(){
        ArrayList<Vector2d> jungleGrow = MovementUtils.getFreeVectorsFromJungle(map);
        ArrayList<Vector2d> steppeGrow = MovementUtils.getFreeVectorsFromSteppe(map
        );
        Vector2d jungleField = null;
        Vector2d steppeField = null;
        if(jungleGrow.size()>0)
        jungleField = MovementUtils.getRandomVector(jungleGrow);
        if(steppeGrow.size()>0)
        steppeField = MovementUtils.getRandomVector(steppeGrow);

        if(jungleField != null) {
            Grass g1 = new Grass(jungleField, map);
            map.place(g1);
        }
        if(steppeField != null) {
            Grass g2 = new Grass(steppeField, map);
            map.place(g2);
        }
    }
    public void dayPassed() throws InterruptedException {
        daysPassed++;
        removeDeadAnimals();
        movePhase();
        feedingPhase();
        breedingPhase();
        growingPhase();
        map.stats.updateAll(map);
        visualiser.update();
        Thread.sleep(200);
    }
    public int getDaysPassed(){
        return this.daysPassed;
    }
    @Override
    public void run(){
        while(true){
            if(isSimulationPaused){
                synchronized (this){
                    if(isSimulationPaused){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            try {
                dayPassed();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void unpause(){

        this.isSimulationPaused = false;
        notify();

    }
    public synchronized void pause(){

        this.isSimulationPaused = true;
    }
    public boolean getSimulationState(){
        return this.isSimulationPaused;
    }
}
