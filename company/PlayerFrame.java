package company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

/**
 *  Player Frame class - main component in GUI
 *  */
public class PlayerFrame extends JFrame implements WindowListener {

    /**
     *
     * @param width Frame width
     * @param height Frame height
     */
    public PlayerFrame(int width, int height) {
        super();
        setTitle("Player");
        setResizable(false);
        setJMenuBar(new ControlMenu());
        setSize(new Dimension(width, height));
        getContentPane().setLayout(null);
        getContentPane().add(new TabsPanel(width, height - 100));
        getContentPane().add(new ControllerPanel(width, height - 100));
        addWindowListener(this);
        setBackground(ColorSheet.DarkBg);
        setVisible(true);
        repaint();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String path = System.getProperty("user.home") + File.separator + "player_data.txt";
        try {
            AudioBase.getAudioBase().saveData(new BufferedWriter(new FileWriter(path)));
        } catch (Exception ignored) {}
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
