import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EditRecord extends UIHandler {
    private static final String RECORDS_FILE = "records.csv";
    menu menu = new menu();
    AddNewRecord add = new AddNewRecord();
    JFrame editRecord = new JFrame();
    ImageIcon featuresBg = new ImageIcon("FeaturesBg.png");
    ImageIcon blackLogo = new ImageIcon("blackLogo.png");
    JLabel logolabel = new JLabel();
    JLabel featuresBgLabel = new JLabel();
    JLabel editTitleLabel = new JLabel();
    JPanel editContainer = new JPanel();
    JPanel listNavPanel = new JPanel();
    JButton editButton = new JButton("EDIT");
    JButton searchButton = new JButton("SEARCH");
    JTextField searchField = new JTextField(15);  // Search field for the user
    JTable table;
    DefaultTableModel tableModel;

    EditRecord() {
        editRecord.setTitle("SugarSync");
        editRecord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editRecord.setLayout(null);
        editRecord.setSize(1440, 1024);
        editRecord.setResizable(true);
        editRecord.setUndecorated(true);

        addAboutTab(editRecord, 1057, 68);
        addContributorsTab(editRecord, 1164, 68);

        resizeImage(editRecord, blackLogo, logolabel, 200, 285, 181);
        menu.setTitle("<html>Select a Record to Edit<html>", editTitleLabel, 306, 246, 700, 100, 40, editRecord, Color.black);
        smallNavBar(editRecord, listNavPanel);

        editButton.setFont(new Font("Arial", Font.BOLD, 18));
        editButton.setForeground(Color.WHITE);
        editButton.setBackground(new Color(123, 193, 58));  // Green color for button
        editButton.setBounds(895, 650, 273, 55);  // Set position and size
        editButton.setEnabled(false);
        editButton.addActionListener(e -> editSelectedRecord());

        // Add editButton to the frame
        editRecord.add(editButton);

        // Setup search field and search button
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchField.setBounds(352, 650, 200, 30);
        searchButton.setBounds(375, 690, 150, 55);
        searchButton.setBackground(new Color(123, 193, 58));
        searchButton.addActionListener(e -> searchRecord());

        editRecord.add(searchField);
        editRecord.add(searchButton);

        // Initialize the table with an empty model
        String[] columnNames = {"DATE", "TIME", "FIELD NUMBER", "SEASON", "TASK"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(353, 340, 816, 300);  // Position and size of the table
        editRecord.add(scrollPane);

        // Add listener for row selection in the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                editButton.setEnabled(selectedRow >= 0);  // Enable button if a row is selected
            }
        });

        createRoundedButton(editRecord, editContainer, 245, 156, 1042, 650, 64, new Color(217, 217, 217), null, null, null, 0, 0);

        setBackground(editRecord, featuresBg, featuresBgLabel, 0, 0);
        BlackTitleBarFrame(editRecord);
        editRecord.setVisible(true);
    }

    private void searchRecord() {
        String searchDate = searchField.getText();
        if (searchDate.isEmpty()) {
            JOptionPane.showMessageDialog(editRecord, "Please enter a date to search.", "Input Required", JOptionPane.WARNING_MESSAGE);
        } else {
            // Create table based on search result
            List<String[]> filteredRecords = searchRecordsByDate(searchDate);
            if (!filteredRecords.isEmpty()) {
                updateTableData(filteredRecords);
            } else {
                JOptionPane.showMessageDialog(editRecord, "No records found for the given date.", "No Records", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private List<String[]> searchRecordsByDate(String searchDate) {
        List<String[]> filteredRecords = new ArrayList<>();
        String[] columnNames = {"DATE", "TIME", "FIELD NUMBER", "SEASON", "TASK"};

        try (BufferedReader br = new BufferedReader(new FileReader(RECORDS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || !line.contains(",")) continue;

                String[] values = line.split(",");
                if (values.length == columnNames.length && values[0].equals(searchDate)) {
                    filteredRecords.add(values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filteredRecords;
    }

    private void updateTableData(List<String[]> filteredRecords) {
        // Clear the existing data from the table
        tableModel.setRowCount(0);

        // Add the filtered records to the table model
        for (String[] record : filteredRecords) {
            tableModel.addRow(record);
        }

        // Refresh the table
        table.revalidate();
        table.repaint();
    }

    public void editSelectedRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Get the values of the selected row
            String date = (String) table.getValueAt(selectedRow, 0);
            String time = (String) table.getValueAt(selectedRow, 1);
            String fieldNumber = (String) table.getValueAt(selectedRow, 2);
            String season = (String) table.getValueAt(selectedRow, 3);
            String task = (String) table.getValueAt(selectedRow, 4);

            // Prompt user to edit the values
            String newDate = JOptionPane.showInputDialog(editRecord, "Edit Date:", date);
            String newTime = JOptionPane.showInputDialog(editRecord, "Edit Time:", time);
            String newFieldNumber = JOptionPane.showInputDialog(editRecord, "Edit Field Number:", fieldNumber);
            String newSeason = JOptionPane.showInputDialog(editRecord, "Edit Season:", season);
            String newTask = JOptionPane.showInputDialog(editRecord, "Edit Task:", task);

            if (newDate != null && newTime != null && newFieldNumber != null && newSeason != null && newTask != null) {
                // Update the selected row in the table model
                tableModel.setValueAt(newDate, selectedRow, 0);
                tableModel.setValueAt(newTime, selectedRow, 1);
                tableModel.setValueAt(newFieldNumber, selectedRow, 2);
                tableModel.setValueAt(newSeason, selectedRow, 3);
                tableModel.setValueAt(newTask, selectedRow, 4);

                // Update the records file
                updateRecordInFile(date, time, fieldNumber, season, task, newDate, newTime, newFieldNumber, newSeason, newTask);
            }
        } else {
            JOptionPane.showMessageDialog(editRecord, "Please select a record to edit.", "No Record Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateRecordInFile(String date, String time, String fieldNumber, String season, String task,
                                    String newDate, String newTime, String newFieldNumber, String newSeason, String newTask) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RECORDS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    // Replace the old record with the new one
                    if (values[0].equals(date) && values[1].equals(time) && values[2].equals(fieldNumber)
                            && values[3].equals(season) && values[4].equals(task)) {
                        data.add(new String[]{newDate, newTime, newFieldNumber, newSeason, newTask});
                    } else {
                        data.add(values);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the updated data back to the file
        try (FileWriter fw = new FileWriter(RECORDS_FILE)) {
            for (String[] record : data) {
                fw.write(String.join(",", record) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EditRecord();
    }
}
