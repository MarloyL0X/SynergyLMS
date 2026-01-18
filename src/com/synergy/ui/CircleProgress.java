package com.synergy.ui;

import com.synergy.utils.Animator;
import com.synergy.utils.ColorScheme;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class CircleProgress extends JPanel {
    private final int targetPercentage;
    private float currentPercentage = 0f;
    private final Color color;
    private final int size = 60;
    private final Animator animator;

    public CircleProgress(int percentage, Color color) {
        this.targetPercentage = percentage;
        this.color = color;
        setPreferredSize(new Dimension(size, size));
        setOpaque(false);
        
        // Animate over 1.5 seconds (1500ms)
        animator = new Animator(1500, val -> {
            currentPercentage = targetPercentage * val;
            repaint();
        });
        
        // Start animation after a short random delay to make it look organic? 
        // Or just start immediately. Let's add a small delay so UI can render first.
        javax.swing.Timer delay = new javax.swing.Timer(300, e -> animator.animateTo(1f));
        delay.setRepeats(false);
        delay.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int strokeWidth = 6;
        int diameter = Math.min(getWidth(), getHeight()) - strokeWidth;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        // Track (gray background circle)
        g2.setColor(new Color(230, 230, 230));
        g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawOval(x, y, diameter, diameter);

        // Progress Arc
        if (currentPercentage > 0) {
            g2.setColor(color);
            // -90 starts from top (12 o'clock)
            int angle = (int) (360 * (currentPercentage / 100.0));
            g2.drawArc(x, y, diameter, diameter, 90, -angle);
        }
    }
}
