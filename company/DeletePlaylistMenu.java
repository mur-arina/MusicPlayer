package company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Delete menu bar
 */
public class DeletePlaylistMenu extends JMenu {
    public DeletePlaylistMenu() {
        super("Delete playlist");
        renderPlaylists();
        DataViewBinder.setDeletePlaylistMenu(this);
    }

    public void renderPlaylists() {
        removeAll();
        HashMap<String, Playlist> playlists = AudioBase.getAudioBase().getPlaylists();
        for (Map.Entry<String, Playlist> playlistEntry : playlists.entrySet()) {
            if (playlistEntry.getKey().equals("All")) {
                continue;
            }
            JMenuItem deleteList = new JMenuItem(playlistEntry.getKey());
            deleteList.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (AudioPlayer.getPlaylist().getName().equals(playlistEntry.getKey())) {
                        AudioPlayer.setPlaylist(AudioBase.getAudioBase().getPlaylists().get("All"));

                        if (AudioPlayer.getPlaylist().size() > 0) {
                           AudioPlayer.play(0);
                           AudioPlayer.pause();
                        }
                    }
                    AudioBase.getAudioBase().deletePlaylist(playlistEntry.getKey());
                    DataViewBinder.getLib().renderPlaylist();
                    DataViewBinder.getPlaylistsMenu().renderPlaylists();
                    DataViewBinder.getEditPlaylistMenu().renderPlaylists();
                    DataViewBinder.getDeletePlaylistMenu().renderPlaylists();
                    DataViewBinder.getPlaylistsTab().renderPlaylists();
                }
            });
            add(deleteList);
        }
        repaint();
    }
}
