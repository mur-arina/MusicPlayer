package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Playlist Panel
 */
public class PlaylistLine extends JPanel implements MouseListener {
    JButton btn;

    /**
     * @param playlist playlist name
     * @param width element width
     * @param i element number in list
     */
    public PlaylistLine(String playlist, int width, int i) {
        super();
        setVisible(true);
        setSize(width, 50);
        btn = new JButton(playlist);
        btn.setBackground(ColorSheet.Background);
        btn.setForeground(ColorSheet.LightForeground);
        btn.setBorder(null);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(this);
        btn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.setPlaylist(
                    AudioBase.getAudioBase().getPlaylists().get(playlist)
                );
                DataViewBinder.getTabsPane().setSelectedIndex(0);
                DataViewBinder.getLib().renderPlaylist();
                if (AudioPlayer.getPlaylist().size() > 0) {
                    AudioPlayer.play(0);
                }
            }
        });
        add(btn);
        btn.setSize(width, 50);
        setLayout(null);
        setLocation(0, i * 50 + 5 * i);
        repaint();
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
        btn.setBackground(ColorSheet.HoverBg);
        btn.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        btn.setBackground(ColorSheet.Background);
        btn.repaint();
    }
}
