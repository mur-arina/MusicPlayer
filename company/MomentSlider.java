package company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Slider for playing progress
 */
public class MomentSlider extends JSlider implements ChangeListener {
    public MomentSlider() {
        super();
        setMinimum(0);
        setMaximum(100);
        setValue(0);
        addChangeListener(this);
        setForeground(ColorSheet.LightForeground);
        setBackground(ColorSheet.Background);
        AudioPlayer.setMomentSlider(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        AudioPlayer.setMoment(getValue());
    }
}
