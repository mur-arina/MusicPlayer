package company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** Search data panel which contain search results */
public class SearchDataPanel extends JScrollPane {
    JPanel panel;

    /**
     *
     * @param width Panel width
     * @param height Panel height
     */
    public SearchDataPanel(int width, int height) {
        super();
        setSize(width, height);
        setLocation(0, 50);
        setBackground(null);
        panel = new GradientTab();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setVisible(true);
        panel.setSize(width, height);
        panel.setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        setViewportView(panel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    /**
     * Render result of search
     * @param ids Array of track ids of result
     */
    public void renderSearchResult(ArrayList<Integer> ids) {
        panel.removeAll();
        for (Integer id : ids) {
            panel.add(Box.createRigidArea(new Dimension(5, 5)));
            TrackButton trackButton = new TrackButton(id, getWidth());
            trackButton.setVisible(true);
            panel.add(trackButton);
        }
        panel.repaint();
        repaint();
    }
}
