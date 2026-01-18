package com.synergy.ui;

import com.synergy.auth.UserSession;
import com.synergy.utils.ColorScheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserProfileBadge extends JPanel {
    
    public UserProfileBadge() {
        setLayout(new BorderLayout(10, 0));
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        UserSession session = UserSession.getInstance();
        String name = session.getFullName() != null ? session.getFullName() : "Guest";
        String group = session.getGroup() != null ? session.getGroup() : "";
        
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        JLabel groupLabel = new JLabel(group);
        groupLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        groupLabel.setForeground(new Color(200, 200, 200));
        groupLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        textPanel.add(nameLabel, BorderLayout.NORTH);
        textPanel.add(groupLabel, BorderLayout.SOUTH);
        
        add(textPanel, BorderLayout.CENTER);
        add(new AvatarIcon(session.getInitials()), BorderLayout.EAST);
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Profile clicked");
            }
        });
    }
    
    private static class AvatarIcon extends JPanel {
        private final String initials;
        
        AvatarIcon(String initials) {
            this.initials = initials;
            setPreferredSize(new Dimension(36, 36));
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(ColorScheme.PRIMARY_RED);
            g2.fillOval(0, 0, 36, 36);
            
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            java.awt.FontMetrics fm = g2.getFontMetrics();
            int w = fm.stringWidth(initials);
            int h = fm.getAscent();
            g2.drawString(initials, (36 - w) / 2, (36 + h) / 2 - 2);
        }
    }
}
