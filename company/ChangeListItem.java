package company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Class of change playlist JMenuItems */
public class ChangeListItem extends JMenuItem implements ActionListener {

    private Playlist playlist;

    /**
     *
     * @param name playlist name
     * @param playlist playlist instance
     */
    public ChangeListItem(String name, Playlist playlist) {
        super(name);
        this.playlist = playlist;
        addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        AudioPlayer.stop();
        AudioPlayer.setPlaylist(playlist);
        DataViewBinder.getLib().renderPlaylist();
        if (AudioPlayer.getPlaylist().size() > 0) {
            AudioPlayer.play(0);
        }
    }
}
