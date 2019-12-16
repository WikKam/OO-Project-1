package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.movementUtils.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class SquarePanel extends JButton implements ActionListener {
    public final Vector2d position;
   private MapVisualiser visualiser;
    public SquarePanel(Vector2d position, MapVisualiser visualiser){
        super();
        this.position = position;
        this.setSize(30,30);
        this.setPreferredSize(new Dimension(30,30));
        this.setEnabled(false);
        this.visualiser = visualiser;
        this.addActionListener(this);
        this.setFont(new Font("Arial", Font.PLAIN, 8));
        //details.setPreferredSize(new Dimension(30,30));
        //this.add(details);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
     Animal picked = (Animal) Collections.max(visualiser.getMap().getElements().get(position));
    this.visualiser.getStatPanel().getPickedAnimalGene().setText("genom wybranego animala:\n"
            + picked.getGenotype().getGenes());
    }
}
