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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SemesterProgressPanel extends RoundedPanel {

    public SemesterProgressPanel() {
        super(16, true);
        setBackground(ColorScheme.CARD_BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        refresh();
    }
    
    public void refresh() {
        removeAll();
        setLayout(new GridLayout(1, 8, 10, 0)); 
        
        add(createItem(1, 100));
        add(createItem(2, 100));
        add(createItem(3, 100));
        add(createItem(4, 100));
        add(createItem(5, 100));
        add(createItem(6, 100));
        add(createItem(7, 38));
        add(createItem(8, 0));
        
        revalidate();
        repaint();
    }

    private JPanel createItem(int semester, int percent) {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        
        p.add(createPercentLabel(percent), BorderLayout.NORTH);
        p.add(createProgressCircle(percent), BorderLayout.CENTER);
        p.add(createBottomPanel(semester), BorderLayout.SOUTH);
        
        return p;
    }
    
    private JLabel createPercentLabel(int percent) {
        JLabel percentLabel = new JLabel(percent + "%", SwingConstants.CENTER);
        percentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        percentLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        return percentLabel;
    }
    
    private JPanel createProgressCircle(int percent) {
        Color c;
        if (percent == 100) c = new Color(76, 175, 80); 
        else if (percent > 0) c = new Color(255, 193, 7); 
        else c = Color.LIGHT_GRAY;
        
        JPanel circleWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        circleWrapper.setOpaque(false);
        circleWrapper.add(new CircleProgress(percent, c));
        return circleWrapper;
    }
    
    private JPanel createBottomPanel(int semester) {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        
        JLabel caption = new JLabel(semester + " " + Localization.get("semester"), SwingConstants.CENTER);
        caption.setFont(new Font("Arial", Font.PLAIN, 12));
        caption.setForeground(ColorScheme.SECONDARY_TEXT);
        bottomPanel.add(caption, BorderLayout.CENTER);
        
        if (semester == 7) {
            JPanel line = new JPanel();
            line.setBackground(new Color(76, 175, 80)); 
            line.setPreferredSize(new Dimension(40, 2));
            bottomPanel.add(line, BorderLayout.SOUTH);
        } else {
            bottomPanel.add(new JLabel(" "), BorderLayout.SOUTH);
        }
        return bottomPanel;
    }
}
