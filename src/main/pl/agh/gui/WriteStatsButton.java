package pl.agh.gui;

import pl.agh.animalMap.MapStatistics;
import pl.agh.output.StatWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteStatsButton extends JButton implements ActionListener {
    private MapStatistics stats;
    public WriteStatsButton(MapStatistics stats){
        this.stats = stats;
        this.addActionListener(this);
        this.setPreferredSize(new Dimension(200,50));
        this.setText("Zapisz statystyki");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        StatWriter.writeStats(stats);
    }
}
