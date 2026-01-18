package com.synergy.ui;

import com.synergy.utils.ColorScheme;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class SynergyIcons {
    
    public static Icon getLogoIcon(int size) {
        // Try to load from resources
        URL imgUrl = SynergyIcons.class.getResource("/resources/logo.png");
        if (imgUrl == null) {
            // Try fallback path if resources folder is in src root but not in classpath yet
            imgUrl = SynergyIcons.class.getClassLoader().getResource("resources/logo.png");
        }
        
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        }

        // Fallback to vector drawing if image not found
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(ColorScheme.TABLE_HEADER_TEXT);
                int w = width;
                int h = height;
                g2.fillArc(w/4, h/4, w/2, h/2, 90, 180);
                g2.fillOval(w/4, h/4, w/4, h/4);
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
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                int gap = 2;
                int box = (width - gap) / 2;
                g2.fillRect(0, 0, box, box);
                g2.fillRect(box + gap, 0, box, box);
                g2.fillRect(0, box + gap, box, box);
                g2.fillRect(box + gap, box + gap, box, box);
            }
        };
    }

    public static Icon getCalendarIcon(int size, boolean active) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                g2.drawRoundRect(1, 3, width-2, height-4, 2, 2);
                g2.fillRect(1, 3, width-2, 3);
                g2.fillRect(4, 1, 2, 4);
                g2.fillRect(width-6, 1, 2, 4);
            }
        };
    }
    
    public static Icon getBillIcon(int size, boolean active) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                g2.fillRect(3, 1, width-6, height-2);
                g2.setColor(ColorScheme.TOP_BAR_BG);
                g2.drawLine(5, 4, width-5, 4);
                g2.drawLine(5, 7, width-5, 7);
            }
        };
    }

    public static Icon getDocumentIcon(int size, boolean active) {
        return new VectorIcon(size, size) {
            @Override
            protected void paintIcon(Graphics2D g2, int width, int height) {
                g2.setColor(active ? ColorScheme.ICON_ACTIVE : ColorScheme.ICON_INACTIVE);
                int[] x = {3, 10, 13, 13, 3};
                int[] y = {1, 1, 4, 15, 15};
                g2.fillPolygon(x, y, 5);
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
