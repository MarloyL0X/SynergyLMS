package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import com.synergy.utils.FontManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class SegmentedControl extends JPanel {
    private int selectedIndex = 0;
    private final String[] options;
    private Consumer<Integer> onSegmentSelected;

    public SegmentedControl(String[] options) {
        this.options = options;
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setOpaque(false);
        createSegments();
    }

    private void createSegments() {
        for (int i = 0; i < options.length; i++) {
            final int index = i;
            JLabel segment = new JLabel(options[i]);
            segment.setFont(FontManager.getBoldFont(13));
            segment.setHorizontalAlignment(SwingConstants.CENTER);
            segment.setOpaque(true);
            segment.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
            segment.setCursor(new Cursor(Cursor.HAND_CURSOR));

            updateSegmentStyle(segment, index == selectedIndex);

            segment.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setSelectedIndex(index);
                }
            });
            add(segment);
        }
    }

    private void setSelectedIndex(int index) {
        if (this.selectedIndex != index) {
            this.selectedIndex = index;
            if (onSegmentSelected != null) {
                onSegmentSelected.accept(index);
            }
            // Redraw all segments
            removeAll();
            createSegments();
            revalidate();
            repaint();
        }
    }

    private void updateSegmentStyle(JLabel label, boolean isSelected) {
        if (isSelected) {
            label.setBackground(ColorScheme.PRIMARY_BLUE);
            label.setForeground(Color.WHITE);
        } else {
            label.setBackground(ColorScheme.APP_BACKGROUND.brighter());
            label.setForeground(ColorScheme.SECONDARY_TEXT);
        }
    }

    public void setOnSegmentSelected(Consumer<Integer> listener) {
        this.onSegmentSelected = listener;
    }
}
