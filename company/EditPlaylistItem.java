package company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPlaylistItem extends JMenuItem implements ActionListener {
    String playlist;
    public EditPlaylistItem(String playlist) {
        super(playlist);
        this.playlist = playlist;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new EditPlaylistFrame(AudioBase.getAudioBase().getPlaylists().get(playlist));
    }
}
