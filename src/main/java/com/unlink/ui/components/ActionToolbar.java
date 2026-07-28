package com.unlink.ui.components;

import javax.swing.*;
import java.awt.*;

public class ActionToolbar extends JPanel {

    private final JButton addButton;
    private final JButton editButton;
    private final JButton deleteButton;
    private final JButton refreshButton;

    public ActionToolbar() {

        setLayout(new FlowLayout(FlowLayout.LEFT));

        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        add(addButton);
        add(editButton);
        add(deleteButton);
        add(refreshButton);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}