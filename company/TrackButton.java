package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TrackButton class (for track buttons)
 */
public class TrackButton extends JButton implements ActionListener, MouseListener {
    private int trackId;

    /**
     * @param track trackId
     * @param width button width
     */
    public TrackButton(int track, int width) {
        super();
        this.trackId = track;

        addActionListener(this);
        addMouseListener(this);
        setText(AudioPlayer.getPlaylist().get(track));
        setPreferredSize(new Dimension(width - 20, 50));
        setSize(width - 20, 50);
        setMinimumSize(new Dimension(width - 20, 50));
        setMaximumSize(new Dimension(width - 20, 50));
        setBackground(ColorSheet.Background);
        setForeground(ColorSheet.LightForeground);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(null);
        setVisible(true);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AudioPlayer.play(trackId);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(ColorSheet.HoverBg);
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(ColorSheet.Background);
        repaint();
    }

}
