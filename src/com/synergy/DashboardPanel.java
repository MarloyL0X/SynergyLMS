package com.synergy;

import com.synergy.auth.UserSession;
import com.synergy.ui.ElectronicDiaryView;
import com.synergy.ui.PaymentsView;
import com.synergy.ui.SchoolScheduleView;
import com.synergy.ui.SemesterProgressPanel;
import com.synergy.ui.Sidebar;
import com.synergy.ui.SubjectCard;
import com.synergy.ui.SynergyIcons;
import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DashboardPanel extends JPanel {
    private Sidebar sidebar;
    private SemesterProgressPanel semesterPanel;
    private final JPanel contentPanel;
    private final CardLayout cardLayout;

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(ColorScheme.APP_BACKGROUND);

        sidebar = new Sidebar();
        add(sidebar, BorderLayout.WEST);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setOpaque(false);
        add(contentPanel, BorderLayout.CENTER);

        sidebar.addNavItem("COURSES", "menu.courses", SynergyIcons.getGridIcon(20, false));
        sidebar.addNavItem("SCHEDULE", "menu.schedule", SynergyIcons.getCalendarIcon(20, false));
        sidebar.addNavItem("PAYMENTS", "menu.payments", SynergyIcons.getBillIcon(20, false));
        sidebar.addNavItem("DIARY", "menu.profile", SynergyIcons.getDocumentIcon(20, false));
        sidebar.buildNav();

        sidebar.setNavigationListener((viewName) -> {
            cardLayout.show(contentPanel, viewName);
        });

        setupViews();
    }

    private void setupViews() {
        contentPanel.add(createCoursesView(), "COURSES");
        contentPanel.add(new SchoolScheduleView(), "SCHEDULE");
        contentPanel.add(new PaymentsView(), "PAYMENTS");
        contentPanel.add(new ElectronicDiaryView(), "DIARY");
        cardLayout.show(contentPanel, "COURSES");
    }

    private JPanel createCoursesView() {
        JPanel coursesPanel = new JPanel(new BorderLayout(0, 20));
        coursesPanel.setOpaque(false);
        coursesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 40, 30, 40));

        semesterPanel = new SemesterProgressPanel();
        coursesPanel.add(semesterPanel, BorderLayout.NORTH);
        coursesPanel.add(createTableSection(), BorderLayout.CENTER);

        return coursesPanel;
    }

    public void updateLanguage() {
        sidebar.refresh();
        contentPanel.removeAll();
        setupViews();
        semesterPanel.refresh();
        revalidate();
        repaint();
    }

    private JPanel createTableSection() {
        JPanel listContainer = new JPanel(new BorderLayout());
        listContainer.setOpaque(false);
        
        JLabel title = new JLabel("Мои предметы");
        title.setFont(com.synergy.utils.FontManager.getBoldFont(20));
        title.setForeground(ColorScheme.PRIMARY_TEXT);
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 15, 0));
        listContainer.add(title, BorderLayout.NORTH);
        
        JPanel subjectsList = createSubjectsList();
        JScrollPane scrollPane = new JScrollPane(subjectsList);
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(ColorScheme.APP_BACKGROUND);
        
        listContainer.add(scrollPane, BorderLayout.CENTER);
        return listContainer;
    }
    
    private JPanel createSubjectsList() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);
        listPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 25, 0, 25));
        
        listPanel.add(new SubjectCard("Математический анализ I", "Проф. Б. Иванов", "A"));
        listPanel.add(Box.createVerticalStrut(10));
        listPanel.add(new SubjectCard("Основы программирования", "Проф. А. Петрова", "B+"));
        listPanel.add(Box.createVerticalStrut(10));
        listPanel.add(new SubjectCard("История России", "Проф. В. Сидоров", "C"));
        listPanel.add(Box.createVerticalStrut(10));
        listPanel.add(new SubjectCard("Английский язык", "Проф. Е. Козлова", "A-"));
        listPanel.add(Box.createVerticalStrut(10));
        listPanel.add(new SubjectCard("Физика: Механика", "Проф. Г. Смирнов", "B"));
        
        return listPanel;
    }
}
