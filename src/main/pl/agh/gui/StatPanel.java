package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.animalMap.MapStatistics;
import pl.agh.utils.MapUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatPanel extends JPanel {
    private boolean isAnimalPicked = false;
    private boolean isDeathDayUpdated = false;
    private MapVisualiser visualiser;
   // private Animal pickedAnimal;
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
    private ArrayList<JTextArea> container = new ArrayList<>();
    public StatPanel(MapStatistics stats, MapVisualiser visualiser){
        super();
        container.add(currentDay);
        container.add(animalNo);
        container.add(childrenNo);
        container.add(avgEnergy);
        container.add(avgLifespan);
        container.add(grassNo);
        container.add(dominatingGene);
        container.add(pickedAnimalDescendantNo);
        container.add(pickedAnimalChildrenNo);
        container.add(pickedAnimalDescendantNo);
        container.add (pickedAnimalGene);
        container.add(pickedAnimalDeathDay);
        init();
        this.visualiser = visualiser;
        this.stats = stats;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        currentDay.setText("epoka: " + visualiser.getManager().getDaysPassed());
        animalNo.setText("ilosc animal: " + stats.getAnimalNo());
        childrenNo.setText("srednia ilosc dzieci: " + stats.getAverageChildrenNo());
        avgEnergy.setText("srednia ilosc energii: " + stats.getAverageEnergy());
        avgLifespan.setText("sredni wiek" + stats.getAveragelifespan());
        grassNo.setText("ilosc trawy" + stats.getGrassNo());
        dominatingGene.setText("dominujący gen: " + stats.getDominatingGene());
        this.setPreferredSize(new Dimension(500,250));
    }

    private void init() {
        container.forEach(item -> {
            item.setEditable(false);
            item.setLineWrap(true);
            this.add(item);
        });
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
                        + stats.getPickedAnimalGene().getGenes());
                showPickedAnimalChildrenNo("liczba dzieci wybranego animala: "
                        + stats.getPickedAnimalChildrenNo());
                showPickedAnimalDescendantNo("liczba potomków wybranego animala: "
                        + stats.getPickedAnimalDescendantNo());
                if(stats.getPickedAnimal().isDead()&&!isDeathDayUpdated){
                    pickedAnimalDeathDay.setText("epoka, w ktorej zmarl wybrany animal: "
                            + this.visualiser.getManager().getDaysPassed());
                    this.isDeathDayUpdated = true;
                }
                if(!stats.getPickedAnimal().isDead()){
                    pickedAnimalDeathDay.setText("");
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
        if(isAnimalPicked){
            update();
        }
    }
    public void hideDetailedStats(){
        pickedAnimalGene.setText("");
        pickedAnimalDeathDay.setText("");
        pickedAnimalChildrenNo.setText("");
        pickedAnimalDescendantNo.setText("");
    }
}
