package com.unlink.ui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class DataTable extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;

    public DataTable(String[] columns) {

        setLayout(new BorderLayout());

        model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }
}