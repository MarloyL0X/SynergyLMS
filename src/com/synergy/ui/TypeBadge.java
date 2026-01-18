package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TypeBadge extends JPanel {
    private final String text;
    private final Color color;

    public TypeBadge(String text, Color color) {
        this.text = text;
        this.color = color;
        setOpaque(false);
        setPreferredSize(new Dimension(80, 22));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));
        
        java.awt.FontMetrics fm = g2.getFontMetrics();
        int w = fm.stringWidth(text);
        int h = fm.getAscent();
        
        g2.drawString(text, (getWidth() - w) / 2, (getHeight() + h) / 2 - 2);
    }
}
