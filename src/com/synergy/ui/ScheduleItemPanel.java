package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.FontManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScheduleItemPanel extends RoundedPanel {

    public ScheduleItemPanel(String time, String subject, ScheduleType type, String room, String teacher, String topic, String homework, Runnable onClick) {
        super(12, true);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 0));
        setBorder(BorderFactory.createEmptyBorder());
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Type Color Bar
        JPanel colorBar = new JPanel();
        colorBar.setBackground(type.getColor());
        colorBar.setPreferredSize(new Dimension(8, 0));
        add(colorBar, BorderLayout.WEST);

        // Main Content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        // Time
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(FontManager.getBoldFont(14));
        timeLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        contentPanel.add(timeLabel, gbc);

        // Subject
        gbc.gridy = 1;
        gbc.insets = new Insets(2, 0, 0, 0);
        JLabel subjectLabel = new JLabel(subject);
        subjectLabel.setFont(FontManager.getBoldFont(18));
        subjectLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        contentPanel.add(subjectLabel, gbc);

        // Teacher & Room
        gbc.gridy = 2;
        JLabel teacherLabel = new JLabel(teacher + "  •  " + room);
        teacherLabel.setFont(FontManager.getRegularFont(13));
        teacherLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        contentPanel.add(teacherLabel, gbc);

        // Details Icon
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 20, 0, 0);
        JLabel detailsIcon = new JLabel(" > "); // Placeholder for a real icon
        detailsIcon.setFont(FontManager.getBoldFont(24));
        detailsIcon.setForeground(ColorScheme.SECONDARY_TEXT);
        contentPanel.add(detailsIcon, gbc);
        
        // Hover Effect
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(new Color(248, 248, 248)); repaint(); }
            public void mouseExited(MouseEvent e) { setBackground(Color.WHITE); repaint(); }
            public void mouseClicked(MouseEvent e) { onClick.run(); }
        });
    }
}
