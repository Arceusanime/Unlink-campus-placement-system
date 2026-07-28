package com.unlink.ui.student;

import com.unlink.ui.components.ActionToolbar;
import com.unlink.ui.components.DataTable;
import com.unlink.ui.components.SearchPanel;
import com.unlink.model.Student;
import com.unlink.service.StudentService;
import com.unlink.service.impl.StudentServiceImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {

    private final ActionToolbar toolbar;
    private final SearchPanel searchPanel;
    private final DataTable table;
    private final StudentService studentService = new StudentServiceImpl();
    private static final String[] COLUMNS = {
            "ID",
            "USN",
            "Name",
            "Department",
            "CGPA",
            "Status"
    };

    public StudentPanel() {

        setLayout(new BorderLayout(10,10));

        toolbar = new ActionToolbar();

        searchPanel = new SearchPanel();

        JPanel northPanel = new JPanel(new BorderLayout());

        northPanel.add(toolbar, BorderLayout.WEST);

        northPanel.add(searchPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);

        table = new DataTable(
                COLUMNS
        );
        table.getTable().getColumnModel().getColumn(0).setMinWidth(0);
        table.getTable().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTable().getColumnModel().getColumn(0).setWidth(0);

        add(table, BorderLayout.CENTER);

        registerEvents();
        loadStudents();
    }

    private void registerEvents() {

        toolbar.getAddButton().addActionListener(e ->

                new StudentDialog(
                        (Frame) SwingUtilities.getWindowAncestor(this),
                        null,
                        this::loadStudents
                )

        );

        toolbar.getRefreshButton().addActionListener(e -> {

            searchPanel.getSearchField().setText("");

            loadStudents();

        });

        toolbar.getEditButton().addActionListener(
                e -> editSelectedStudent()
        );

        table.getTable().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    editSelectedStudent();

                }

            }

        });

        toolbar.getDeleteButton().addActionListener(e -> {

            Student student = getSelectedStudent();

            if (student == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a student."
                );

                return;
            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Delete " + student.getName() + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                boolean success =
                        studentService.deleteStudent(student.getId());

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student deleted successfully."
                    );

                    loadStudents();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to delete student."
                    );

                }

            }

        });
        searchPanel.getSearchButton().addActionListener(e -> {

            String keyword = searchPanel.getSearchField()
                    .getText()
                    .trim();

            if (keyword.isEmpty()) {

                loadStudents();

            } else {

                loadStudents(
                        studentService.searchStudents(keyword)
                );

            }

        });
        searchPanel.getSearchField().addActionListener(e ->

                searchPanel.getSearchButton().doClick()

        );
    }
    private void loadStudents(List<Student> students) {

        DefaultTableModel model = table.getModel();

        model.setRowCount(0);

        for (Student student : students) {

            model.addRow(new Object[]{
                    student.getId(),
                    student.getUsn(),
                    student.getName(),
                    student.getDepartment(),
                    student.getCgpa(),
                    student.getPlacementStatus()
            });

        }
    }

    private void loadStudents() {

        loadStudents(studentService.getAllStudents());

    }
    private int getSelectedStudentId() {

        int selectedRow = table.getTable().getSelectedRow();

        if (selectedRow == -1) {
            return -1;
        }

        return (int) table.getModel().getValueAt(selectedRow, 0);
    }
    private Student getSelectedStudent() {

        int id = getSelectedStudentId();

        if (id == -1) {
            return null;
        }

        return studentService.getStudentById(id);
    }

    private void editSelectedStudent() {

        Student student = getSelectedStudent();

        if (student == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        new StudentDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                student,
                this::loadStudents
        );
    }

}