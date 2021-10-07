package company;

import javax.swing.*;
import java.awt.*;

/**
 * Gradient Button (JButton with gradient)
 */
public class GradientButton extends JButton {
    Color color1 = ColorSheet.HelpColor;
    Color color2 = Color.BLACK;

    /**
     * @param text Button text
     */
    public GradientButton(String text) {
        super(text);
    }

    /**
     *
     * @param text Button text
     * @param color1 First gradient color
     * @param color2 Second gradient color
     */
    public GradientButton(String text, Color color1, Color color2) {
        super(text);
        this.color1 = color1;
        this.color2 = color2;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = (int)((double)getHeight());
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.setColor(Color.white);
        g2d.drawString(getText(), 30, 25);
    }
}
