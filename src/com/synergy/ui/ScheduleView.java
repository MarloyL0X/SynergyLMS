package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ScheduleView extends JPanel {

    public ScheduleView() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        add(createHeader(), BorderLayout.NORTH);
        add(createScheduleList(), BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        JLabel titleLabel = new JLabel(Localization.get("schedule.title"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(createFilterPanel(), BorderLayout.EAST);
        return headerPanel;
    }

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        filterPanel.setOpaque(false);
        filterPanel.add(new JLabel(Localization.get("schedule.sort")));
        String[] sortOptions = {
            Localization.get("schedule.today"), 
            Localization.get("schedule.tomorrow"), 
            Localization.get("schedule.week")
        };
        JComboBox<String> sortCombo = new JComboBox<>(sortOptions);
        filterPanel.add(sortCombo);
        return filterPanel;
    }

    private JScrollPane createScheduleList() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);
        
        addDateGroup(listPanel, Localization.get("schedule.today") + ", 24 Окт");
        addScheduleItem(listPanel, "08:30 - 10:00", "Математический анализ I", ScheduleType.LECTURE, "Ауд. 304", "Проф. Б. Иванов", "Пределы и непрерывность", "Решить задачи 1-10 на стр. 42.");
        addScheduleItem(listPanel, "10:15 - 11:45", "Введение в информатику", ScheduleType.PRACTICE, "Лаб. 101", "Д-р А. Смирнов", "Основы Java", "Установить JDK и IntelliJ IDEA.");
        
        listPanel.add(Box.createVerticalStrut(20));
        
        addDateGroup(listPanel, Localization.get("schedule.tomorrow") + ", 25 Окт");
        addScheduleItem(listPanel, "12:00 - 13:30", "Современная история", ScheduleType.SEMINAR, "Ауд. 202", "М. К. Петрова", "Вторая мировая война", "Прочитать главу 5.");
        addScheduleItem(listPanel, "14:00 - 16:00", "Физика", ScheduleType.EXAM, "Актовый зал", "Проф. Эйнштейн", "Промежуточный экзамен", "Подготовить калькулятор и ручку.");

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private void addDateGroup(JPanel container, String dateText) {
        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.setOpaque(false);
        datePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel dateLabel = new JLabel(dateText);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        
        datePanel.add(dateLabel);
        datePanel.setMaximumSize(new Dimension(2000, 40));
        container.add(datePanel);
    }

    private void addScheduleItem(JPanel container, String time, String subject, ScheduleType type, String room, String teacher, String topic, String homework) {
        ScheduleItemPanel itemPanel = new ScheduleItemPanel(time, subject, type, room, teacher, topic, homework, () -> {
            java.awt.Window window = SwingUtilities.getWindowAncestor(container);
            if (window instanceof java.awt.Frame) {
                new LessonDetailsDialog((java.awt.Frame) window, subject, type.getDisplayName(), teacher, topic, homework).setVisible(true);
            }
        });
        itemPanel.setMaximumSize(new Dimension(2000, 60));
        container.add(itemPanel);
        container.add(Box.createVerticalStrut(10));
    }
}
