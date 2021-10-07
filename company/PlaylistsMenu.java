package company;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Playlist menu from app menu bar.
 */
public class PlaylistsMenu extends JMenu {
    public PlaylistsMenu() {
        super("Select playlist");
        renderPlaylists();
        DataViewBinder.setPlaylistMenu(this);
    }

    public void renderPlaylists() {
        removeAll();
        HashMap<String, Playlist> playlists = AudioBase.getAudioBase().getPlaylists();
        for (Map.Entry<String, Playlist> playlistEntry : playlists.entrySet()) {
            JMenuItem changeList = new ChangeListItem(playlistEntry.getKey(), playlistEntry.getValue());
            add(changeList);
        }
        repaint();
    }
}
