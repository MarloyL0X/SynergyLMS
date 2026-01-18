package com.synergy;

import com.synergy.auth.LoginPanel;
import com.synergy.utils.ColorScheme;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public MainFrame() {
        setTitle("Synergy University LMS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setBackground(ColorScheme.APP_BACKGROUND);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        LoginPanel loginPanel = new LoginPanel(() -> {
            // Re-create dashboard to load user session data into header
            mainPanel.add(new DashboardPanel(), "DASHBOARD");
            cardLayout.show(mainPanel, "DASHBOARD");
        });
        
        mainPanel.add(loginPanel, "LOGIN");
        
        setContentPane(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
