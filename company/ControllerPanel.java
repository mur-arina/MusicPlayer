package company;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with player controllers elements
 */
public class ControllerPanel extends JPanel {

    /**
     *
     * @param width panel width
     * @param height panel height
     */
    public ControllerPanel(int width, int height) {

        super();
        setVisible(true);
        setLayout(null);
        setSize(width, 60);
        setLocation(0, height);
        setBackground(ColorSheet.Background);
        setBorder(BorderFactory.createMatteBorder(
            1,
            0,
            0,
            0,
            ColorSheet.HelpColor
        ));

        add(new PlayPanel());

        JLabel timer = new JLabel();
        timer.setLocation(325, 5);
        timer.setSize(100, 50);
        timer.setText("0:0/0:0");
        timer.setForeground(ColorSheet.LightForeground);
        AudioPlayer.setTimeViewer(timer);
        add(timer);

        VolumeRangeSlider volumeController = new VolumeRangeSlider();
        volumeController.setLocation(width - 300, 5);
        volumeController.setSize(new Dimension(200, 50));
        add(volumeController);

        JLabel trackName = new JLabel();
        trackName.setLocation(width - 1000, 5);
        trackName.setSize(600, 20);
        trackName.setText("Nothing is playing now. Select track.");
        trackName.setForeground(ColorSheet.LightForeground);
        AudioPlayer.setNameViewer(trackName);
        add(trackName);

        MomentSlider momentSlider = new MomentSlider();
        momentSlider.setLocation(width - 1000, 25);
        momentSlider.setSize(new Dimension(600, 30));
        add(momentSlider);


    }

}
