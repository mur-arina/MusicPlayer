package company;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {

        String path = System.getProperty("user.home") + File.separator + "player_data.txt";
        try {
            AudioBase.getAudioBase().restoreData(new BufferedReader(new FileReader(path)));
        }catch (Exception ignored) {
        }

        UIManager.put("TabbedPane.selected", ColorSheet.HoverBg);
        // remove borders
        Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
        insets.top = -1;
        UIManager.put("TabbedPane.contentBorderInsets", insets);
        AudioPlayer.setPlaylist(AudioBase.getAudioBase().getPlaylists().get("All"));
        new PlayerFrame(1400, 800).repaint();
    }

}
