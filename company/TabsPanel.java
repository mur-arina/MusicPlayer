package company;

import javax.swing.*;

/**
 * Panel for tabs pane
 */
public class TabsPanel extends JPanel {
    public TabsPanel(int width, int height) {
        super();
        setSize(width, height);
        AppTabsPane tabs = new AppTabsPane(width, height);
        setVisible(true);
        add(tabs);
        setBackground(ColorSheet.DarkBg);
        repaint();
    }
}
