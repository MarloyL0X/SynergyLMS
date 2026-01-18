package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StyledTextField extends JTextField {
    public StyledTextField(String placeholder) {
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        setPreferredSize(new Dimension(300, 40));
    }
}
