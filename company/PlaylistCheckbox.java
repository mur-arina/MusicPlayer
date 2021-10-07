package company;

import javax.swing.*;
import java.awt.*;

/**
 * Class for track checkbox
 */
public class PlaylistCheckbox extends JCheckBox {
    String path;
    public PlaylistCheckbox(String path) {

        super();

        String checkboxText = path.substring(
                Math.max(0, path.length() - 50));
        if (Math.max(0, path.length() - 50) > 0) {
            checkboxText = "..." + checkboxText;
        }
        setText(checkboxText);

        setMaximumSize(new Dimension(500, 30));
        setMinimumSize(new Dimension(500, 30));
        setPreferredSize(new Dimension(500, 30));
        setSize(new Dimension(500, 30));
        setHorizontalAlignment(JCheckBox.LEFT);
        setMargin(new Insets(0, 0, 0 ,0));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBackground(ColorSheet.Background);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(ColorSheet.LightForeground);

        this.path = path;
    }

    /**
     * Get full audio file path
     * @return Audio file associated path
     */
    String getPath() {
        return path;
    }
}
