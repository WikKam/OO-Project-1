package pl.agh.gui;

import javax.swing.*;
import java.awt.*;

public class Menu {
    private JFrame menu = new JFrame();
    private JPanel title = new JPanel();
    public Menu(){
        JLabel label = new JLabel("EVOLUTION SIMULATOR");
        title.add(label);
        menu.add(title, BorderLayout.NORTH);
        JButton button = new SimulationStartButton("START NEW SIMULATION");
        menu.add(button,BorderLayout.SOUTH);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void show(){
        this.menu.pack();
        this.menu.setVisible(true);
    }
}
