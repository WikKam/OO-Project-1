package pl.agh.gui;

import pl.agh.animal.Animal;
import pl.agh.movementUtils.Vector2d;
import pl.agh.utils.MapUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class SquarePanel extends JButton implements ActionListener {
    public final Vector2d position;
    private boolean isAnimalPicked = false;
    private MapVisualiser visualiser;
    private Animal pickedAnimal = null;
    public SquarePanel(Vector2d position, MapVisualiser visualiser){
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
            this.pickedAnimal = (Animal) Collections.max(visualiser.getMap().getElements().get(position));
            this.pickedAnimal.setChildrenNo(0);
            this.visualiser.getStatPanel().setIsAnimalPicked(true);
            this.visualiser.getStatPanel().setPickedAnimal(this.pickedAnimal);
            this.visualiser.getManager().setPraParent(this.pickedAnimal);
           // this.setBorder(BorderFactory.createLineBorder(Color.white,3));
            this.visualiser.getFields().forEach(field -> visualiser.highlightPickedAnimal(field));
        }
        else{
            this.visualiser.getStatPanel().setIsAnimalPicked(false);
            this.setBorder(null);
            this.isAnimalPicked = false;
            this.visualiser.getStatPanel().setPickedAnimal(null);
        }
    }
    public void setIsAnimalPicked(boolean isPicked){
        this.isAnimalPicked = isPicked;
    }
}
