import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class UIHandler extends JFrame implements ActionListener, MouseListener {

    private static Color buttonColor; 
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    Image image;
    ImageIcon landingPageBg = new ImageIcon("LandingPageBg.png");
    ImageIcon TitleLogo = new ImageIcon("LargeLogo.png");
    ImageIcon smallLogo = new ImageIcon("smallLogo.png");
    ImageIcon exitButton = new ImageIcon("ExitButton.png");
    ImageIcon plantLogo = new ImageIcon("plantLogo.png");
    ImageIcon AddnewRecordIcon = new ImageIcon("AddNewRecordIcon.png");
    ImageIcon SearchRecordIcon = new ImageIcon("SearchRecordIcon.png");
    ImageIcon ListAllDatesIcon = new ImageIcon("ListAllDatesIcon.png");
    ImageIcon EditRecordIcon = new ImageIcon("EditRecordIcon.png");
    ImageIcon DeleteRecordIcon = new ImageIcon("DeleteRecordIcon.png");
    ImageIcon GenerateReportIcon = new ImageIcon("GenerateReportIcon.png");
    ImageIcon ExitMenuIcon = new ImageIcon("ExitButton.png");
    JLabel smallNavAdd = new JLabel();
    JLabel smallNavSearch = new JLabel();
    JLabel smallNavList = new JLabel();
    JLabel smallNavEdit = new JLabel();
    JLabel smallNavDelete = new JLabel();
    JLabel smallNavGen = new JLabel();
    JLabel smallNavExit = new JLabel();
    JLabel LandingPBgLabel = new JLabel();
    JLabel TitleLogoLabel = new JLabel();
    JLabel smallLogoLabel = new JLabel();
    JLabel aboutLabel = new JLabel();
    JLabel contributorsLabel = new JLabel();
    JLabel titleLabel = new JLabel();
    JLabel exitButtonLabel = new JLabel();
    JLabel plantLogoLabel = new JLabel();
    JPanel createButtonPanel = new JPanel();
    JPanel titleBar = new JPanel();

    

    UIHandler() {
        this.setTitle("SugarSync");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1440, 1024); //Used default desktop size since screen sizes are different for every computer
        this.setResizable(true);
        this.setUndecorated(true);

        resizeImage(this, TitleLogo, TitleLogoLabel, 820, 281, 240);
        resizeImage(this, smallLogo, smallLogoLabel, 180, 150, 74);
        addAboutTab(this, 1051, 96);  
        addContributorsTab(this, 1159, 96);
        createRoundedButton(this, createButtonPanel, 535, 650, 350, 65, 92, Color.white, Color.black, "LOGIN", new Color(123, 193, 58), 0, 0);
        createButtonPanel.addMouseListener(this);

        resizeImage(this, exitButton, exitButtonLabel, 25, 1300, 775);
        exitButtonLabel.addMouseListener(this);

        setBackground(this, landingPageBg, LandingPBgLabel, 0, 0);
        //BlackTitleBarFrame(this);
        this.setVisible(true);

    }

    //=====================================================================================================================================================================
       
    public void setBackground(JFrame frame, ImageIcon image, JLabel label, int x, int y) {
        Image img = image.getImage();
        int width = screensize.width;
        int height = screensize.height;
        Image newImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(newImage);
        label.setIcon(resizedIcon);
        label.setBounds(x, y, width, height);
        frame.add(label);
    }

    public void resizeImage(JFrame frame, ImageIcon image, JLabel label, int width, int x, int y) {
        Image img = image.getImage();
        if (img == null) {
            System.out.println("Error: Image not loaded");
            return;
        }
    
        int originalWidth = img.getWidth(null);
        int originalHeight = img.getHeight(null);
        if (originalWidth <= 0 || originalHeight <= 0) {
            System.out.println("Error: Invalid image dimensions");
            return;
        }
    
        int height = (int) ((double) originalHeight / originalWidth * width);
        Image newImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(newImage));
        label.setBounds(x, y, width, height);
    
        if (!frame.isAncestorOf(label)) {
            frame.add(label);
        }
    
        frame.revalidate();
        frame.repaint();
    }    
    

    public void addAboutTab(JFrame frame, int x, int y) {
        aboutLabel.setText("ABOUT");
        aboutLabel.setFont(new Font("Outfit", Font.BOLD, 16));
        aboutLabel.setForeground(Color.white);
        aboutLabel.setHorizontalTextPosition(JLabel.CENTER);
        aboutLabel.setBounds(x, y, 57, 20);
        aboutLabel.addMouseListener(this);
        frame.add(aboutLabel);

        aboutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==aboutLabel) {
                    new about();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                aboutLabel.setForeground(new Color(123, 193, 58));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                aboutLabel.setForeground(Color.white);
            }         

        });
    }

    public void addContributorsTab(JFrame frame, int x, int y) {
        contributorsLabel.setText("CONTRIBUTORS");
        contributorsLabel.setFont(new Font("Outfit", Font.BOLD, 16));
        contributorsLabel.setForeground(Color.white);
        contributorsLabel.setHorizontalTextPosition(JLabel.CENTER);
        contributorsLabel.setBounds(x, y, 130, 20);
        contributorsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==contributorsLabel) {
                   new contributors();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                contributorsLabel.setForeground(new Color(123, 193, 58));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                contributorsLabel.setForeground(Color.white);
            }         

        });


        frame.add(contributorsLabel);
    }

    public static void createRoundedButton(JFrame frame, JPanel panel, int panelX, int panelY, int width, int height, int arc, Color initialButtonColor, Color textColor, String text, Color hoverColor, int hoverWidthIncrease, int hoverHeightIncrease) {
        panel.setLayout(null);
        panel.setBounds(panelX, panelY, width, height);
        panel.setOpaque(false);
    
        final Color[] buttonColor = {initialButtonColor}; 
        final int originalX = panelX; 
        final int originalY = panelY;
    
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
    
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
                // Use the current button color
                g2d.setColor(buttonColor[0]);
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
            protected void paintBorder(Graphics g) {
            }
        };
    
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
    
        button.setBounds(0, 0, width, height);
    
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (hoverColor != null) { 
                    buttonColor[0] = hoverColor;
                    button.setBounds(
                        -hoverWidthIncrease / 2, 
                        -hoverHeightIncrease / 2, 
                        width + hoverWidthIncrease, 
                        height + hoverHeightIncrease
                    );
                    button.repaint();
                }
            }
    
            @Override
            public void mouseClicked(MouseEvent e) {
                new login();
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                if (hoverColor != null) { 
                    buttonColor[0] = initialButtonColor;
                    button.setBounds(0, 0, width, height);
                    button.repaint();
                }
            }
        });
    
        panel.add(button);
        frame.add(panel);
    }
    


    public void BlackTitleBarFrame(JFrame frame) {
            titleBar.setBackground(Color.black);
            titleBar.setPreferredSize(new Dimension(this.getWidth(),30));
            titleBar.setLayout(new BorderLayout());
    
            titleLabel.setForeground(Color.white);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setVerticalAlignment(SwingConstants.CENTER);
            titleBar.add(titleLabel, BorderLayout.CENTER);
            
            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(titleBar, BorderLayout.NORTH);
    }

    public void createRect(JFrame frame, JLabel label, int x, int y, int width, int height, Color bgColor) {
        label.setBounds(x, y, width, height);
        label.setOpaque(true);
        label.setBackground(bgColor);
        label.setFont(new Font("Outfit", Font.BOLD, 20));
        label.setHorizontalTextPosition(JLabel.CENTER);
        frame.add(label);
    }

    public void smallNavBar(JFrame frame, JPanel panel) {

        if (panel != null) {
            panel.setVisible(true);
            frame.add(panel);  // Add the panel explicitly here if it's not already
        }
        // Resize images for different buttons
        resizeImage(frame, plantLogo, plantLogoLabel, 60, 43, 40);
        resizeImage(frame, AddnewRecordIcon, smallNavAdd, 56, 49, 150);
        resizeImage(frame, ListAllDatesIcon, smallNavList, 62, 46, 250);
        resizeImage(frame, EditRecordIcon, smallNavEdit, 35, 58, 350);
        resizeImage(frame, DeleteRecordIcon, smallNavDelete, 52, 54, 450);
        resizeImage(frame, exitButton, smallNavExit, 34, 55, 550);
    
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change icon size and ensure visibility when hovered
                if (e.getSource() == smallNavAdd) {
                    resizeImage(frame, AddnewRecordIcon, smallNavAdd, 70, 49, 150);
                } else if (e.getSource() == smallNavList) {
                    resizeImage(frame, ListAllDatesIcon, smallNavList, 70, 50, 250);
                } else if (e.getSource() == smallNavEdit) {
                    resizeImage(frame, EditRecordIcon, smallNavEdit, 40, 60, 350);
                } else if (e.getSource() == smallNavDelete) {
                    resizeImage(frame, DeleteRecordIcon, smallNavDelete, 55, 60, 450);
                } else if (e.getSource() == smallNavExit) {
                    resizeImage(frame, exitButton, smallNavExit, 40, 60, 550);
                }
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                // Reset icons to original size when mouse exits
                if (e.getSource() == smallNavAdd) {
                    resizeImage(frame, AddnewRecordIcon, smallNavAdd, 56, 49, 150);
                } else if (e.getSource() == smallNavList) {
                    resizeImage(frame, ListAllDatesIcon, smallNavList, 62, 50, 250);
                } else if (e.getSource() == smallNavEdit) {
                    resizeImage(frame, EditRecordIcon, smallNavEdit, 35, 60, 350);
                } else if (e.getSource() == smallNavDelete) {
                    resizeImage(frame, DeleteRecordIcon, smallNavDelete, 52, 60, 450);
                } else if (e.getSource() == smallNavExit) {
                    resizeImage(frame, exitButton, smallNavExit, 40, 55, 550);
                }
            }
    
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Close the current window
                if (e.getSource() == smallNavAdd) {
                    new AddNewRecord(); // Open AddNewRecord window
                } else if (e.getSource() == smallNavSearch) {
                } else if (e.getSource() == smallNavList) {
                    new ListAllDatesWithRecord(); // Open ListAllDates window
                } else if (e.getSource() == smallNavEdit) {
                    new EditRecord(); // Open EditRecord window
                } else if (e.getSource() == smallNavDelete) {
                    new DeleteRecord();
                } else if (e.getSource() == smallNavGen) {
                    // new GenerateReport(); // Open GenerateReport window
                } else if (e.getSource() == smallNavExit) {
                    System.exit(0); 
                }
            }
        };
    
        // Attach the same MouseListener to all buttons
        smallNavAdd.addMouseListener(mouseAdapter);
        smallNavSearch.addMouseListener(mouseAdapter);
        smallNavList.addMouseListener(mouseAdapter);
        smallNavEdit.addMouseListener(mouseAdapter);
        smallNavDelete.addMouseListener(mouseAdapter);
        smallNavGen.addMouseListener(mouseAdapter);
        smallNavExit.addMouseListener(mouseAdapter);
    
        // Create the rounded button at the end
        createRoundedButton(frame, panel, -438, -10, 598, 900, 107, new Color(30, 77, 5), null, null, null, 0, 0);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==aboutLabel) {
            this.dispose();
            this.setVisible(false);
            new menu();
        }   
        if (e.getSource()==exitButtonLabel) {
            System.exit(0);
        }
        if (e.getSource()==contributorsLabel) {
            this.dispose();
            this.setVisible(false);
            new contributors();
        }

    }

    public void saveToCSV(String filename, String data) {
        try (FileWriter csvWriter = new FileWriter(filename, true)) {
          csvWriter.write(data);
          csvWriter.write("\n");
          csvWriter.flush();
          System.out.println("Record successfully saved to " + filename);
        } catch (IOException e) {
          System.out.println("Error while saving to CSV: " + e.getMessage());
        }
    }

    public List<String[]> readFromCSV(String filename) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                records.add(data);
            }
        } catch (IOException e) {
            System.out.println("Error while reading CSV: " + e.getMessage());
        }
        return records;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }
}