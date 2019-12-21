package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.movementUtils.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class SquarePanel extends JButton implements ActionListener {
    final Vector2d position;
    private boolean isAnimalPicked = false;
    private MapVisualiser visualiser;

    SquarePanel(Vector2d position, MapVisualiser visualiser){
        super();
        this.position = position;
        this.setSize(15,15);
        this.setPreferredSize(new Dimension(15,15));
        this.setEnabled(false);
        this.visualiser = visualiser;
        this.addActionListener(this);
        this.setFont(new Font("Arial", Font.PLAIN, 8));
        this.setBorder(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isAnimalPicked) {
            Animal pickedAnimal = (Animal) Collections.max(visualiser.getMap().getElements().get(position));
            pickedAnimal.setChildrenNo(0);
            pickedAnimal.setDescendantNo(0);
            this.visualiser.getStatPanel().getStats().setPickedAnimal(pickedAnimal);
            this.visualiser.getStatPanel().setIsAnimalPicked(true);
            this.visualiser.getStatPanel().update();
            this.visualiser.getManager().setPraParent(pickedAnimal);
            this.visualiser.getFields().forEach(field -> visualiser.highlightIfContainsPickedAnimal(field));
        }
        else{
            this.visualiser.getStatPanel().getStats().setPickedAnimal(null);
            this.visualiser.getStatPanel().setIsAnimalPicked(false);
            this.visualiser.getStatPanel().hideDetailedStats();
            this.setBorder(null);
            this.isAnimalPicked = false;
        }
    }
    void setIsAnimalPicked(boolean isPicked){
        this.isAnimalPicked = isPicked;
    }
}
