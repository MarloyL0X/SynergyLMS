package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MetricCard extends RoundedPanel {

    public MetricCard(String title, String value, Icon icon, int progress) {
        super(16, true);
        setBackground(ColorScheme.CARD_BACKGROUND);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        add(createHeader(title, icon), BorderLayout.NORTH);
        add(createValue(value), BorderLayout.CENTER);
        add(new ThickProgressBar(progress), BorderLayout.SOUTH);
    }

    private JPanel createHeader(String title, Icon icon) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        titleLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        
        JLabel iconLabel = new JLabel(icon);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(iconLabel, BorderLayout.EAST);
        return headerPanel;
    }

    private JLabel createValue(String val) {
        JLabel valueLabel = new AnimatedValueLabel(val, new Font("Arial", Font.BOLD, 48));
        valueLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return valueLabel;
    }
}
