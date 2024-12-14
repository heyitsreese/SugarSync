import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class menu extends UIHandler{
    JFrame menuFrame = new JFrame();
    ImageIcon menuBg = new ImageIcon("LandingPageBg.png");
    ImageIcon AddnewRecordIcon = new ImageIcon("AddNewRecordIcon.png");
    ImageIcon SearchRecordIcon = new ImageIcon("SearchRecordIcon.png");
    ImageIcon ListAllDatesIcon = new ImageIcon("ListAllDatesIcon.png");
    ImageIcon EditRecordIcon = new ImageIcon("EditRecordIcon.png");
    ImageIcon DeleteRecordIcon = new ImageIcon("DeleteRecordIcon.png");
    ImageIcon GenerateReportIcon = new ImageIcon("GenerateReportIcon.png");
    JPanel navbarBgPanel = new JPanel();
    JLabel menuBgLabel = new JLabel();
    JLabel newRecordLabel = new JLabel();
    JLabel newRecord = new JLabel();
    JLabel searchRecordLabel = new JLabel();
    JLabel searchRecord = new JLabel();
    JLabel listLabel = new JLabel();
    JLabel list = new JLabel();
    JLabel editRecordLabel = new JLabel();
    JLabel editRecord = new JLabel();
    JLabel deleteRecordLabel = new JLabel();
    JLabel deleteRecord = new JLabel();
    JLabel generateReportLabel = new JLabel();
    JLabel generateReport = new JLabel();
    JLabel exitMenuLabel = new JLabel();
    JLabel exitMenu = new JLabel();
    JLabel systemDesc = new JLabel();
    JLabel add = new JLabel();
    JLabel search = new JLabel();
    JLabel listAll = new JLabel();
    JLabel edit = new JLabel();
    JLabel delete = new JLabel();
    JLabel generate = new JLabel();
    JLabel exit = new JLabel();




    menu() {
        menuFrame.setTitle("SugarSync");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(null);
        menuFrame.setSize(1440, 1024);
        menuFrame.setResizable(true);
        menuFrame.setUndecorated(true);

        resizeImage(menuFrame, TitleLogo, TitleLogoLabel, 623, 666, 312);
        resizeImage(menuFrame, smallLogo, smallLogoLabel, 276, 102, 45);
        addIcons();
        menuChoices();
        createRects();
        newRecordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==newRecordLabel) {
                    new AddNewRecord();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
    
            }         

        });

        listLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==listLabel) {
                    new ListAllDatesWithRecord();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }         

        });
        editRecordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==editRecordLabel) {
                    new EditRecord();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }         

        });
        deleteRecordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==deleteRecordLabel) {
       
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
    
            }

            @Override
            public void mouseExited(MouseEvent e) {
     
            }         

        });
        exitMenuLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==exitMenuLabel) {

                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
      
            }

            @Override
            public void mouseExited(MouseEvent e) {
           
            }         

        });
       
        setText(systemDesc, 500, 570, 963, 35, "Streamline Your Field Operation - From Field To Yield!", 18, menuFrame, Color.white);

        addAboutTab(menuFrame, 1058, 83);
        addContributorsTab(menuFrame, 1165, 83);
    
        createRoundedButton(menuFrame, navbarBgPanel, -58, -28, 596, 925, 92, new Color(30, 77, 5), null, null, null, 0, 0);

        setBackground(menuFrame, menuBg, menuBgLabel, 0, 0);
        BlackTitleBarFrame(menuFrame);
        menuFrame.setVisible(true);
    }

    public void setTitle(String text, JLabel label, int x, int y, int width, int height, int size, JFrame frame, Color color) {
        label.setText(text);
        label.setFont(new Font("Outfit", Font.BOLD, size));
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        frame.add(label);
    }

    public void setText(JLabel label, int x, int y, int width, int height, String text, int size, JFrame frame, Color color){
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Outfit", Font.PLAIN, size));
        label.setForeground(color);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(label);
    }

    public void menuChoices() {
        setTitle("ADD NEW RECORD", newRecord, 202, 189, 237, 38, 20, menuFrame, Color.white);
        setTitle("LIST ALL DATES WITH RECORD", list, 202, 309, 330, 38, 20, menuFrame, Color.white);
        setTitle("EDIT RECORD", editRecord, 202, 429, 300, 38, 20, menuFrame, Color.white);
        setTitle("DELETE RECORD", deleteRecord, 202, 549, 300, 38, 20, menuFrame, Color.white);
        setTitle("EXIT", exitMenu, 202, 669, 300, 38, 20, menuFrame, Color.white);
    }

    public void addIcons () {
        resizeImage(menuFrame, AddnewRecordIcon, add, 56, 107, 188);
        resizeImage(menuFrame, ListAllDatesIcon, listAll, 62, 110, 303);
        resizeImage(menuFrame, EditRecordIcon, edit, 60, 116, 426);
        resizeImage(menuFrame, DeleteRecordIcon, delete, 52, 116, 545);
        resizeImage(menuFrame, ExitMenuIcon, exit, 25, 116, 680);
    }

    public void createRects() {
        createRect(menuFrame, newRecordLabel, 0, 144, 538, 116, new Color(30, 77, 5));
        createRect(menuFrame, listLabel, 0, 310, 538, 116, new Color(30, 77, 5));
        createRect(menuFrame, editRecordLabel, 0, 476, 538, 116, new Color(30, 77, 5));
        createRect(menuFrame, deleteRecordLabel, 0, 642, 538, 116, new Color(30, 77, 5));
        createRect(menuFrame, exitMenuLabel, 0, 808, 538, 116, new Color(30, 77, 5));
    }
}