package com.synergy.ui;

import com.synergy.utils.Animator;
import com.synergy.utils.ColorScheme;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

public class ThickProgressBar extends JComponent {
    private final int targetProgress;
    private float currentProgress = 0f;
    private final Animator animator;

    public ThickProgressBar(int progress) {
        this.targetProgress = progress;
        setPreferredSize(new Dimension(100, 8));
        
        animator = new Animator(1000, val -> {
            currentProgress = targetProgress * val;
            repaint();
        });
        
        javax.swing.Timer delay = new javax.swing.Timer(200, e -> animator.animateTo(1f));
        delay.setRepeats(false);
        delay.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(ColorScheme.PROGRESS_TRACK);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
        
        if (currentProgress > 0) {
            g2.setColor(ColorScheme.PRIMARY_RED);
            int w = (int) (getWidth() * (currentProgress / 100.0));
            g2.fillRoundRect(0, 0, Math.max(getHeight(), w), getHeight(), getHeight(), getHeight());
        }
    }
}
