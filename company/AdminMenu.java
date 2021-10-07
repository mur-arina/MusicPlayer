package company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Admin menu for JMenuBar
 */
public class AdminMenu extends JMenu {
    public AdminMenu() {
        super("Admin");
        JMenuItem addFolder = new JMenuItem("Add folder");
        addFolder.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                chooser.setDialogTitle("Select folder");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    AudioBase.getAudioBase().addFolder(chooser.getSelectedFile().getPath());
                    DataViewBinder.getLib().renderPlaylist();
                }

            }
        });

        add(addFolder);

        JMenuItem addPlaylist = new JMenuItem("New playlist");
        addPlaylist.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MakePlaylistFrame();
            }
        });
        add(addPlaylist);
    }
}
