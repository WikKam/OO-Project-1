package pl.agh.gui;

import pl.agh.animalMap.MapStatisticts;

import javax.swing.*;

public class StatPanel extends JPanel {
    private MapStatisticts stats;
    private JLabel animalNo = new JLabel();
    private JLabel childrenNo = new JLabel();
    private JLabel avgEnergy = new JLabel();
    private JLabel avgLifespan = new JLabel();
    private JLabel grassNo = new JLabel();
    private JLabel dominatingGene = new JLabel();
    private JLabel pickedAnimalGene = new JLabel();
    public StatPanel(MapStatisticts stats){
        super();
        this.stats = stats;
        animalNo.setText("ilosc animal: " + stats.getAnimalNo());
        childrenNo.setText("srednia ilosc dzieci: " + stats.getAverageChildrenNo());
        avgEnergy.setText("srednia ilosc energii: " + stats.getAverageEnergy());
        avgLifespan.setText("sredni wiek" + stats.getAverageLifeSpan());
        grassNo.setText("ilosc trawy" + stats.getGrassNo());
        dominatingGene.setText("dominujący gen: " + stats.getDominatingGene());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(animalNo);
        this.add(childrenNo);
        this.add(avgEnergy);
        this.add(avgLifespan);
        this.add(grassNo);
        this.add(dominatingGene);
        this.add(pickedAnimalGene);
    }

    public void update() {
        animalNo.setText("ilosc animal: " + stats.getAnimalNo());
        childrenNo.setText("srednia ilosc dzieci: " + stats.getAverageChildrenNo());
        avgEnergy.setText("srednia ilosc energii: " + stats.getAverageEnergy());
        avgLifespan.setText("sredni wiek" + stats.getAverageLifeSpan());
        grassNo.setText("ilosc trawy" + stats.getGrassNo());
        dominatingGene.setText("dominujący gen: " + stats.getDominatingGene().getGenes());
    }

    public JLabel getPickedAnimalGene() {
        return pickedAnimalGene;
    }

    public MapStatisticts getStats() {
        return stats;
    }
}
