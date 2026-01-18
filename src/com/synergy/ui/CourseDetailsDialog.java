package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.Localization;
import com.synergy.utils.Animator;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CourseDetailsDialog extends JDialog {
    private final Animator animator;

    public CourseDetailsDialog(Frame owner, String courseName, String instructor, String status) {
        super(owner, true);
        initializeDialog();
        
        JPanel contentPanel = new RoundedPanel(20, true);
        configureContentPanel(contentPanel);
        
        contentPanel.add(createHeader(courseName, instructor), BorderLayout.NORTH);
        contentPanel.add(createBody(status), BorderLayout.CENTER);
        contentPanel.add(createFooter(), BorderLayout.SOUTH);
        
        add(contentPanel);
        setSize(500, 600);
        setLocationRelativeTo(owner);
        
        animator = createAnimator();
        setupWindowListener();
    }

    private void initializeDialog() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setOpacity(0f);
    }

    private void configureContentPanel(JPanel panel) {
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 25, 20, 25));
    }
    
    private Animator createAnimator() {
        return new Animator(300, value -> {
            try {
                setOpacity(value);
            } catch (UnsupportedOperationException e) {
            }
        });
    }

    private void setupWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                animator.animateTo(1f);
            }
        });
    }
    
    private JPanel createHeader(String title, String subtitle) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        headerPanel.add(createTextPanel(title, subtitle), BorderLayout.CENTER);
        headerPanel.add(createBadgePanel(), BorderLayout.EAST);
        
        return headerPanel;
    }

    private JPanel createTextPanel(String title, String subtitle) {
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(ColorScheme.SECONDARY_TEXT);
        
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        return textPanel;
    }

    private JPanel createBadgePanel() {
        // Localized badge text
        TypeBadge badge = new TypeBadge(Localization.get("badge.course"), ColorScheme.PRIMARY_RED);
        JPanel badgePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        badgePanel.setOpaque(false);
        badgePanel.add(badge);
        return badgePanel;
    }
    
    private JPanel createBody(String status) {
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.setOpaque(false);
        
        int totalTopics = 12;
        int completedTopics = calculateCompletedTopics(status);
        
        bodyPanel.add(createProgressSection(completedTopics, totalTopics));
        bodyPanel.add(Box.createVerticalStrut(20));
        bodyPanel.add(createTopicsTitle());
        bodyPanel.add(Box.createVerticalStrut(10));
        bodyPanel.add(createTopicsList(totalTopics, completedTopics));
        
        return bodyPanel;
    }

    private int calculateCompletedTopics(String status) {
        // Check both localized statuses just in case
        if ("Active".equals(status) || "Активен".equals(status)) return 12;
        if ("Pending".equals(status) || "Ожидание".equals(status)) return 0;
        return 5;
    }

    private JLabel createTopicsTitle() {
        JLabel listTitle = new JLabel(Localization.get("details.topics"));
        listTitle.setFont(new Font("Arial", Font.BOLD, 16));
        listTitle.setForeground(ColorScheme.PRIMARY_TEXT);
        listTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        return listTitle;
    }

    private JScrollPane createTopicsList(int totalTopics, int completedTopics) {
        JPanel listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setOpaque(false);
        
        String topicPrefix = Localization.get("details.topic_prefix");
        String topicTitle = Localization.get("details.topic_title");
        
        for (int i = 1; i <= totalTopics; i++) {
            boolean done = i <= completedTopics;
            listContainer.add(createTopicRow(i, topicPrefix + " " + i + ": " + topicTitle, done));
            listContainer.add(Box.createVerticalStrut(8));
        }
        
        JScrollPane scrollPane = new JScrollPane(listContainer);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        return scrollPane;
    }
    
    private JPanel createProgressSection(int completed, int total) {
        JPanel progressPanel = new JPanel(new BorderLayout(0, 10));
        progressPanel.setOpaque(false);
        progressPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        progressPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        progressPanel.add(createProgressLabels(completed, total), BorderLayout.NORTH);
        progressPanel.add(createCustomProgressBar(completed, total), BorderLayout.CENTER);
        
        return progressPanel;
    }

    private JPanel createProgressLabels(int completed, int total) {
        JPanel labelsPanel = new JPanel(new BorderLayout());
        labelsPanel.setOpaque(false);
        
        JLabel progressLabel = new JLabel(Localization.get("details.progress"));
        progressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        progressLabel.setForeground(ColorScheme.PRIMARY_TEXT);
        
        JLabel countLabel = new JLabel(completed + "/" + total + " " + Localization.get("details.completed"));
        countLabel.setFont(new Font("Arial", Font.BOLD, 14));
        countLabel.setForeground(completed == total ? new Color(46, 204, 113) : ColorScheme.PRIMARY_RED);
        
        labelsPanel.add(progressLabel, BorderLayout.WEST);
        labelsPanel.add(countLabel, BorderLayout.EAST);
        return labelsPanel;
    }

    private JPanel createCustomProgressBar(int completed, int total) {
        JPanel progressBar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                graphics2D.setColor(ColorScheme.PROGRESS_TRACK);
                graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                int width = (int) ((double)completed / total * getWidth());
                if (width > 0) {
                    graphics2D.setColor(completed == total ? new Color(46, 204, 113) : ColorScheme.PRIMARY_RED);
                    graphics2D.fillRoundRect(0, 0, width, getHeight(), 10, 10);
                }
            }
        };
        progressBar.setPreferredSize(new Dimension(0, 10));
        return progressBar;
    }
    
    private JPanel createTopicRow(int number, String title, boolean done) {
        JPanel rowPanel = new JPanel(new BorderLayout(15, 0));
        rowPanel.setOpaque(false);
        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        rowPanel.add(createTopicIcon(done), BorderLayout.WEST);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", done ? Font.BOLD : Font.PLAIN, 13));
        titleLabel.setForeground(done ? ColorScheme.PRIMARY_TEXT : ColorScheme.SECONDARY_TEXT);
        rowPanel.add(titleLabel, BorderLayout.CENTER);
        
        return rowPanel;
    }

    private JPanel createTopicIcon(boolean done) {
        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (done) {
                    drawCheckmark(graphics2D);
                } else {
                    drawEmptyCircle(graphics2D);
                }
            }

            private void drawCheckmark(Graphics2D g2) {
                g2.setColor(new Color(46, 204, 113));
                g2.fillOval(0, 0, 24, 24);
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(6, 12, 10, 16);
                g2.drawLine(10, 16, 18, 8);
            }

            private void drawEmptyCircle(Graphics2D g2) {
                g2.setColor(new Color(200, 200, 200));
                g2.drawOval(1, 1, 22, 22);
            }
        };
        iconPanel.setPreferredSize(new Dimension(25, 25));
        iconPanel.setOpaque(false);
        return iconPanel;
    }
    
    private JPanel createFooter() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        PillButton closeButton = new PillButton(Localization.get("details.close"), false);
        closeButton.setBackground(ColorScheme.PRIMARY_RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setPreferredSize(new Dimension(150, 40));
        closeButton.addActionListener(e -> dispose());
        
        footerPanel.add(closeButton);
        return footerPanel;
    }
}
