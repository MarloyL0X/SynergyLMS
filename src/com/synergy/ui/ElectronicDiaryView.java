package com.synergy.ui;

import com.synergy.auth.UserSession;
import com.synergy.utils.ColorScheme;
import com.synergy.utils.FontManager;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

// TODO: This is a copy of DocumentsView. It needs to be adapted for the new school model.
public class ElectronicDiaryView extends JPanel {

    public ElectronicDiaryView() {
        setLayout(new BorderLayout(0, 30));
        setOpaque(false);
        setBorder(new EmptyBorder(30, 40, 30, 40));
        initializeUI();
    }

    private void initializeUI() {
        try {
            add(createStudentCard(), BorderLayout.NORTH);
            add(createGradebookSection(), BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            add(new JLabel("Error loading documents: " + e.getMessage()), BorderLayout.CENTER);
        }
    }

    private JPanel createStudentCard() {
        RoundedPanel card = new RoundedPanel(20, true);
        card.setBackground(ColorScheme.CARD_BACKGROUND);
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(0, 220));
        card.setBorder(new EmptyBorder(25, 130, 25, 25));

        card.add(createLeftPanel(), BorderLayout.WEST);
        card.add(createStudentInfoPanel(), BorderLayout.CENTER);
        return card;
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(100, 200));
        
        leftPanel.add(createAvatarPanel());
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(createIdLabel());
        
        return leftPanel;
    }

    private JPanel createAvatarPanel() {
        JPanel avatar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillOval(0, 0, 100, 100);
                drawInitials(g2);
            }
        };
        avatar.setPreferredSize(new Dimension(100, 100));
        avatar.setMinimumSize(new Dimension(100, 100));
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        avatar.setOpaque(false);
        return avatar;
    }

    private void drawInitials(Graphics2D g2) {
        g2.setColor(ColorScheme.PRIMARY_BLUE);
        g2.setFont(FontManager.getBoldFont(40));
        
        String initials = getStudentInitials();
        
        FontMetrics fm = g2.getFontMetrics();
        int width = fm.stringWidth(initials);
        int height = fm.getAscent();
        g2.drawString(initials, (100 - width) / 2, (100 + height) / 2 - 8);
    }

    private String getStudentInitials() {
        try {
            return UserSession.getInstance().getInitials();
        } catch (Exception e) {
            e.printStackTrace();
            return "??";
        }
    }

    private JLabel createIdLabel() {
        JLabel idLabel = new JLabel("ID: 24098231");
        idLabel.setFont(FontManager.getBoldFont(14));
        idLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return idLabel;
    }

    private JPanel createStudentInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(0, 40, 0, 0));
        
        UserSession user = UserSession.getInstance();
        
        infoPanel.add(createCardField(Localization.get("docs.student"), user.getFullName()));
        infoPanel.add(createCardField(Localization.get("docs.class"), user.getGroup()));
        infoPanel.add(createCardField(Localization.get("docs.status"), Localization.get("docs.status_val")));
        
        return infoPanel;
    }

    private JPanel createCardField(String labelText, String valueText) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setOpaque(false);
        
        JLabel label = new JLabel(labelText);
        label.setFont(FontManager.getBoldFont(10));
        label.setForeground(ColorScheme.SECONDARY_TEXT);
        
        JLabel value = new JLabel(valueText != null ? valueText : "---");
        value.setFont(FontManager.getBoldFont(18));
        value.setForeground(ColorScheme.PRIMARY_TEXT);
        
        fieldPanel.add(label, BorderLayout.NORTH);
        fieldPanel.add(value, BorderLayout.CENTER);
        return fieldPanel;
    }

    private JPanel createGradebookSection() {
        RoundedPanel panel = new RoundedPanel(16, true);
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(createGradebookTitle(), BorderLayout.NORTH);
        panel.add(createGradeTableScrollPane(), BorderLayout.CENTER);

        return panel;
    }

    private JLabel createGradebookTitle() {
        JLabel title = new JLabel(Localization.get("docs.title"));
        title.setFont(FontManager.getBoldFont(18));
        title.setForeground(ColorScheme.PRIMARY_TEXT);
        title.setBorder(new EmptyBorder(0, 0, 15, 0));
        return title;
    }

    private JScrollPane createGradeTableScrollPane() {
        JTable table = createGradeTable();
        configureTableHeader(table);
        configureTableRenderers(table);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        return scroll;
    }

    private JTable createGradeTable() {
        String[] columns = {
            Localization.get("docs.subject"), Localization.get("docs.quarter"), 
            Localization.get("docs.work_type"), Localization.get("docs.grade"), Localization.get("docs.date")
        };
        Object[][] data = getMockGradeData();

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        configureTableProperties(table);
        return table;
    }

    private Object[][] getMockGradeData() {
        return new Object[][] {
            {"Алгебра", "1", "Контрольная работа", "5", "2024-10-15"},
            {"Геометрия", "1", "Самостоятельная работа", "4", "2024-10-18"},
            {"Русский язык", "1", "Диктант", "5-", "2024-09-25"},
            {"Литература", "1", "Сочинение", "4+", "2024-10-20"},
            {"Физика", "1", "Лабораторная работа", "5", "2024-09-28"},
            {"Химия", "2", "Контрольная работа", "4", "2025-01-10"},
            {"Биология", "2", "Тест", "5", "2025-01-14"},
            {"История", "2", "Устный ответ", "5", "2025-02-20"},
        };
    }
    
    private void configureTableProperties(JTable table) {
        table.setRowHeight(40);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(230, 230, 230));
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFont(FontManager.getRegularFont(13));
    }

    private void configureTableHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(Color.WHITE);
                label.setForeground(ColorScheme.PRIMARY_TEXT);
                label.setFont(FontManager.getBoldFont(12));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setPreferredSize(new Dimension(0, 40));
                return label;
            }
        });
    }

    private void configureTableRenderers(JTable table) {
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
