import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.swing.*;

public class contributors extends UIHandler {
    JFrame contributors = new JFrame();
    UIHandler handler = new UIHandler();
    JLabel contributorsSugarSync = new JLabel();
    JLabel contributorsDesc = new JLabel();
    JLabel shekLink = new JLabel();
    JLabel reeseLink = new JLabel();
    JLabel zharLink = new JLabel();
    JLabel shekName = new JLabel();
    JLabel reeseName = new JLabel();
    JLabel zharName = new JLabel();

    contributors() {
        contributors.setTitle("SugarSync");
        contributors.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contributors.setLayout(null);
        contributors.setSize(1440, 1024);
        contributors.setResizable(true);
        contributors.setUndecorated(true);
    
        // Add the image first so it's in the background
        displayImage(contributors, "shek1.jpg", 200, 450, 158, 148, 500);
        displayImage(contributors, "reese.png", 600, 450, 158, 148, 500);
        displayImage(contributors, "zhar.png", 1000, 450, 158, 148, 500);
        displayImage(contributors, "github.png", 105, 700, 54, 46, 500);
        displayImage(contributors, "github.png", 505, 700, 54, 46, 500);
        displayImage(contributors, "github.png", 905, 700, 54, 46, 500);
    
        resizeImage(contributors, TitleLogo, TitleLogoLabel, 130, 655, 87);
        resizeImage(contributors, exitButton, exitButtonLabel, 25, 1300, 775);
        exitButtonLabel.addMouseListener(this);
    
        setTitle("CONTRIBUTORS", contributorsSugarSync, 655, 310, 238, 30, 16, contributors, Color.white);
        setText(contributorsDesc, 460, 291, 518, 140, "<html><div style='text-align: center;'>MEET THE CONTRIBUTORS</div><html>", 24, contributors, Color.white);
        setNames(shekName, 175, 600, 400, 30, "<html><div style='text-align: center;'>SHEKINA TAUTHO</div><html>", 24, contributors, Color.white);        
        setNames(reeseName, 555, 600, 400, 30, "<html><div style='text-align: center;'>SAMUEL REESE APUA</div><html>", 24, contributors, Color.white);        
        setNames(zharName, 930, 600, 400, 30, "<html><div style='text-align: center;'>ZHARINA ANGELA CANAG</div><html>", 24, contributors, Color.white);        

        setNames(shekLink, 160, 710, 300, 30, "<html><a href='https://github.com/Shekina-Tautho' style='color:white;'>https://github.com/Shekina-Tautho</a><html>", 16, contributors, Color.white);
        setNames(reeseLink, 575, 710, 300, 30, "<html><a href='https://github.com/heyitsreese' style='color:white;'>https://github.com/heyitsreese</a><html>", 16, contributors, Color.white);
        setNames(zharLink, 975, 710, 300, 30, "<html><a href='https://github.com/Zhrn7' style='color:white;'>https://github.com/Zhrn7</a><html>", 16, contributors, Color.white);
        
        addRoundedRectangle(100, 500, 360, 291, "#012809", 59);
        addRoundedRectangle(500, 500, 360, 291, "#012809", 59);
        addRoundedRectangle(900, 500, 360, 291, "#012809", 59);
    
        setBackground(contributors, landingPageBg, LandingPBgLabel, 0, 0);
        handler.BlackTitleBarFrame(contributors);
        contributors.setVisible(true);
    }

    private void displayImage(JFrame frame, String imagePath, int x, int y, int width, int height, int arcRadius) {
        // Check if file exists and print absolute path for debug
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            System.out.println("Image file not found at path: " + imagePath);
            return;
        }
        
        System.out.println("Image file exists at: " + imageFile.getAbsolutePath());
        
        // Load the image
        ImageIcon imageIcon = new ImageIcon(imagePath);
        if (imageIcon.getIconWidth() == -1 || imageIcon.getIconHeight() == -1) {
            System.out.println("Failed to load image.");
            return;
        }
        
        System.out.println("Image loaded successfully.");
        
        // Scale the image to the specified width and height
        Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        // Create a panel with rounded corners
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Apply rounded corners clip
                g2d.setClip(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcRadius, arcRadius));
                
                // Draw the scaled image within the rounded corner mask
                g2d.drawImage(scaledImage, 0, 0, this);
            }
        };
        
        // Set bounds for the image panel based on the specified width and height
        imagePanel.setBounds(x, y, width, height);
        imagePanel.setOpaque(false); // Transparency for the panel
        
        // Add the image panel to the frame
        frame.add(imagePanel);
        
        // Revalidate and repaint the frame to ensure everything is updated
        frame.revalidate();
        frame.repaint();
    }
    

    private void addRoundedRectangle(int x, int y, int width, int height, String colorHex, int arcRadius) {
        JPanel rectangle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.decode(colorHex));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcRadius, arcRadius);
            }
        };
        rectangle.setBounds(x, y, width, height);
        rectangle.setOpaque(false); // Ensures transparency for the corners
        contributors.add(rectangle);
    }

    public void setTitle(String text, JLabel label, int x, int y, int width, int height, int size, JFrame frame, Color color) {
        label.setText(text);
        label.setFont(new Font("Outfit", Font.BOLD, size));
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        frame.add(label);
    }

    public void setText(JLabel label, int x, int y, int width, int height, String text, int size, JFrame frame, Color color) {
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Calibri", Font.PLAIN, size));
        label.setForeground(color);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(label);
    }

    public void setNames(JLabel label, int x, int y, int width, int height, String text, int size, JFrame frame, Color color) {
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Outfit", Font.BOLD, size));
        label.setForeground(color);
        label.setText(text);
        frame.add(label);
    }
}
