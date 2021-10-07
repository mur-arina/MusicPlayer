package company;

/**
Tab for audio search
 */
public class SearchTab extends GradientTab {
    public SearchTab(int width, int height) {
        super();
        setVisible(true);
        setSize(width, height);
        SearchDataPanel panel = new SearchDataPanel(width, height - 75);
        add(new SearchLine(panel, width, 50));
        add(panel);
        setLayout(null);
        repaint();
    }
}
