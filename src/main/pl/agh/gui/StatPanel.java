package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.animalMap.MapStatistics;
import pl.agh.utils.MapUtils;

import javax.swing.*;
import java.awt.*;

public class StatPanel extends JPanel {
    private boolean isAnimalPicked = false;
    private boolean isDeathDayUpdated = false;
    private MapVisualiser visualiser;
    private Animal pickedAnimal;
    private MapStatistics stats;
    private JTextArea currentDay = new JTextArea();
    private JTextArea animalNo = new JTextArea();
    private JTextArea childrenNo = new JTextArea();
    private JTextArea avgEnergy = new JTextArea();
    private JTextArea avgLifespan = new JTextArea();
    private JTextArea grassNo = new JTextArea();
    private JTextArea dominatingGene = new JTextArea();
    private JTextArea pickedAnimalGene = new JTextArea();
    private JTextArea pickedAnimalChildrenNo = new JTextArea();
    private JTextArea pickedAnimalDescendantNo = new JTextArea();
    private JTextArea pickedAnimalDeathDay = new JTextArea();
    public StatPanel(MapStatistics stats, MapVisualiser visualiser){
        super();
        this.visualiser = visualiser;
        this.stats = stats;
        currentDay.setText("epoka: " + visualiser.getManager().getDaysPassed());
        animalNo.setText("ilosc animal: " + stats.getAnimalNo());
        childrenNo.setText("srednia ilosc dzieci: " + stats.getAverageChildrenNo());
        avgEnergy.setText("srednia ilosc energii: " + stats.getAverageEnergy());
        avgLifespan.setText("sredni wiek" + stats.getAveragelifespan());
        grassNo.setText("ilosc trawy" + stats.getGrassNo());
        dominatingGene.setText("dominujący gen: " + stats.getDominatingGene());
        animalNo.setEditable(false);
        childrenNo.setEditable(false);
        avgEnergy.setEditable(false);
        avgLifespan.setEditable(false);
        grassNo.setEditable(false);
        dominatingGene.setEditable(false);
        animalNo.setLineWrap(true);
        childrenNo.setLineWrap(true);
        avgEnergy.setLineWrap(true);
        avgLifespan.setLineWrap(true);
        grassNo.setLineWrap(true);
        dominatingGene.setLineWrap(true);
        pickedAnimalGene.setLineWrap(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(currentDay);
        this.add(animalNo);
        this.add(childrenNo);
        this.add(avgEnergy);
        this.add(avgLifespan);
        this.add(grassNo);
        this.add(dominatingGene);
        this.add(pickedAnimalGene);
        this.add(pickedAnimalChildrenNo);
        this.add(pickedAnimalDescendantNo);
        this.add(pickedAnimalDeathDay);
        this.setPreferredSize(new Dimension(500,250));
    }

    void update() {
        currentDay.setText("epoka: " + visualiser.getManager().getDaysPassed());
        animalNo.setText("liczba animali: " + stats.getAnimalNo());
        childrenNo.setText("srednia liczba dzieci: " + stats.getAverageChildrenNo());
        avgEnergy.setText("srednia ilosc energii: " + stats.getAverageEnergy());
        avgLifespan.setText("sredni wiek: "  + stats.getAveragelifespan());
        grassNo.setText("liczba traw: " + stats.getGrassNo());
        dominatingGene.setText("dominujący gen: " + stats.getDominatingGene().getGenes());
        if(isAnimalPicked){
                showPickedAnimalGene("genom wybranego animala:\n"
                        + pickedAnimal.getGenotype().getGenes());
                showPickedAnimalChildrenNo("liczba dzieci wybranego animala: "
                        + pickedAnimal.getChildrenNo());
                showPickedAnimalDescendantNo("liczba potomków wybranego animala: "
                        + pickedAnimal.getDescendantNo());
                if(pickedAnimal.isDead()&&!isDeathDayUpdated){
                    pickedAnimalDeathDay.setText("epoka, w ktorej zmarl wybrany animal: "
                            + this.visualiser.getManager().getDaysPassed());
                    this.isDeathDayUpdated = true;
                }
        }
    }

    private void showPickedAnimalGene(String text) {
        this.pickedAnimalGene.setText(text);
    }

    MapStatistics getStats() {
        return stats;
    }

    private void showPickedAnimalDescendantNo(String text) {
        this.pickedAnimalDescendantNo.setText(text);
    }

    private void showPickedAnimalChildrenNo(String text) {
        this.pickedAnimalChildrenNo.setText(text);
    }

    void setIsAnimalPicked(boolean animalPicked) {
        isAnimalPicked = animalPicked;
    }
    void setPickedAnimal(Animal animal){
        this.pickedAnimal = animal;
        this.isDeathDayUpdated = false;
        this.update();
        this.pickedAnimalDeathDay.setText("");
    }
    Animal getPickedAnimal(){
        return this.pickedAnimal;
    }
    public void hideDetailedStats(){
        pickedAnimalGene.setText("");
        pickedAnimalDeathDay.setText("");
        pickedAnimalChildrenNo.setText("");
        pickedAnimalDescendantNo.setText("");
    }
}
