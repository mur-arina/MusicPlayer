package company;

import javax.swing.*;

/**
 * JMenuBar for application (Admin menu + Playlists menu)
 */
public class ControlMenu extends JMenuBar {
    public ControlMenu() {
        super();
        add(new PlaylistsMenu());
        add(new EditPlaylistMenu());
        add(new DeletePlaylistMenu());
        add(new AdminMenu());
    }
}
