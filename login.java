import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class login extends UIHandler {

    private final String adminUsername = "admin123";
    private final String adminPassword = "admin123";

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    about about = new about();
    JFrame loginFrame = new JFrame();
    ImageIcon loginBg = new ImageIcon("LoginBG.png");
    JLabel loginBgLabel = new JLabel();
    JLabel welcomeLabel = new JLabel();
    JLabel loginTextLabel = new JLabel();
    JLabel invalidLabel = new JLabel();
    JPanel loginPanel = new JPanel();
    JPanel loginButtonPanel = new JPanel();
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();

    login() {
        loginFrame.setTitle("SugarSync");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        loginFrame.setSize(1440, 1024); 
        loginFrame.setResizable(true);  
        loginFrame.setUndecorated(true);


        about.setTitle("Login", loginTextLabel, 772, 120, 150, 60, 40, loginFrame, Color.BLACK);
        about.setText(aboutLabel, 772, 196, 427, 60, "<html>Welcome back! Please login to your account.<html>", 24, loginFrame, Color.gray);

        about.setText(invalidLabel, 772, 196, 427, 60, "<html>Please enter a valid username or password<html>", 24, loginFrame, Color.gray);
        invalidLabel.setVisible(false);

        username.setBounds(772, 381, 497, 35);
        username.setOpaque(false);
        username.setBackground(Color.white);
        username.setText("Username");
        username.setFont(new Font("Calibri", Font.PLAIN, 20));
        username.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        username.setForeground(Color.gray);
        loginFrame.add(username);

        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().equals("Username")) {
                    username.setText("");
                    username.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (username.getText().trim().isEmpty()) {
                    username.setText("Username");
                    username.setFont(new Font("Calibri", Font.PLAIN, 20));
                    username.setForeground(Color.gray);
                }
            }
        });

        password.setBounds(772, 555, 497, 35);
        password.setOpaque(false);
        password.setBackground(Color.white);
        password.setEchoChar((char)0);
        password.setText("Password");
        password.setFont(new Font("Calibri", Font.PLAIN, 20));
        password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        password.setForeground(Color.gray);
        loginFrame.add(password);

        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(password.getPassword()).equals("Password")) {
                    password.setText("");
                    password.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(password.getPassword()).trim().isEmpty()) {
                    password.setText("Password");
                    password.setFont(new Font("Calibri", Font.PLAIN, 20));
                    password.setEchoChar((char)0);
                }
            }
        });

        
        ActionListener loginAction = e -> {
            String enteredUsername = username.getText().trim();
            String enteredPassword = String.valueOf(password.getPassword()).trim();

            if (enteredUsername.equals(getAdminUsername()) && enteredPassword.equals(getAdminPassword())) {
                new menu();      
                loginFrame.dispose();     
            } else if (enteredUsername.isEmpty() || enteredUsername.equals("Username") || enteredPassword.isEmpty() || enteredPassword.equals("Password")) {
                invalidLabel.setVisible(true);
                aboutLabel.setVisible(false);
            } else {
                invalidLabel.setVisible(true);
                aboutLabel.setVisible(false);
            }
        };
        
        createRoundedButtonLogin(loginFrame, loginButtonPanel, 803, 689, 435, 65, 93, Color.black, Color.white, "LOGIN", loginAction, new Color(123, 193, 58), 0, 0);
        createRoundedButton(loginFrame, loginPanel, 630, 0, 878, 900, 93, Color.white, null,   null, null, 0, 0);
        

        resizeImage(loginFrame, smallLogo, smallLogoLabel, 205, 55, 91);
        about.setTitle("<html>Welcome <br> Back!<html>", welcomeLabel, 120, 300, 450, 242, 100, loginFrame, Color.white);


        setBackground(loginFrame, loginBg, loginBgLabel, 0, 0);
        BlackTitleBarFrame(loginFrame);
        
        
        loginFrame.setVisible(true);
    }

    public static void createRoundedButtonLogin(JFrame frame, JPanel panel, int panelX, int panelY, int width, int height, int arc, Color buttonColor, Color textColor, String text, ActionListener actionListener, Color hoverColor, int hoverWidthIncrease, int hoverHeightIncrease) {
        panel.setLayout(null); 
        panel.setBounds(panelX, panelY, width, height); 
        panel.setOpaque(false); 
    
        final Color[] currentButtonColor = {buttonColor};
    
        JButton loginButton = new JButton(text) {
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
    
        loginButton.setBounds(0, 0, width, height); 
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false); 
        loginButton.setOpaque(false); 
        loginButton.addActionListener(actionListener); 
    
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (hoverColor != null) {
                    currentButtonColor[0] = hoverColor;
    
                    int newWidth = width + hoverWidthIncrease;
                    int newHeight = height + hoverHeightIncrease;
                    int newX = -(hoverWidthIncrease / 2); 
                    int newY = -(hoverHeightIncrease / 2);
                    loginButton.setBounds(newX, newY, newWidth, newHeight);
                    loginButton.repaint();
                }
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                if (hoverColor != null) {
                    currentButtonColor[0] = buttonColor;
    
                    loginButton.setBounds(0, 0, width, height);
                    loginButton.repaint();
                }
            }
        });
    
        panel.add(loginButton);
        frame.add(panel);
    }
}