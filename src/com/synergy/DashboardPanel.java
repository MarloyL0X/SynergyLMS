package com.synergy;

import com.synergy.ui.CoursesTable;
import com.synergy.ui.DocumentsView;
import com.synergy.ui.HeaderPanel;
import com.synergy.ui.MetricCard;
import com.synergy.ui.PaymentsView;
import com.synergy.ui.RoundedPanel;
import com.synergy.ui.ScheduleView;
import com.synergy.ui.SemesterProgressPanel;
import com.synergy.ui.SynergyIcons;
import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import com.synergy.ui.TransitionContainer;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DashboardPanel extends JPanel {
    private TransitionContainer contentArea;
    private HeaderPanel header;
    private SemesterProgressPanel semesterPanel;
    private JPanel statsPanel;

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.APP_BACKGROUND);
        
        header = new HeaderPanel();
        add(header, BorderLayout.NORTH);
        
        contentArea = new TransitionContainer();
        
        contentArea.addView(createCoursesView(), "COURSES");
        contentArea.addView(new ScheduleView(), "SCHEDULE");
        contentArea.addView(new PaymentsView(), "PAYMENTS");
        contentArea.addView(new DocumentsView(), "DOCUMENTS");
        
        add(contentArea, BorderLayout.CENTER);
        
        contentArea.showView("COURSES");
        
        header.setNavigationListener((viewName) -> {
            if ("Courses".equals(viewName) || "Курсы".equals(viewName)) contentArea.showView("COURSES");
            else if ("Schedule".equals(viewName) || "Расписание".equals(viewName)) contentArea.showView("SCHEDULE");
            else if ("Payments".equals(viewName) || "Оплата".equals(viewName)) contentArea.showView("PAYMENTS");
            else if ("Profile".equals(viewName) || "Зачетка".equals(viewName)) {
                contentArea.showView("DOCUMENTS");
            }
        });
        
        Localization.setLanguageListener(v -> updateLanguage());
    }
    
    private void updateLanguage() {
        header.refresh();
        if (semesterPanel != null) semesterPanel.refresh();
        if (statsPanel != null) {
            statsPanel.removeAll();
            statsPanel.add(new MetricCard(Localization.get("stats.points"), "120", SynergyIcons.getStarIcon(16), 85));
            statsPanel.add(new MetricCard(Localization.get("stats.debts"), "2", SynergyIcons.getWarningIcon(16), 10));
            statsPanel.add(new MetricCard(Localization.get("stats.progress"), "75%", SynergyIcons.getTrendIcon(16), 75));
            statsPanel.revalidate();
            statsPanel.repaint();
        }
        
        // Re-create views to apply translations
        contentArea.removeAll();
        contentArea.addView(createCoursesView(), "COURSES");
        contentArea.addView(new ScheduleView(), "SCHEDULE");
        contentArea.addView(new PaymentsView(), "PAYMENTS");
        contentArea.addView(new DocumentsView(), "DOCUMENTS");
        contentArea.showView("COURSES"); // Reset to courses or track current view
    }

    private JPanel createCoursesView() {
        JPanel contentPanel = new JPanel(new BorderLayout(0, 25));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        
        JPanel topSection = new JPanel(new BorderLayout(0, 25));
        topSection.setOpaque(false);
        topSection.add(createStats(), BorderLayout.NORTH);
        
        semesterPanel = new SemesterProgressPanel();
        topSection.add(semesterPanel, BorderLayout.CENTER);
        
        contentPanel.add(topSection, BorderLayout.NORTH);
        contentPanel.add(createTableSection(), BorderLayout.CENTER);
        
        return contentPanel;
    }

    private JPanel createStats() {
        statsPanel = new JPanel(new GridLayout(1, 3, 25, 0));
        statsPanel.setOpaque(false);
        statsPanel.setPreferredSize(new Dimension(0, 140));
        
        statsPanel.add(new MetricCard(Localization.get("stats.points"), "120", SynergyIcons.getStarIcon(16), 85));
        statsPanel.add(new MetricCard(Localization.get("stats.debts"), "2", SynergyIcons.getWarningIcon(16), 10));
        statsPanel.add(new MetricCard(Localization.get("stats.progress"), "75%", SynergyIcons.getTrendIcon(16), 75));
        
        return statsPanel;
    }

    private JPanel createTableSection() {
        RoundedPanel tableContainer = new RoundedPanel(16, true);
        tableContainer.setBackground(ColorScheme.CARD_BACKGROUND);
        tableContainer.setLayout(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        CoursesTable coursesTable = new CoursesTable();
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(ColorScheme.CARD_BACKGROUND);
        
        tableContainer.add(scrollPane, BorderLayout.CENTER);
        return tableContainer;
    }
}
