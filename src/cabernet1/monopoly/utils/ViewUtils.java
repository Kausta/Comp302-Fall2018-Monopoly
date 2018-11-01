package cabernet1.monopoly.utils;

import cabernet1.monopoly.ui.BaseView;

import javax.swing.*;

public class ViewUtils {

    public static JFrame createWindowFromView(BaseView view) {
        return createWindowFromView(view, "Monopoly Game");
    }

    public static JFrame createWindowFromView(BaseView view, String title) {
        JFrame frame = new JFrame(title);
        // Register view
        frame.setContentPane(view.getRoot());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // Set fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        return frame;
    }
}
