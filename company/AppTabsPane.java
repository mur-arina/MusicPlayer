package company;

import javax.swing.*;
import java.awt.*;

/** Class with 3 tabs of our app: Library, Search, Playlists */
public class AppTabsPane extends JTabbedPane {
    /**
     *
     * @param width Frame width
     * @param height Frame height
     */
    public AppTabsPane(int width, int height) {
        super(JTabbedPane.TOP);

        DataViewBinder.setTabsPane(this);

        setVisible(true);
        setPreferredSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        setBackground(ColorSheet.HelpColor);
        setForeground(ColorSheet.LightForeground);
        setBorder(null);

        JScrollPane scrollLib = new JScrollPane();
        scrollLib.setPreferredSize(new Dimension(width, height));
        scrollLib.setViewportView(new LibraryTab(width, height));
        scrollLib.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollLib.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        addTab("Library",null, scrollLib , "Music lib");
        addTab("Search",null,  new SearchTab(width, height), "Search");
        PlaylistsTab playlistsTab = new PlaylistsTab(width, height);
        DataViewBinder.setPlaylistsTab(playlistsTab);
        addTab("Playlists",null, playlistsTab, "Playlists");

        repaint();
    }
}
