package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScheduleItemPanel extends RoundedPanel {
    private final String time;
    private final String subject;
    private final ScheduleType type;
    private final String room;
    private final String teacher;
    private final String topic;
    private final String homework;
    private final Runnable onClick;

    public ScheduleItemPanel(String time, String subject, ScheduleType type, String room, String teacher, String topic, String homework, Runnable onClick) {
        super(10, false);
        this.time = time;
        this.subject = subject;
        this.type = type;
        this.room = room;
        this.teacher = teacher;
        this.topic = topic;
        this.homework = homework;
        this.onClick = onClick;
        
        initPanel();
        addComponents();
        setupInteractions();
    }

    private void initPanel() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(15, 0));
        setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void addComponents() {
        add(createTimeLabel(), BorderLayout.WEST);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createRoomLabel(), BorderLayout.EAST);
    }

    private JLabel createTimeLabel() {
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timeLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        timeLabel.setPreferredSize(new Dimension(100, 0));
        return timeLabel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));
        centerPanel.setOpaque(false);
        
        JLabel subjectLabel = new JLabel(subject);
        subjectLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subjectLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        
        centerPanel.add(subjectLabel);
        centerPanel.add(new TypeBadge(type.getDisplayName(), type.getColor()));
        
        return centerPanel;
    }

    private JLabel createRoomLabel() {
        String roomPrefix = Localization.get("schedule.room");
        JLabel roomLabel = new JLabel(roomPrefix + ": " + room);
        roomLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roomLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        return roomLabel;
    }

    private void setupInteractions() {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { setBackground(new Color(250, 250, 250)); repaint(); }
            public void mouseExited(MouseEvent e) { setBackground(Color.WHITE); repaint(); }
            public void mouseClicked(MouseEvent e) { onClick.run(); }
        });
    }
    
    public String getSubject() { return subject; }
    public ScheduleType getType() { return type; }
    public String getTeacher() { return teacher; }
    public String getTopic() { return topic; }
    public String getHomework() { return homework; }
}
