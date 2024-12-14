import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class about extends UIHandler{
    JFrame about = new JFrame();
    UIHandler handler = new UIHandler();
    JLabel aboutSugarSync = new JLabel();
    JLabel aboutDesc = new JLabel();
    JLabel ourPurpose = new JLabel();
    JLabel purposeDesc = new JLabel();
    JLabel offer = new JLabel();
    JLabel offerDesc = new JLabel();


    about() {
        about.setTitle("SugarSync");
        about.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        about.setLayout(null);
        about.setSize(1440, 1024);
        about.setResizable(true);
        about.setUndecorated(true);

        
        resizeImage(about, TitleLogo, TitleLogoLabel, 130, 655, 87);
        resizeImage(about, exitButton, exitButtonLabel, 25, 1300, 775);
        exitButtonLabel.addMouseListener(this);

        
        setTitle("ABOUT SUGARSYNC", aboutSugarSync, 620, 236, 238, 30, 18, about, Color.white);
        setText(aboutDesc, 460, 291, 518, 140, "<html><div style='text-align: center;'>Welcome to SugarSync, a comprehensive sugarcane farm management system created specifically for sugarcane farming. Designed with farmers in mind, our system provides a reliable and efficient way to manage tasks, organize records, and streamline operations across every planting season. Whether you're overseeing planting, irrigation, or post-harvest cleanup, our platform ensures your farm's operation records are organized and easily accessible.</div><html>", 15, about, Color.white);
        
        
        setTitle("OUR PURPOSE", ourPurpose, 325, 538, 139, 25, 18, about, Color.white);
        setText(purposeDesc, 182, 583, 455, 140, "<html><div style='text-align: center;'>At SugarSync, our mission is to support sugarcane farmers in achieving greater efficiency and productivity by simplifying the process of logging and managing farm operations. We understand the challenges of managing farm operations and the importance of accurate records for decision-making. By improving the way records are kept, we help farmers focus on their work while keeping critical data easily accessible.</div><html>", 15, about, Color.white);

        //WHAT WE OFFER
        setTitle("WHAT WE OFFER", offer, 973, 538, 172, 25, 18, about, Color.white);
        setText(offerDesc, 831, 579, 455, 140, "<html><div style='text-align: center;'>Our system features an all-in-one platform for managing sugarcane farming records. Log daily tasks, organize activities, and keep track of operations with ease. Additionally, we provide powerful reporting tools to analyze land usage and to track recurring patterns and improve efficiency. Whether you're managing planting, harvesting, or post-harvest activities, we ensure your records are accurate, insightful, and actionable.</div><html>", 15, about, Color.white);

        setBackground(about, landingPageBg, LandingPBgLabel, 0, 0);
        handler.BlackTitleBarFrame(about);
        about.setVisible(true);

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
        label.setFont(new Font("Calibri", Font.PLAIN, size));
        label.setForeground(color);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(label);
    }
 }


