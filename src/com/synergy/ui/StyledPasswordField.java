package com.synergy.ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

public class StyledPasswordField extends JPasswordField {
    public StyledPasswordField() {
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        setPreferredSize(new Dimension(300, 40));
    }
}
