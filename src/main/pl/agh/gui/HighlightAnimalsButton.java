package pl.agh.gui;

import pl.agh.timeManager.TimeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighlightAnimalsButton extends JButton implements ActionListener {
    private TimeManager manager;
    private MapVisualiser visualiser;
    private boolean areAnimalsHighlighted = false;
    public HighlightAnimalsButton(TimeManager manager, MapVisualiser visualiser){
        this.manager = manager;
        this.visualiser = visualiser;
        this.setText("Zaznacz animale z dominującym genem");
        this.setPreferredSize(new Dimension(400,50));
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!areAnimalsHighlighted){
            this.highlightAnimalsWithDominatingGene();
        }
        else{
            this.clearHighlight();
        }
    }
    public void clearHighlight(){
        this.visualiser.clearHighlight();
        areAnimalsHighlighted = false;
    }
    public void highlightAnimalsWithDominatingGene(){
        this.visualiser.highlightAnimalsWithDominatingGene();
        areAnimalsHighlighted = true;
    }
}
