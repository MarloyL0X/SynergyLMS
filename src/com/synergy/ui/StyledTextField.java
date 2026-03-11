package com.synergy.ui;

import com.synergy.utils.Animator;
import com.synergy.utils.ColorScheme;
import com.synergy.utils.FontManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class StyledTextField extends JTextField {
    private float focusProgress = 0f;

    public StyledTextField(String placeholder) {
        setOpaque(false);
        setBackground(new Color(242, 242, 247));
        setForeground(ColorScheme.PRIMARY_TEXT);
        setCaretColor(ColorScheme.PRIMARY_TEXT);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        setFont(FontManager.getRegularFont(16));
        setPreferredSize(new Dimension(300, 48));

        Animator focusAnimator = new Animator(250, (p) -> {
            focusProgress = p;
            repaint();
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                focusAnimator.animateTo(1f);
            }

            @Override
            public void focusLost(FocusEvent e) {
                focusAnimator.animateTo(0f);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

        Color borderColor = com.synergy.utils.AnimationUtils.interpolateColor(
            getBackground().brighter(),
            ColorScheme.PRIMARY_BLUE,
            focusProgress
        );
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);

        g2.dispose();
        super.paintComponent(g);
    }
}
