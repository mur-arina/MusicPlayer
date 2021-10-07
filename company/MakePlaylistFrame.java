package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Class for Playlist creation Frame
 */
public class MakePlaylistFrame extends JFrame {
    public MakePlaylistFrame() {
        super("Playlist frame");
        setResizable(false);
        setSize(new Dimension(500, 800));
        ArrayList<String> audios = AudioBase.getAudioBase().getAudioList();
        JPanel checkPanel = new GradientTab();
        checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));

        ArrayList<PlaylistCheckbox> checkBoxes = new ArrayList<>();

        for (String audio : audios) {
            PlaylistCheckbox trackBox = new PlaylistCheckbox(audio);
            checkBoxes.add(trackBox);
            checkPanel.add(trackBox);
            trackBox.setVisible(true);
            checkPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        }


        checkPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        JButton accept = new JButton("Create playlist");
        accept.setBackground(ColorSheet.Background);
        accept.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accept.setForeground(ColorSheet.LightForeground);
        accept.setMaximumSize(new Dimension(500, 30));
        accept.setMinimumSize(new Dimension(500, 30));

        MakePlaylistFrame self = this;
        accept.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)JOptionPane.showInputDialog(
                    self,
                    "Print name of playlist:\n",
                    "Playlist name",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "Playlist"
                );
                AudioBase.getAudioBase().createPlaylist(name);
                for (PlaylistCheckbox jCheckBox : checkBoxes) {
                    if (jCheckBox.isSelected()) {
                       AudioBase.getAudioBase().addInPlaylist(name, jCheckBox.getPath());
                    }
                }
                DataViewBinder.getPlaylistsMenu().renderPlaylists();
                DataViewBinder.getEditPlaylistMenu().renderPlaylists();
                DataViewBinder.getDeletePlaylistMenu().renderPlaylists();
                DataViewBinder.getPlaylistsTab().renderPlaylists();
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
