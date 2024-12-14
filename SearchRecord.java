import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchRecord extends UIHandler {
    menu menu = new menu();
    AddNewRecord add = new AddNewRecord();
    JFrame searchRecord = new JFrame();
    ImageIcon featuresBg = new ImageIcon("FeaturesBg.png");
    ImageIcon blackLogo = new ImageIcon("blackLogo.png");
    JLabel featuresBgLabel = new JLabel();
    JLabel date = new JLabel();
    JLabel searchTitleLabel = new JLabel();
    JTextField getDate = new JTextField();
    JPanel searchContainer = new JPanel();
    JPanel searchButton = new JPanel();
    JPanel navBarJPanel = new JPanel();
    JLabel logoLabel = new JLabel();
    
    SearchRecord() {
        searchRecord.setTitle("SugarSync");
        searchRecord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchRecord.setLayout(null);
        searchRecord.setSize(1440, 1024); 
        searchRecord.setResizable(true);
        searchRecord.setUndecorated(true);

        addAboutTab(searchRecord, 1057, 68);
        addContributorsTab(searchRecord, 1164, 68);

        resizeImage(searchRecord, blackLogo, logoLabel, 185, 323, 285);
        menu.setTitle("<html>Search Record<br> By Date<html>", searchTitleLabel, 344, 343, 450, 100, 40, searchRecord, Color.black);
        menu.setTitle("DATE", date, 344, 512, 70, 25, 20, searchRecord, Color.black);
        add.createDate(344, 543, 205, 30, searchRecord, getDate);
        createRoundedButton(searchRecord, searchButton, 344, 644, 273, 55, 93, new Color(123, 193, 58), Color.white, "SEARCH", Color.black, 0, 0);
        smallNavBar(searchRecord, navBarJPanel);

        createRoundedButton(searchRecord, searchContainer, 245, 156, 1042, 650, 64, new Color(217, 217, 217), null, null, null, 0, 0);

        setBackground(searchRecord, featuresBg, featuresBgLabel, 0, 0);
        BlackTitleBarFrame(searchRecord);
        searchRecord.setVisible(true);
    }
}