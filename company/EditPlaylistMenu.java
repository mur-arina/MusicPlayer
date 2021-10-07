package company;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Playlist menu from app menu bar for editing.
 */
public class EditPlaylistMenu extends JMenu {
    public EditPlaylistMenu() {
        super("Edit playlist");
        renderPlaylists();
        DataViewBinder.setEditPlaylistMenu(this);
    }

    public void renderPlaylists() {
        removeAll();
        HashMap<String, Playlist> playlists = AudioBase.getAudioBase().getPlaylists();
        for (Map.Entry<String, Playlist> playlistEntry : playlists.entrySet()) {
            if (playlistEntry.getKey().equals("All")) {
                continue;
            }
            JMenuItem changeList = new EditPlaylistItem(playlistEntry.getKey());
            add(changeList);
        }
        repaint();
    }
}

