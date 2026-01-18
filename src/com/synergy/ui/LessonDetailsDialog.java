package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LessonDetailsDialog extends JDialog {

    public LessonDetailsDialog(Frame owner, String subject, String type, String teacher, String topic, String homework) {
        super(owner, true);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // Transparent background for custom shape
        
        JPanel content = new RoundedPanel(20, true);
        content.setBackground(Color.WHITE);
        content.setLayout(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 0);
        
        // Header with Close button
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel(subject);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(ColorScheme.PRIMARY_TEXT);
        
        JLabel closeBtn = new JLabel("✕");
        closeBtn.setFont(new Font("Arial", Font.BOLD, 18));
        closeBtn.setForeground(Color.GRAY);
        closeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { dispose(); }
        });
        
        header.add(title, BorderLayout.CENTER);
        header.add(closeBtn, BorderLayout.EAST);
        
        content.add(header, gbc);
        
        gbc.insets = new Insets(0, 0, 20, 0);
        JLabel typeLabel = new JLabel(type);
        typeLabel.setForeground(ColorScheme.PRIMARY_RED);
        typeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        content.add(typeLabel, gbc);
        
        // Details
        addDetail(content, gbc, Localization.get("details.teacher"), teacher);
        addDetail(content, gbc, Localization.get("details.topic_prefix"), topic);
        
        // Homework (highlighted)
        gbc.insets = new Insets(10, 0, 5, 0);
        JLabel hwTitle = new JLabel(Localization.get("details.homework") + ":");
        hwTitle.setFont(new Font("Arial", Font.BOLD, 12));
        hwTitle.setForeground(ColorScheme.SECONDARY_TEXT);
        content.add(hwTitle, gbc);
        
        gbc.insets = new Insets(0, 0, 10, 0);
        RoundedPanel hwPanel = new RoundedPanel(10, false);
        hwPanel.setBackground(new Color(245, 247, 250));
        hwPanel.setLayout(new BorderLayout());
        hwPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel hwText = new JLabel("<html><body style='width: 250px'>" + homework + "</body></html>");
        hwText.setFont(new Font("Arial", Font.PLAIN, 13));
        hwPanel.add(hwText);
        
        content.add(hwPanel, gbc);
        
        add(content);
        pack();
        setLocationRelativeTo(owner);
    }
    
    private void addDetail(JPanel p, GridBagConstraints gbc, String label, String value) {
        gbc.insets = new Insets(5, 0, 2, 0);
        JLabel l = new JLabel(label);
        l.setFont(new Font("Arial", Font.BOLD, 11));
        l.setForeground(ColorScheme.SECONDARY_TEXT);
        p.add(l, gbc);
        
        gbc.insets = new Insets(0, 0, 10, 0);
        JLabel v = new JLabel("<html><body style='width: 300px'>" + value + "</body></html>");
        v.setFont(new Font("Arial", Font.PLAIN, 14));
        v.setForeground(ColorScheme.PRIMARY_TEXT);
        p.add(v, gbc);
    }
}
