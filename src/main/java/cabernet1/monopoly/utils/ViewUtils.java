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

        // Preventing resizing
        frame.setResizable(false);

        // Making the game fullscreen. We are not using it right now for development purposes.
        frame.dispose();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);


        // Setting screen size
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Center, not required when we make it full screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        return frame;
    }
}
