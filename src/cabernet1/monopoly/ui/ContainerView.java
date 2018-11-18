package cabernet1.monopoly.ui;

import javax.swing.*;

/**
 * View class for holding current view, so that we can easily change the view
 */
public class ContainerView extends BaseView {
    private static volatile ContainerView _instance = null;
    /**
     * Currently active view
     */
    private BaseView currentView = null;

    private ContainerView() {

    }

    public static synchronized ContainerView getInstance() {
        if (_instance == null) {
            _instance = new ContainerView();
        }
        return _instance;
    }

    public BaseView getCurrentView() {
        return currentView;
    }

    public void setCurrentView(BaseView currentView) {
        BaseView previous = this.currentView;
        this.currentView = currentView;

        if (previous != null) {
            JPanel previousRoot = previous.getRoot();
            JFrame window = (JFrame) SwingUtilities.getWindowAncestor(previousRoot);
            window.setContentPane(this.currentView.getRoot());
            window.validate();
            window.repaint();
        }
    }

    @Override
    public JPanel getRoot() {
        return currentView != null ? currentView.getRoot() : null;
    }
}
