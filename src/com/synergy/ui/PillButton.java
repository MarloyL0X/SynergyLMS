package com.synergy.ui;

import com.synergy.utils.AnimationUtils;
import com.synergy.utils.Animator;
import com.synergy.utils.FontManager;
import com.synergy.utils.ColorScheme;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class PillButton extends JButton {
    private final Animator animator;
    private float hoverProgress = 0f;
    private final boolean isPrimary;

    public PillButton(String text, boolean primary) {
        super(text);
        this.isPrimary = primary;
        
        // Initialize Animator (200ms duration)
        animator = new Animator(200, val -> {
            hoverProgress = val;
            repaint();
        });
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(FontManager.getBoldFont(12));
        setForeground(primary ? Color.WHITE : ColorScheme.PRIMARY_TEXT);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setupInteractions();
    }
    
    private void setupInteractions() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { animator.animateTo(1f); }
            public void mouseExited(MouseEvent e) { animator.animateTo(0f); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color baseColor = isPrimary ? ColorScheme.PRIMARY_BLUE : new Color(229, 229, 234);
        Color hoverColor = isPrimary ? ColorScheme.PRIMARY_BLUE.brighter() : new Color(209, 209, 214);
        
        // Use utility to mix colors
        g2.setColor(AnimationUtils.interpolateColor(baseColor, hoverColor, hoverProgress));
        
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
        super.paintComponent(g);
    }
}
