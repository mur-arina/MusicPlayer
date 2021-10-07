package company;

import java.util.HashMap;
import java.util.Map;

/**
 * Tab with list of playlists
 */
public class PlaylistsTab extends GradientTab {

    int width;

    public PlaylistsTab(int width, int height) {
        super();
        setVisible(true);
        setSize(width, height);
        setLayout(null);

        this.width = width;
        renderPlaylists();
    }

    public void renderPlaylists() {
        removeAll();
        HashMap<String, Playlist> base = AudioBase.getAudioBase().getPlaylists();
        int i = 0;
        for (Map.Entry<String, Playlist> entry : base.entrySet()) {
            String playlist = entry.getKey();
            add(new PlaylistLine(playlist,  width, i++));
        }

        repaint();
    }
}
