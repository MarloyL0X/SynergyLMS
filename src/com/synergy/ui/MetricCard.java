package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.FontManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MetricCard extends RoundedPanel {

    public MetricCard(String title, String value, Icon icon, int progress) {
        super(16, true);
        setBackground(ColorScheme.CARD_BACKGROUND);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();

        // Icon
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 15);
        JLabel iconLabel = new JLabel(icon);
        add(iconLabel, gbc);

        // Title
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.insets = new Insets(0, 0, 0, 0);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(FontManager.getRegularFont(14));
        titleLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        add(titleLabel, gbc);

        // Value
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(FontManager.getBoldFont(36));
        valueLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        add(valueLabel, gbc);
        
        // Progress Bar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(15, 0, 0, 0);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(progress);
        progressBar.setBorderPainted(false);
        progressBar.setForeground(ColorScheme.PRIMARY_BLUE);
        progressBar.setBackground(ColorScheme.PROGRESS_TRACK);
        add(progressBar, gbc);
    }
}
