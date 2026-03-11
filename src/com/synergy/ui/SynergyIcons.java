package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class SynergyIcons {
    
    public static Icon getAlertIcon(int size) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(ColorScheme.PRIMARY_RED);
                g2.fillOval(x, y, size, size);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("SansSerif", Font.BOLD, size / 2));
                g2.drawString("!", x + size / 2 - size / 8, y + size / 2 + size / 4);
                g2.dispose();
            }
            @Override public int getIconWidth() { return size; }
            @Override public int getIconHeight() { return size; }
        };
    }

    public static Icon getLogoIcon(int size) {
        // Vector-based placeholder logo
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(ColorScheme.PRIMARY_BLUE);
                g2.fillOval(0, 0, width, height);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.BOLD, (int)(size * 0.7)));
                String text = "П";
                FontMetrics fm = g2.getFontMetrics();
                int x = (width - fm.stringWidth(text)) / 2;
                int y = (height - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(text, x, y);
            }
        };
    }

    public static Icon getMegaphoneIcon(int size, boolean active) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                int[] xPoints = {2, 10, 14, 14, 10, 2};
                int[] yPoints = {6, 2, 2, 14, 14, 10};
                g2.fillPolygon(xPoints, yPoints, 6);
                g2.fillRect(12, 6, 2, 4);
            }
        };
    }

    public static Icon getGridIcon(int size, boolean active) {
        // New icon: Book
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                g2.setStroke(new java.awt.BasicStroke(1.5f));
                g2.drawRect(3, 2, width - 6, height - 4); // Book cover
                g2.drawLine(width / 2, 2, width / 2, height - 2); // Spine
            }
        };
    }

    public static Icon getCalendarIcon(int size, boolean active) {
        // New icon: Modern Calendar
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                g2.setStroke(new java.awt.BasicStroke(1.5f));
                g2.drawRoundRect(2, 3, width - 4, height - 5, 4, 4); // Calendar body
                g2.drawLine(2, 7, width - 2, 7); // Line for header
                g2.fillRect(5, 1, 2, 4); // Left ring holder
                g2.fillRect(width - 7, 1, 2, 4); // Right ring holder
            }
        };
    }
    
    public static Icon getBillIcon(int size, boolean active) {
        // New icon: Wallet
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                g2.setStroke(new java.awt.BasicStroke(1.5f));
                g2.drawRoundRect(2, 4, width - 4, height - 7, 4, 4); // Wallet body
                g2.drawRoundRect(width - 6, 2, 3, 3, 2, 2); // Clasp
            }
        };
    }

    public static Icon getDocumentIcon(int size, boolean active) {
        // New icon: Open Diary/Book
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                g2.setStroke(new java.awt.BasicStroke(1.5f));
                g2.drawLine(width / 2, 3, width / 2, height - 3); // Spine
                g2.drawArc(2, 3, width / 2 - 2, height - 6, 90, 180);
                g2.drawArc(width / 2, 3, width / 2 - 2, height - 6, 90, -180);
            }
        };
    }

    public static Icon getStarIcon(int size) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(ColorScheme.SECONDARY_TEXT);
                int[] x = {8, 10, 16, 11, 13, 8, 3, 5, 0, 6};
                int[] y = {0, 6, 6, 10, 16, 12, 16, 10, 6, 6};
                g2.fillPolygon(x, y, 10);
            }
        };
    }

    public static Icon getWarningIcon(int size) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(ColorScheme.SECONDARY_TEXT);
                int[] x = {width/2, width, 0};
                int[] y = {0, height, height};
                g2.fillPolygon(x, y, 3);
                g2.setColor(ColorScheme.CARD_BACKGROUND);
                g2.fillRect(width/2 - 1, 4, 2, 6);
                g2.fillRect(width/2 - 1, 12, 2, 2);
            }
        };
    }

    public static Icon getTrendIcon(int size) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(ColorScheme.SECONDARY_TEXT);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.drawLine(1, 14, 6, 8);
                g2.drawLine(6, 8, 10, 11);
                g2.drawLine(10, 11, 15, 2);
                g2.drawLine(15, 2, 11, 2);
                g2.drawLine(15, 2, 15, 6);
            }
        };
    }

    public static Icon getBellIcon(int size) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(ColorScheme.TABLE_HEADER_TEXT);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.fillArc(2, 2, width-4, height-4, 0, 180);
                g2.fillRect(2, height/2, width-4, 4);
                g2.drawLine(width/2, height-2, width/2, height);
            }
        };
    }

    public static Icon getSettingsIcon(int size) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(ColorScheme.TABLE_HEADER_TEXT);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.drawOval(4, 4, width-8, height-8);
                g2.drawLine(width/2, 0, width/2, height);
                g2.drawLine(0, height/2, width, height/2);
            }
        };
    }
    
    private abstract static class VectorIcon implements Icon {
        private final int w, h;
        VectorIcon(int w, int h) { this.w = w; this.h = h; }
        public int getIconWidth() { return w; }
        public int getIconHeight() { return h; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.translate(x, y);
            paintIcon(g2, w, h);
            g2.dispose();
        }
        protected abstract void paintIcon(Graphics2D g2, int w, int h);
    }
}
