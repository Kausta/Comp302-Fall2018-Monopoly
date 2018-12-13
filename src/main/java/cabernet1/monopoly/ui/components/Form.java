package cabernet1.monopoly.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Helper for building initial forms
 */
public class Form implements ActionListener {
    private final HashMap<String, Runnable> onClickMethods;
    private final JPanel root;

    private Form() {
        root = new JPanel();
        BoxLayout layout = new BoxLayout(root, BoxLayout.Y_AXIS);
        onClickMethods = new HashMap<>();
        root.setLayout(layout);
    }

    public JPanel getContent() {
        return this.root;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == null) {
            return;
        }
        Runnable r = onClickMethods.get(command);
        if (r == null) {
            return;
        }
        r.run();
    }

    public static class Builder {
        private List<Component> components = new ArrayList<>();
        private Form form;

        public Builder() {
            form = new Form();
        }

        public Builder addComponent(Component component) {
            components.add(component);
            return this;
        }

        public Builder addLabel(String labelText) {
            JPanel container = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            container.setLayout(layout);

            JLabel label = new JLabel(labelText);

            GridBagConstraints c1 = new GridBagConstraints();
            c1.gridx = 0;
            c1.gridy = 0;
            c1.gridwidth = 1;
            c1.gridheight = 1;
            c1.anchor = GridBagConstraints.CENTER;
            container.add(label, c1);

            components.add(container);

            return this;
        }

        public Builder addLabeledComponent(String labelText, Component component) {
            JPanel container = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            container.setLayout(layout);

            JLabel label = new JLabel(labelText);

            GridBagConstraints c1 = new GridBagConstraints();
            c1.gridx = 0;
            c1.gridy = 0;
            c1.gridwidth = 1;
            c1.gridheight = 1;
            c1.anchor = GridBagConstraints.LINE_START;
            container.add(label, c1);

            GridBagConstraints c2 = new GridBagConstraints();
            c2.gridx = 1;
            c2.gridy = 0;
            c2.gridwidth = 1;
            c2.gridheight = 1;
            c2.anchor = GridBagConstraints.LINE_END;
            c2.weightx = 1.;
            c2.fill = GridBagConstraints.HORIZONTAL;
            container.add(component, c2);

            components.add(container);

            return this;
        }

        public Builder addButton(String text, Runnable onClick) {
            JButton button = new JButton(text);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.CENTER);

            String identifier = UUID.randomUUID() + " | " + text;
            button.setActionCommand(identifier);
            button.addActionListener(form);
            form.onClickMethods.put(identifier, onClick);

            Dimension size = new Dimension(250, 50);
            button.setPreferredSize(size);
            button.setMinimumSize(size);
            button.setMaximumSize(size);

            JPanel buttonContainer = new JPanel();
            buttonContainer.add(button);

            components.add(buttonContainer);

            return this;
        }

        public Builder addVerticalSpace(int height) {
            components.add(Box.createVerticalStrut(height));

            return this;
        }

        public Form build() {
            for (Component comp : components) {
                form.root.add(comp);
            }
            return form;
        }
    }
}
