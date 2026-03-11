package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;

public class SemesterProgressPanel extends RoundedPanel {

    public SemesterProgressPanel() {
        super(16, true);
        setBackground(ColorScheme.CARD_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        refresh();
    }
    
    public void refresh() {
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Mock data for overall progress
        int overallProgress = 78;

        // Progress Circle
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.insets = new Insets(0, 0, 0, 30);
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        CircleProgress circle = new CircleProgress(overallProgress, ColorScheme.PRIMARY_BLUE);
        circle.setPreferredSize(new Dimension(100, 100));
        add(circle, gbc);

        // Title
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        JLabel titleLabel = new JLabel("Общая успеваемость");
        titleLabel.setFont(com.synergy.utils.FontManager.getRegularFont(16));
        titleLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        add(titleLabel, gbc);

        // Percentage Value
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.LINE_START;
        JLabel valueLabel = new JLabel(overallProgress + "%");
        valueLabel.setFont(com.synergy.utils.FontManager.getBoldFont(48));
        valueLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        add(valueLabel, gbc);

        // Sub-text
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        JLabel subLabel = new JLabel("за текущую четверть");
        subLabel.setFont(com.synergy.utils.FontManager.getRegularFont(14));
        subLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        add(subLabel, gbc);

        revalidate();
        repaint();
    }
}
