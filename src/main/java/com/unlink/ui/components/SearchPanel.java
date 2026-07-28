package com.unlink.ui.components;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    private final JTextField searchField;
    private final JButton searchButton;

    public SearchPanel() {

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        searchField = new JTextField(20);

        searchButton = new JButton("Search");

        add(searchField);
        add(searchButton);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}