import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;


public class AddNewRecord extends UIHandler{

    private static final String TASKS_FILE = "tasks.csv";
    private static final String RECORDS_FILE = "records.csv";
    List<String> tasks = new ArrayList<>();
    String[] choices = {"AM", "PM"};
    String[] seasonChoices = {"Pre-Planting", "Planting", "Growing", "Harvesting"};
    String[] prePlantingChoices = {};

    
    menu menu = new menu();
    JFrame newRecord = new JFrame();
    ImageIcon featuresBg = new ImageIcon("FeaturesBg.png");
    ImageIcon blackLogo = new ImageIcon("blackLogo.png");
    JLabel logoLabel = new JLabel();
    JLabel featuresBgLabel = new JLabel();
    JLabel date = new JLabel();
    JLabel addTitle = new JLabel();
    JLabel invalidLabel = new JLabel();
    JLabel time = new JLabel();
    JLabel season = new JLabel();
    JLabel fieldNum = new JLabel();
    JLabel task = new JLabel();
    JLabel errorLabel = new JLabel();
    JPanel inputPanel = new JPanel();
    JPanel navBarPanel = new JPanel();
    JPanel addRecordPanel = new JPanel();
    JTextField addDate = new JTextField();
    JTextField addField = new JTextField();
    final JComboBox<String> cb = new JComboBox<String>(choices);
    final JComboBox<String> cbSeason = new JComboBox<String>(seasonChoices);
    String seasonChoice = (String) cbSeason.getSelectedItem();
    final JComboBox<String> cbTask = new JComboBox<String>();


    AddNewRecord() {

        String defaultSeason = (String) cbSeason.getSelectedItem(); 
            List<String> defaultTasks = loadForChosenSeason(defaultSeason);
            for (String task : defaultTasks) {
        cbTask.addItem(task);
        }


    
        
        newRecord.setTitle("SugarSync");
        newRecord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newRecord.setLayout(null);
        newRecord.setSize(1440, 1024); 
        newRecord.setResizable(true);
        newRecord.setUndecorated(true);

        addAboutTab(newRecord, 1057, 68);
        addContributorsTab(newRecord, 1164, 68);

        resizeImage(newRecord, blackLogo, logoLabel, 177, 374, 209);
        menu.setTitle("Add New Record", addTitle, 374, 269, 350, 50, 40, newRecord, Color.black);
        menu.setTitle("DATE", date, 374, 415, 70, 25, 20, newRecord, Color.black);
        menu.setTitle("TIME", time, 674, 415, 55, 25, 20, newRecord, Color.black);
        menu.setTitle("FIELD NUMBER", fieldNum, 916, 417, 200, 25, 20, newRecord, Color.black);
        menu.setTitle("SEASON", season, 374, 553, 108, 25, 20, newRecord, Color.black);
        menu.setTitle("TASK", task, 774, 553, 100, 25, 20, newRecord, Color.BLACK);

        createDate(375, 446, 205, 30, newRecord, addDate);
        createTime();
        createFieldNumber();
        loadTasks();
        loadTasksPerSeason();

        menu.setText(invalidLabel, 564, 650, 465, 20, "Invalid or missing input. Please recheck and submit again.", 18, newRecord, Color.red);
        invalidLabel.setVisible(false);
        
        addRecordButton(newRecord, addRecordPanel, 584, 700, 400, 64, 92, new Color(123, 193, 58), Color.white, "ADD RECORD", this, Color.black);
        

        createRoundedButton(newRecord, inputPanel, 245, 135, 1042, 700, 64, Color.white, null, null, null, 0, 0);
        smallNavBar(newRecord, navBarPanel);

        setBackground(newRecord, featuresBg, featuresBgLabel, 0, 0);
        BlackTitleBarFrame(newRecord);
        newRecord.setVisible(true);
        
        
    }

    //==========================================================================================================================

    public void AddNewCSV() {
        createCSVIfNotExists(RECORDS_FILE);
    }

    public AddNewRecord(String recordsFile) {
        createCSVIfNotExists(recordsFile);
    }

        private void createCSVIfNotExists(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write("Date,AM/PM,Field Number,Season,Task\n"); 
            } catch (IOException e) {     
                
            }
        }
    
    }

    public void addRecord(int m1, int m2, int d1, int d2, int y1, int y2, int y3, int y4) {
        if (addDate.equals(m1 + m2 + "/" + d1 + d2 + "/" + y1 + y2 + y3 + y4)) {
            
        } 
        else {
            invalidLabel.setVisible(true);
        }
    }

    public void addRecordButton(JFrame frame, JPanel panel, int panelX, int panelY, int width, int height, int arc, Color buttonColor, Color textColor, String text, ActionListener actionListener, Color hoverColor) {
        panel.setLayout(null);
        panel.setBounds(panelX, panelY, width, height);
        panel.setOpaque(false);

        final Color[] currentButtonColor = {buttonColor};

        JButton addRecordButton = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.SrcOver);
    
             
                g2d.setColor(currentButtonColor[0]);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
    
                g2d.setColor(textColor);
                g2d.setFont(new Font("Outfit", Font.BOLD, 32)); 
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2d.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);
                g2d.dispose();
            }
    
            @Override
            protected void paintBorder(Graphics g) {}
        };

        addRecordButton.setBounds(0, 0, width, height);
        addRecordButton.setFocusPainted(false);
        addRecordButton.setBorderPainted(false);
        addRecordButton.setContentAreaFilled(false); 
        addRecordButton.setOpaque(false); 
        addRecordButton.addActionListener(actionListener); 

        addRecordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (hoverColor != null) {
                    currentButtonColor[0] = hoverColor;
                    addRecordButton.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (hoverColor !=null) {
                    currentButtonColor[0] = buttonColor;
                    addRecordButton.repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                addRecordToCSV(); 
    }
});

        panel.add(addRecordButton);
        frame.add(panel);
    }

