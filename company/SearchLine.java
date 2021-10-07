package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Component with search control
 */
public class SearchLine extends JPanel {
    /**
     * @param width width of panel
     * @param height height of panel
     */
    public SearchLine(SearchDataPanel panel, int width, int height) {
        super();
        setVisible(true);
        setSize(width, height);
        setLocation(0, 0);
        setLayout(null);

        JButton btn = new JButton("Find");
        btn.setSize(200, height);
        btn.setLocation(width - 200, 0);
        btn.setBackground(ColorSheet.DarkBg);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setForeground(ColorSheet.LightForeground);
        btn.setBorder(null);

        JTextField query = new JTextField(200);
        query.setFont(new Font("serif", Font.PLAIN, 24));
        query.setEditable(true);
        query.setLocation(0, 0);
        query.setSize(width - 200, 50);
        query.setVisible(true);
        add(query);

        btn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String queryStr = query.getText();
                ArrayList<Integer> lst = new ArrayList<>();
                int i = 0;
                for (String str : AudioPlayer.getPlaylist()) {

                    if (str.contains(queryStr)) {
                        lst.add(i);
                    }
                    ++i;
                }
                panel.renderSearchResult(lst);
                panel.repaint();
            }
        });

        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(ColorSheet.HoverBg);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(ColorSheet.DarkBg);
            }
        });
        add(btn);

    }
}
