package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Class for Playlist creation Frame
 */
public class EditPlaylistFrame extends JFrame {
    public EditPlaylistFrame(Playlist playlist) {
        super("Editing playlist");
        setResizable(false);
        setSize(new Dimension(500, 800));
        ArrayList<String> audios = AudioBase.getAudioBase().getAudioList();
        JPanel checkPanel = new GradientTab();
        checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));

        ArrayList<PlaylistCheckbox> checkBoxes = new ArrayList<>();

        for (String audio : audios) {
            PlaylistCheckbox trackBox = new PlaylistCheckbox(audio);
            if (playlist.contains(audio)) {
                trackBox.setSelected(true);
            }
            checkBoxes.add(trackBox);
            checkPanel.add(trackBox);
            trackBox.setVisible(true);
            checkPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        }


        checkPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        JButton accept = new JButton("Save changes");
        accept.setMaximumSize(new Dimension(500, 30));
        accept.setMinimumSize(new Dimension(500, 30));
        accept.setBackground(ColorSheet.Background);
        accept.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accept.setForeground(ColorSheet.LightForeground);

        EditPlaylistFrame self = this;
        accept.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = playlist.getName();
                for (PlaylistCheckbox jCheckBox : checkBoxes) {
                    String text = jCheckBox.getPath();
                    Playlist playlist = AudioBase.getAudioBase().getPlaylists().get(name);
                    if (jCheckBox.isSelected() && !playlist.contains(text)) {
                        AudioBase.getAudioBase().addInPlaylist(name, text);
                    }
                    if (!jCheckBox.isSelected()) {
                        playlist.remove(text);
                    }
                }
                DataViewBinder.getPlaylistsMenu().renderPlaylists();
                DataViewBinder.getLib().renderPlaylist();
                self.setVisible(false);
                self.dispose();
            }
        });

        checkPanel.add(accept);

        JScrollPane tracks = new JScrollPane();
        tracks.setPreferredSize(new Dimension(500, 600));
        tracks.setViewportView(checkPanel);
        tracks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tracks.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(tracks);

        setVisible(true);
        repaint();
    }
}
