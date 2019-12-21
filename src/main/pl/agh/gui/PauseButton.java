package pl.agh.gui;

import pl.agh.timeManager.TimeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton implements ActionListener {
    private TimeManager manager;
    private MapVisualiser visualiser;
    private HighlightAnimalsButton highlight;
    public PauseButton(TimeManager manager, MapVisualiser visualiser){
        this.manager = manager;
        this.visualiser = visualiser;
        this.setText("Pauza");
        this.setPreferredSize(new Dimension(100,50));
        this.addActionListener(this);
        this.highlight = new HighlightAnimalsButton(manager,visualiser);
        this.visualiser.getButtonPanel().add(highlight);
        this.highlight.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(this.manager.getSimulationState()){
            this.visualiser.disableFields();
            this.highlight.clearHighlight();
            this.manager.unpause();
            this.highlight.setEnabled(false);
            this.setText("Pauza");
        }
        else {
            this.manager.pause();
            this.setText("Start");
            this.visualiser.activateFields();
            this.highlight.setEnabled(true);
        }
    }
}
