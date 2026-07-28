package com.unlink.ui.common;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {

    private final GridBagConstraints gbc;

    public FormPanel() {

        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    public void addField(String labelText, JComponent component, int row) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        add(component, gbc);
    }

}