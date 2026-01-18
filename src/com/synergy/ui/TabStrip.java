package com.synergy.ui;

import com.synergy.utils.AnimationUtils;
import com.synergy.utils.Animator;
import com.synergy.utils.ColorScheme;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class TabStrip extends JPanel {
    private Component activeComponent;
    private final Animator animator;
    
    private float currentX = 0f;
    private float currentWidth = 0f;
    
    private float targetX = 0f;
    private float targetWidth = 0f;
    
    private float startX = 0f;
    private float startWidth = 0f;

    public TabStrip() {
        setOpaque(false);
        // Custom layout logic isn't needed if we use FlowLayout outside, 
        // but painting needs absolute coordinates relative to this panel.
        
        animator = new Animator(300, progress -> {
            // Interpolate X and Width
            currentX = startX + (targetX - startX) * progress;
            currentWidth = startWidth + (targetWidth - startWidth) * progress;
            repaint();
        });
    }

    public void setActiveItem(Component c) {
        this.activeComponent = c;
        updateTarget();
        
        // Start animation from current state to new target
        startX = currentX;
        startWidth = currentWidth;
        
        // If first run (initialization), jump instantly
        if (currentWidth == 0f && currentX == 0f) {
            currentX = targetX;
            currentWidth = targetWidth;
            repaint();
        } else {
            animator.animateTo(1f);
        }
    }
    
    // Call this when layout changes (e.g. window resize) to snap the line
    public void revalidateLine() {
        if (activeComponent != null) {
            updateTarget();
            currentX = targetX;
            currentWidth = targetWidth;
            repaint();
        }
    }

    private void updateTarget() {
        if (activeComponent != null) {
            targetX = activeComponent.getX();
            // We want the line to be slightly smaller than the full item width? 
            // Or exact match? Design shows full width of the text/icon area usually.
            // Let's assume full width of the component for now.
            targetWidth = activeComponent.getWidth();
            
            // Adjust for centered line if needed (e.g. 40px width)
            // Design shows small line under text.
            // Let's hardcode a width or base it on content? 
            // In original code: line was 40px wide inside the item.
            // Let's try to center a 40px line under the item.
            
            float lineWidth = 40f;
            targetX = activeComponent.getX() + (activeComponent.getWidth() - lineWidth) / 2f;
            targetWidth = lineWidth;
        }
    }

    @Override
    protected void paintChildren(Graphics g) {
        // Paint children (menu items) first
        super.paintChildren(g);
        
        // Paint the sliding line on top
        if (currentWidth > 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(ColorScheme.PRIMARY_RED);
            
            // Draw line at the bottom
            int h = getHeight();
            g2.fillRoundRect((int)currentX, h - 3, (int)currentWidth, 3, 3, 3);
        }
    }
}
