package company;

import javax.swing.*;

/**
 * Class with static methods to get
 * access for important view components in different places of code
 */
public class DataViewBinder {

    static private LibraryTab lib;
    static private PlaylistsTab playlistsTab;
    static private PlaylistsMenu playlistsMenu;
    static private EditPlaylistMenu editPlaylistMenu;

    public static DeletePlaylistMenu getDeletePlaylistMenu() {
        return deletePlaylistMenu;
    }

    public static void setDeletePlaylistMenu(DeletePlaylistMenu deletePlaylistMenu) {
        DataViewBinder.deletePlaylistMenu = deletePlaylistMenu;
    }

    static private DeletePlaylistMenu deletePlaylistMenu;

    static private JButton playButton;
    static private JTabbedPane pane;

    public static void setLibTab(LibraryTab l) {
        lib = l;
    }

    public static LibraryTab getLib() {
        return lib;
    }

    public static void setPlaylistMenu(PlaylistsMenu menu) {
        playlistsMenu = menu;
    }

    public static PlaylistsMenu getPlaylistsMenu() {
        return playlistsMenu;
    }

    public static void setPlayButton(JButton btn) {
        playButton = btn;
    }

    public static JButton getPlayButton() {
        return playButton;
    }

    public static void setEditPlaylistMenu(EditPlaylistMenu menu) {
        editPlaylistMenu = menu;
    }

    public static EditPlaylistMenu getEditPlaylistMenu() {
        return editPlaylistMenu;
    }

    public static PlaylistsTab getPlaylistsTab() {
        return playlistsTab;
    }

    public static void setPlaylistsTab(PlaylistsTab playlistsTab) {
        DataViewBinder.playlistsTab = playlistsTab;
    }

    public static void setTabsPane(JTabbedPane pane) {
        DataViewBinder.pane = pane;
    }

    public static JTabbedPane getTabsPane() {
        return pane;
    }
}
