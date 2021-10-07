package company;

import javax.swing.*;
import java.awt.*;

/**
 * Tab with playlist library (List of audio in active playlist)
 */
public class LibraryTab extends GradientTab {

    int width;

    /**
     * @param width tab width
     * @param height tab height
     */
    public LibraryTab(int width, int height) {
        super();
        this.width = width;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        renderPlaylist();
        setBorder(null);
        setVisible(true);
        repaint();
        DataViewBinder.setLibTab(this);
    }

    /**
     * Render current playlist
     */
    public void renderPlaylist() {
        removeAll();
        Playlist tracks = AudioPlayer.getPlaylist();
        int i = 0;
        for (String track : tracks) {
            add(Box.createRigidArea(new Dimension(5, 5)));
            add(new TrackButton(i++, width));
        }
        add(Box.createRigidArea(new Dimension(5, 5)));
        repaint();
    }

}
