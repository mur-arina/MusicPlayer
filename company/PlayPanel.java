package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Panel with Next/Pause/Prev buttons
 */
public class PlayPanel extends JPanel {
    public static boolean statePlaying = false;

    /**
     * Set playing state (audio is playing/ audio is stopped
     * and update play button (Play/Pause)
     * @param state true - if playing
     */
    public static void setState(boolean state) {
        if (state) {
            DataViewBinder.getPlayButton().setText("Pause");
        } else {
            DataViewBinder.getPlayButton().setText("Play");
        }
        DataViewBinder.getPlayButton().repaint();
        statePlaying = state;
    }

    public PlayPanel() {

        super();
        setLayout(null);
        setLocation(5, 5);
        setSize(300, 50);

        ArrayList<JButton> controls = new ArrayList<>();

        controls.add(new GradientButton("Prev"));
        controls.add(new GradientButton("Play"));
        controls.add(new GradientButton("Next"));

        controls.get(0).addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.playPrev();
            }
        });

        DataViewBinder.setPlayButton(controls.get(1));
        controls.get(1).addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!PlayPanel.statePlaying) {
                        AudioPlayer.continuePlaying();
                    } else {
                        AudioPlayer.pause();
                    }
                } catch (Exception ignore) {
                    if (AudioPlayer.getPlaylist().size() > 0) {
                        AudioPlayer.play(0);
                    }
                }
            }
        });

        controls.get(2).addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.playNext();
            }
        });

        for (int i = 0; i < controls.size(); ++i) {
            controls.get(i).setLocation(i * 100 + i * 2, 0);
            controls.get(i).setSize(100, 45);
            controls.get(i).setForeground(ColorSheet.LightForeground);
            controls.get(i).setBackground(ColorSheet.HoverBg);
            controls.get(i).setCursor(new Cursor(Cursor.HAND_CURSOR));
            controls.get(i).setBorder(null);
            add(controls.get(i));
        }

        setBackground(ColorSheet.Background);
        repaint();

    }
}
