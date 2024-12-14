import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ListAllDatesWithRecord extends UIHandler {
    private static final String TASKS_FILE = "tasks.csv";
    private static final String RECORDS_FILE = "records.csv";
    menu menu = new menu();
    AddNewRecord add = new AddNewRecord();
    JFrame listAll = new JFrame();
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
    
    
    ListAllDatesWithRecord() {
        listAll.setTitle("SugarSync");
        listAll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listAll.setLayout(null);
        listAll.setSize(1440, 1024); 
        listAll.setResizable(true);
        listAll.setUndecorated(true);

        addAboutTab(listAll, 1057, 68);
        addContributorsTab(listAll, 1164, 68);

        resizeImage(listAll, blackLogo, logolabel, 200, 285, 181);
        menu.setTitle("<html>List All Dates With Record<html>", searchTitleLabel, 306, 246, 700, 100, 40, listAll, Color.black);
        //menu.setTitle("YEAR", year, 344, 512, 70, 25, 20, listAll, Color.black);
       // add.createDate(344, 543, 205, 30, listAll, getDate);
        //createRoundedButton(listAll, searchButton, 344, 644, 273, 55, 93, new Color(123, 193, 58), Color.white, "LIST", Color.black, 0, 0);
        smallNavBar(listAll, listNavPanel);

        JScrollPane scrollPane = createTableFromFile(RECORDS_FILE);
        if (scrollPane != null) {
            scrollPane.setBounds(353, 340, 816 , 420); 
            listAll.add(scrollPane);
        }

        //createRect(listAll, resultLabel, 682, 156, 605, 650, Color.white);
        createRoundedButton(listAll, searchContainer, 245, 156, 1042, 650, 64, new Color(217, 217, 217), null, null, null, 0, 0);
        
        setBackground(listAll, featuresBg, featuresBgLabel, 0, 0);
        BlackTitleBarFrame(listAll);
        listAll.setVisible(true);
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