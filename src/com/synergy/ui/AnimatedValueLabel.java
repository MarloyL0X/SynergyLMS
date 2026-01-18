package com.synergy.ui;

import com.synergy.utils.Animator;
import com.synergy.utils.ColorScheme;
import java.awt.Font;
import javax.swing.JLabel;

public class AnimatedValueLabel extends JLabel {
    private final String targetText;
    private double targetValue = 0;
    private boolean isInteger = true;
    private final Animator animator;

    public AnimatedValueLabel(String text, Font font) {
        this.targetText = text;
        setFont(font);
        setForeground(ColorScheme.PRIMARY_RED);
        
        // Try parse
        try {
            String cleanText = text.replaceAll("[^0-9.]", "");
            if (cleanText.isEmpty()) throw new NumberFormatException();
            
            if (cleanText.contains(".")) {
                targetValue = Double.parseDouble(cleanText);
                isInteger = false;
            } else {
                targetValue = Integer.parseInt(cleanText);
                isInteger = true;
            }
        } catch (NumberFormatException e) {
            setText(text);
            animator = null;
            return;
        }
        
        setText(isInteger ? "0" : "0.0");
        
        animator = new Animator(1500, val -> {
            double current = targetValue * val;
            if (isInteger) {
                setText(String.valueOf((int) current));
            } else {
                setText(String.format("%.1f", current).replace(',', '.'));
            }
        });
        
        javax.swing.Timer delay = new javax.swing.Timer(100, e -> animator.animateTo(1f));
        delay.setRepeats(false);
        delay.start();
    }
}
