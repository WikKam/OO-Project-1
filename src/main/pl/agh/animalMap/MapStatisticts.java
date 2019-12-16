package pl.agh.animalMap;

import pl.agh.animal.Animal;
import pl.agh.animal.Genotype;

import java.util.*;

public class MapStatisticts {
    private int animalNo = 0;
    private int grassNo = 0;
    private int averageEnergy = 0;
    private int averageLifeSpan = 0;
    private int averageChildrenNo = 0;
    private Genotype dominatingGene;
    public int getAverageLifeSpan() {
        return averageLifeSpan;
    }
    public int getAverageEnergy() {
        return averageEnergy;
    }

    public int getGrassNo() {
        return grassNo;
    }

    public int getAnimalNo() {
        return animalNo;
    }

    public void updateAnimalNo(WorldMap map){
        this.animalNo = map.getAnimals().size();
    }
    public void updateGrassNo(WorldMap map){
        this.grassNo = map.getGrassNo();
    }
    public void updateAvarageEnergy(WorldMap map){
        int energySum = map.getAnimals().stream().mapToInt(Animal::getCurrentEnergy).sum();
        this.averageEnergy = energySum/map.getAnimals().size();
    }
    public void updateAvarageLifeSpan(WorldMap map){
        int lifespanSum = map.getAnimals().stream().mapToInt(Animal::getLifespan).sum();
        this.averageLifeSpan = lifespanSum / map.getAnimals().size();
    }
    public void updateAvarageChildrenNo(WorldMap map){
        int childAmount = map.getAnimals().stream().mapToInt(Animal::getChildrenNo).sum();
        this.averageChildrenNo = childAmount / map.getAnimals().size();
    }
    public void updateAll(WorldMap map){
        updateAnimalNo(map);
        updateGrassNo(map);
        updateAvarageEnergy(map);
        updateAvarageLifeSpan(map);
        updateAvarageChildrenNo(map);
        updateDominatingGene(map);
    }

    public int getAverageChildrenNo() {
        return averageChildrenNo;
    }
    public Genotype getDominatingGene(){
        return this.dominatingGene;
    }
    public void updateDominatingGene(WorldMap map){
        HashMap<Genotype,Integer> genesMap = new HashMap<>();
        ArrayList<Animal> animals = map.getAnimals();
        animals.forEach(animal -> genesMap.put(animal.getGenotype(),0));
        animals.forEach(animal -> {
            genesMap.put(animal.getGenotype(),genesMap.get(animal.getGenotype()) + 1);
        });
        this.dominatingGene = Collections.max(genesMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
}