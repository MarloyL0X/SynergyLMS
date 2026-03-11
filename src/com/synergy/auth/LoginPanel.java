package com.synergy.auth;

import com.synergy.utils.FontManager;
import com.synergy.utils.Localization;
import com.synergy.ui.PillButton;
import com.synergy.ui.StyledPasswordField;
import com.synergy.ui.StyledTextField;
import com.synergy.ui.SynergyIcons;
import com.synergy.utils.ColorScheme;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {
    private final Runnable onLoginSuccess;
    
    public LoginPanel(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
        initializePanel();
        add(createLoginCard());
    }
    
    private void initializePanel() {
        setLayout(new GridBagLayout());
        setBackground(ColorScheme.APP_BACKGROUND);
    }
    
    private JPanel createLoginCard() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        
        addComponentsToCard(card);
        return card;
    }
    
    private void addComponentsToCard(JPanel card) {
        GridBagConstraints gbc = createGridBagConstraints();
        
        card.add(createLogoLabel(), gbc);
        
        gbc.insets = new Insets(0, 0, 10, 0);
        card.add(new JLabel(Localization.get("login.title")), gbc);
        
        StyledTextField loginField = new StyledTextField(Localization.get("login.username"));
        card.add(loginField, gbc);
        
        gbc.insets = new Insets(10, 0, 10, 0);
        card.add(new JLabel(Localization.get("login.password")), gbc);
        
        StyledPasswordField passField = new StyledPasswordField(Localization.get("login.password"));
        card.add(passField, gbc);
        
        gbc.insets = new Insets(20, 0, 10, 0);
        card.add(createLoginButton(loginField), gbc);
        
        gbc.insets = new Insets(10, 0, 0, 0);
        card.add(createHelpLabel(), gbc);
    }
    
    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0);
        return gbc;
    }
    
    private JLabel createLogoLabel() {
        JLabel logo = new JLabel(Localization.get("app.title"), SynergyIcons.getLogoIcon(32), JLabel.CENTER);
        logo.setFont(FontManager.getBoldFont(20));
        logo.setForeground(ColorScheme.PRIMARY_TEXT);
        logo.setIconTextGap(10);
        return logo;
    }
    
    private PillButton createLoginButton(StyledTextField loginField) {
        PillButton loginBtn = new PillButton(Localization.get("login.btn"), true);
        loginBtn.setPreferredSize(new java.awt.Dimension(300, 40));
        loginBtn.addActionListener(e -> performLogin(loginField.getText()));
        return loginBtn;
    }
    
    private void performLogin(String username) {
        if (!username.isEmpty()) {
            UserSession.getInstance().login(username, "IK-101", username + "@school.ru");
        } else {
            UserSession.getInstance().login("Ученик", "5-А", "pupil@test.com");
        }
        onLoginSuccess.run();
    }
    
    private JLabel createHelpLabel() {
        JLabel helpLabel = new JLabel("<html><u>" + Localization.get("login.no_account") + "</u></html>");
        helpLabel.setForeground(ColorScheme.PRIMARY_RED);
        helpLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        helpLabel.setHorizontalAlignment(JLabel.CENTER);
        helpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showHelpDialog();
            }
        });
        return helpLabel;
    }
    
    private void showHelpDialog() {
        JOptionPane.showMessageDialog(this, 
            "Пожалуйста, свяжитесь с администрацией школы для получения учетных данных.\nТелефон: +7 (495) 123-45-67", 
            "Информация о доступе", JOptionPane.INFORMATION_MESSAGE);
    }
}
