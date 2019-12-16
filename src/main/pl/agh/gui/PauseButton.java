package pl.agh.gui;

import pl.agh.timeManager.TimeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton implements ActionListener {
    private TimeManager manager;
    private MapVisualiser visualiser;
    public PauseButton(TimeManager manager, MapVisualiser visualiser){
        this.manager = manager;
        this.visualiser = visualiser;
        this.setText("Pauza");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(this.manager.getSimulationState()){
            this.visualiser.disableFields();
            this.visualiser.clearHighlight();
            this.manager.unpause();
            //this.manager.notify();
            this.setText("Pause");

        }
        else {
            this.manager.pause();
            this.setText("Start");
            this.visualiser.activateFields();
            this.visualiser.highlightAnimalsWithDominatingGene();
        }
    }
}
