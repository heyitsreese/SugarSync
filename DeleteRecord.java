import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class DeleteRecord extends UIHandler {
    private static final String TASKS_FILE = "tasks.csv";
    private static final String RECORDS_FILE = "records.csv";
    menu menu = new menu();
    AddNewRecord add = new AddNewRecord();
    JFrame deleteRecord = new JFrame();
    ImageIcon featuresBg = new ImageIcon("FeaturesBg.png");
    ImageIcon blackLogo = new ImageIcon("blackLogo.png");
    JLabel logolabel = new JLabel();
    JLabel featuresBgLabel = new JLabel();
    JLabel searchTitleLabel = new JLabel();
    JLabel year = new JLabel();
    JLabel resultLabel = new JLabel();
    JTextField getDate = new JTextField();
    JPanel searchContainer = new JPanel();
    JPanel searchButton = new JPanel();
    JPanel listNavPanel = new JPanel();
    JButton deleteButton = new JButton("DELETE");
    JTable table;
    DefaultTableModel tableModel;


    
    
    DeleteRecord() {
        deleteRecord.setTitle("SugarSync");
        deleteRecord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteRecord.setLayout(null);
        deleteRecord.setSize(1440, 1024); 
        deleteRecord.setResizable(true);
        deleteRecord.setUndecorated(true);

        addAboutTab(deleteRecord, 1057, 68);
        addContributorsTab(deleteRecord, 1164, 68);

        resizeImage(deleteRecord, blackLogo, logolabel, 200, 285, 181);
        menu.setTitle("<html>Select a Record to Delete<html>", searchTitleLabel, 306, 246, 700, 100, 40, deleteRecord, Color.black);
        smallNavBar(deleteRecord, listNavPanel);

        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(123, 193, 58));  // Green color for button
        deleteButton.setBounds(600, 700, 273, 55);  // Set position and size
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> deleteSelectedRecord());

        // Add deleteButton to the frame
        deleteRecord.add(deleteButton);


        JScrollPane scrollPane = createTableFromFile(RECORDS_FILE);
        if (scrollPane != null) {
            scrollPane.setBounds(353, 340, 816, 300);  // Position and size of the table
            deleteRecord.add(scrollPane);

            // Get the JTable from the JScrollPane
            table = (JTable) scrollPane.getViewport().getView();

            // Initialize tableModel and link it to the JTable
            tableModel = (DefaultTableModel) table.getModel();
        }

        // Add listener for row selection in the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                deleteButton.setEnabled(selectedRow >= 0);  // Enable button if a row is selected
            }
        });


        //createRect(deleteRecord, resultLabel, 682, 156, 605, 650, Color.white);
        createRoundedButton(deleteRecord, searchContainer, 245, 156, 1042, 650, 64, new Color(217, 217, 217), null, null, null, 0, 0);
        
        setBackground(deleteRecord, featuresBg, featuresBgLabel, 0, 0);
        BlackTitleBarFrame(deleteRecord);
        deleteRecord.setVisible(true);
    }

    public void deleteSelectedRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Get the values of the selected row
            String date = (String) table.getValueAt(selectedRow, 0);
            String time = (String) table.getValueAt(selectedRow, 1);
            String fieldNumber = (String) table.getValueAt(selectedRow, 2);
            String season = (String) table.getValueAt(selectedRow, 3);
            String task = (String) table.getValueAt(selectedRow, 4);

            // Confirm deletion
            int response = JOptionPane.showConfirmDialog(deleteRecord,
                "Are you sure you want to delete this record?\n" + date + " " + time + " " + task,
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            // If confirmed, delete the record
            if (response == JOptionPane.YES_OPTION) {
                // Remove the selected row from the table model
                tableModel.removeRow(selectedRow);

                // Also remove the record from the records.csv file
                deleteRecordFromFile(date, time, fieldNumber, season, task);
            }
        } else {
            JOptionPane.showMessageDialog(deleteRecord, "Please select a record to delete.", "No Record Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteRecordFromFile(String date, String time, String fieldNumber, String season, String task) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RECORDS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Add the line to the list if it does not match the record to be deleted
                if (values.length == 5 && !(values[0].equals(date) && values[1].equals(time) && values[2].equals(fieldNumber)
                        && values[3].equals(season) && values[4].equals(task))) {
                    data.add(values);
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

    public static JScrollPane createTableFromFile(String fileName) {
        String[] columnNames = {"DATE", "TIME", "FIELD NUMBER", "SEASON", "TASK"};
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || !line.contains(",")) continue;

                String[] values = line.split(",");
                if (values.length == columnNames.length) {
                    data.add(values);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String[][] tableData = data.toArray(new String[0][0]);
        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        return scrollPane;
    }
}