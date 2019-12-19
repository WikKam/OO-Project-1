package pl.agh.animalMap;

import pl.agh.animal.Animal;
import pl.agh.animal.Genotype;

import java.math.BigInteger;
import java.util.*;

public class MapStatistics {
    private int animalNo = 0;
    private int grassNo = 0;
    private int averageEnergy = 0;
    private int averagelifespan = 0;
    private int averageChildrenNo = 0;
    private HashMap<String,Integer> statMap = new HashMap<>();
    private Genotype dominatingGene;
    public int getAveragelifespan() {
        return averagelifespan;
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
    public void updateAverageEnergy(WorldMap map){
        int energySum = map.getAnimals().stream().mapToInt(Animal::getCurrentEnergy).sum();
        this.averageEnergy = energySum/map.getAnimals().size();
    }
    public void updateAverageLifeSpan(WorldMap map){
        if(map.getDeadAnimalNo().intValueExact() == 0)return;
        BigInteger res = map.getDeadAnimalTotalLifespan().divide(map.getDeadAnimalNo());
        int a = res.intValueExact();
        this.averagelifespan = a;
    }
    public void updateAverageChildrenNo(WorldMap map){
        int childAmount = map.getAnimals().stream().mapToInt(Animal::getChildrenNo).sum();
        this.averageChildrenNo = childAmount / map.getAnimals().size();
    }
    public void updateAll(WorldMap map){
        updateAnimalNo(map);
        updateGrassNo(map);
        updateAverageEnergy(map);
        updateAverageLifeSpan(map);
        updateAverageChildrenNo(map);
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
    public HashMap<String,Integer> getStatMap(){
        statMap.put("animalNo",this.animalNo);
        statMap.put("grassNo", this.animalNo);
        statMap.put("avarageEnergy",this.animalNo);
        statMap.put("avarageLifespan",this.animalNo);
        statMap.put("averageChildrenNo",this.animalNo);
        return statMap;
    }
}