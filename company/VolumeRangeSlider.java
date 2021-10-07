package company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
Slider for volume control
 */
public class VolumeRangeSlider extends JSlider implements ChangeListener {
    public VolumeRangeSlider() {
        super();
        setMinimum(0);
        setMaximum(100);
        setValue(100);
        addChangeListener(this);
        setForeground(ColorSheet.LightForeground);
        setBackground(ColorSheet.Background);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
      AudioPlayer.setVolume(getValue());
    }
}