public void createDate(int x, int y, int width, int height, JFrame frame, JTextField textField) {
        textField.setBounds(x, y, width, height);
        textField.setOpaque(false);
        textField.setBackground(Color.white);
        textField.setText("MM/DD/YYYY");
        textField.setFont(new Font("Calibri", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        textField.setForeground(Color.gray);
        frame.add(textField);

        // Focus listener for clearing placeholder text
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("MM/DD/YYYY")) {
                    textField.setText("");
                    textField.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText("MM/DD/YYYY");
                    textField.setFont(new Font("Calibri", Font.PLAIN, 18));
                    textField.setForeground(Color.gray);
                } else {
                    // Validate the entered date when the focus is lost
                    validateDate(textField.getText(), textField);
                }
            }
        });
    }

    // Validate the date
    private void validateDate(String inputDate, JTextField textField) {
        // Check if the input matches the MM/DD/YYYY format using regular expression
        String regex = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$";
        if (!inputDate.matches(regex)) {
            textField.setForeground(Color.red);
            textField.setText("Invalid Date Format");
            return;
        }
        
        // Validate the date using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false); // Disable lenient mode to prevent invalid date parsing
        try {
            Date date = sdf.parse(inputDate);
            textField.setForeground(Color.black); // Valid date
        } catch (Exception e) {
            textField.setForeground(Color.red);
            textField.setText("Invalid Date");
        }
    }

    public void createTime() {
        cb.setBounds(675, 446, 205, 30);
        newRecord.add(cb);
    }

    public void createFieldNumber() {
        addField.setBounds(917, 446, 205, 30);
        addField.setOpaque(false);
        addField.setBackground(Color.white);
        addField.setText("e.g. 2A");
        addField.setFont(new Font("Calibri", Font.PLAIN, 18));
        addField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        addField.setForeground(Color.gray);
        newRecord.add(addField);

        addField.addFocusListener((FocusListener) new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (addField.getText().equals("e.g. 2A")) {
                    addField.setText("");
                    addField.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (addField.getText().trim().isEmpty()) {
                    addField.setText("e.g. 2A");
                    addField.setFont(new Font("Calibri", Font.PLAIN, 18));
                    addField.setForeground(Color.gray);
                }
            }

        });
    }

    public void loadTasks() {
        cbSeason.setBounds(376, 584, 310, 30);
        cbSeason.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSeason = (String) cbSeason.getSelectedItem();
                List<String> taskChoices = loadForChosenSeason(selectedSeason);
         
                cbTask.removeAllItems(); 
                for (String task : taskChoices) {
                    cbTask.addItem(task); 
                }
            }
        });
        newRecord.add(cbSeason);
    }
    

    public void loadTasksPerSeason() {
        cbTask.setBounds(776, 584, 310, 30);
        newRecord.add(cbTask);
    }

    public List<String> loadForChosenSeason(String season) {
        tasks.clear(); 
        boolean isCorrectSeason = false;
    
        try (Scanner fileScanner = new Scanner(new File(TASKS_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
    
                
                if (line.endsWith("Season") && line.equalsIgnoreCase(season + " Season")) {
                    isCorrectSeason = true;
                    continue; // Skip the season name itself
                }
    
                
                if (line.endsWith("Season") && !line.equalsIgnoreCase(season + " Season")) {
                    isCorrectSeason = false;
                }
    
                
                if (isCorrectSeason && !line.isEmpty() && !line.endsWith("Season")) {
                    tasks.add(line); 
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading tasks.csv");
        }
    
        return tasks;
    }

    public void addRecordToCSV() {
        // Get user inputs
        String date = addDate.getText().trim();
        String time = (String) cb.getSelectedItem();
        String fieldNumber = addField.getText().trim();
        String season = (String) cbSeason.getSelectedItem();
        String task = (String) cbTask.getSelectedItem();
    
    
        if (date.isEmpty() || fieldNumber.isEmpty() || season == null || task == null) {
            invalidLabel.setVisible(true);
            invalidLabel.setText("Invalid or missing input. Please recheck and submit again.");
            return;
        }

        String regex = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$";
        if (!date.matches(regex)) {
            invalidLabel.setVisible(true);
            invalidLabel.setText("Invalid date format. Please use MM/DD/YYYY.");
            return;
        }
    
    
        try (FileWriter writer = new FileWriter(RECORDS_FILE, true)) {
            String record = String.format("%s,%s,%s,%s,%s\n", date, time, fieldNumber, season, task);
            writer.write(record);
            invalidLabel.setVisible(false);
            JOptionPane.showMessageDialog(newRecord, "Record added successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(newRecord, "An error occurred while saving the record.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}