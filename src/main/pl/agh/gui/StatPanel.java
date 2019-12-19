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

    public void update() {
        animalNo.setText("ilosc animal: " + stats.getAnimalNo());
        childrenNo.setText("srednia ilosc dzieci: " + stats.getAverageChildrenNo());
        avgEnergy.setText("srednia ilosc energii: " + stats.getAverageEnergy());
        avgLifespan.setText("sredni wiek" + stats.getAveragelifespan());
        grassNo.setText("ilosc trawy" + stats.getGrassNo());
        dominatingGene.setText("dominujący gen: " + stats.getDominatingGene().getGenes());
        if(isAnimalPicked){
                showPickedAnimalGene("genom wybranego animala:\n"
                        + pickedAnimal.getGenotype().getGenes());
                showPickedAnimalChildrenNo("dzieci wybranego animala: "
                        + pickedAnimal.getChildrenNo());
                showPickedAnimalDescendantNo("potomkowie wybranego animala: "
                        + MapUtils.getDescendantNo(pickedAnimal,visualiser.getMap()));
                if(pickedAnimal.isDead()&&!isDeathDayUpdated){
                    pickedAnimalDeathDay.setText("epoka, w ktorej zmarl wybrany animal: "
                            + this.visualiser.getManager().getDaysPassed());
                    this.isDeathDayUpdated = true;
                }
        }
    }

    public void showPickedAnimalGene(String text) {
        this.pickedAnimalGene.setText(text);
    }

    public MapStatistics getStats() {
        return stats;
    }

    public void showPickedAnimalDescendantNo(String text) {
        this.pickedAnimalDescendantNo.setText(text);
    }

    public void showPickedAnimalChildrenNo(String text) {
        this.pickedAnimalChildrenNo.setText(text);
    }

    public void setIsAnimalPicked(boolean animalPicked) {
        isAnimalPicked = animalPicked;
    }
    public void setPickedAnimal(Animal animal){
        this.pickedAnimal = animal;
        this.isDeathDayUpdated = false;
        this.update();
        this.pickedAnimalDeathDay.setText("");
    }
    public Animal getPickedAnimal(){
        return this.pickedAnimal;
    }
}
