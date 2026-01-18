package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
    private final int radius;
    private final boolean shadow;

    public RoundedPanel(int radius, boolean shadow) {
        this.radius = radius;
        this.shadow = shadow;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (shadow) {
            g2.setColor(ColorScheme.SHADOW_COLOR);
            g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 2, radius, radius);
        }
        
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - (shadow ? 4 : 0), getHeight() - (shadow ? 4 : 0), radius, radius);
        super.paintComponent(g);
    }
}
