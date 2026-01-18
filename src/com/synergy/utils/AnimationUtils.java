package com.synergy.utils;

import java.awt.Color;

public class AnimationUtils {
    
    public static Color interpolateColor(Color c1, Color c2, float fraction) {
        fraction = Math.min(1f, Math.max(0f, fraction));
        
        int r = (int) (c1.getRed() + (c2.getRed() - c1.getRed()) * fraction);
        int g = (int) (c1.getGreen() + (c2.getGreen() - c1.getGreen()) * fraction);
        int b = (int) (c1.getBlue() + (c2.getBlue() - c1.getBlue()) * fraction);
        int a = (int) (c1.getAlpha() + (c2.getAlpha() - c1.getAlpha()) * fraction);
        
        return new Color(r, g, b, a);
    }
}
