package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.FontManager;
import javax.swing.*;
import java.awt.*;

public class SubjectCard extends RoundedPanel {

    public SubjectCard(String subjectName, String teacherName, String grade) {
        super(12, true);
        setBackground(ColorScheme.CARD_BACKGROUND);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        GridBagConstraints gbc = new GridBagConstraints();

        // Subject Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel subjectLabel = new JLabel(subjectName);
        subjectLabel.setFont(FontManager.getBoldFont(16));
        subjectLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        add(subjectLabel, gbc);

        // Teacher Name
        gbc.gridy = 1;
        JLabel teacherLabel = new JLabel(teacherName);
        teacherLabel.setFont(FontManager.getRegularFont(13));
        teacherLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        add(teacherLabel, gbc);

        // Grade
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 20, 0, 20);
        JLabel gradeLabel = new JLabel(grade);
        gradeLabel.setFont(FontManager.getBoldFont(28));
        gradeLabel.setForeground(ColorScheme.PRIMARY_BLUE);
        add(gradeLabel, gbc);

        // View Button
        gbc.gridx = 2;
        gbc.gridheight = 2;
        PillButton viewButton = new PillButton("Просмотр", false);
        add(viewButton, gbc);
    }
}
