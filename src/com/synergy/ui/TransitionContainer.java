package com.synergy.ui;

import com.synergy.utils.Animator;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class TransitionContainer extends JPanel {
    private final Map<String, Component> views = new HashMap<>();
    private Component currentView;
    private Component incomingView;
    
    private BufferedImage currentSnapshot;
    private BufferedImage incomingSnapshot;
    private float transitionProgress = 0f;
    private boolean isAnimating = false;
    
    private final Animator animator;

    public TransitionContainer() {
        setLayout(null);
        setOpaque(false);
        
        animator = new Animator(400, value -> {
            transitionProgress = value;
            repaint();
            if (value >= 1f) {
                finishTransition();
            }
        });
    }

    public void addView(Component component, String name) {
        views.put(name, component);
        component.setVisible(false);
        add(component);
    }
    
    public void showView(String name) {
        Component target = views.get(name);
        if (target == null || target == currentView) return;
        
        if (currentView == null) {
            showInitialView(target);
            return;
        }
        
        if (isAnimating) {
            finishTransition();
        }
        
        startTransition(target);
    }
    
    private void showInitialView(Component target) {
        currentView = target;
        currentView.setVisible(true);
        currentView.setBounds(0, 0, getWidth(), getHeight());
        revalidate();
        repaint();
    }
    
    private void startTransition(Component target) {
        incomingView = target;
        
        currentSnapshot = createSnapshot(currentView);
        
        incomingView.setVisible(true);
        incomingView.setBounds(0, 0, getWidth(), getHeight());
        incomingView.doLayout();
        incomingView.validate();
        
        incomingSnapshot = createSnapshot(incomingView);
        
        currentView.setVisible(false);
        incomingView.setVisible(false);
        
        isAnimating = true;
        animator.animateTo(1f);
    }
    
    private BufferedImage createSnapshot(Component component) {
        BufferedImage snapshot = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = snapshot.createGraphics();
        component.paint(graphics);
        graphics.dispose();
        return snapshot;
    }
    
    private void finishTransition() {
        isAnimating = false;
        
        if (currentView != null) currentView.setVisible(false);
        currentView = incomingView;
        if (currentView != null) currentView.setVisible(true);
        
        incomingView = null;
        currentSnapshot = null;
        incomingSnapshot = null;
        transitionProgress = 0f;
        animator.animateTo(0f);
    }

    @Override
    public void doLayout() {
        if (!isAnimating && currentView != null) {
            currentView.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    protected void paintChildren(Graphics g) {
        if (isAnimating) {
            paintTransition((Graphics2D) g);
        } else {
            super.paintChildren(g);
        }
    }
    
    private void paintTransition(Graphics2D graphics) {
        if (currentSnapshot != null) {
            drawFadingOutView(graphics);
        }
        
        if (incomingSnapshot != null) {
            drawSlidingInView(graphics);
        }
    }
    
    private void drawFadingOutView(Graphics2D graphics) {
        java.awt.Composite oldComposite = graphics.getComposite();
        graphics.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1f - transitionProgress));
        graphics.drawImage(currentSnapshot, 0, 0, null);
        graphics.setComposite(oldComposite);
    }
    
    private void drawSlidingInView(Graphics2D graphics) {
        java.awt.Composite oldComposite = graphics.getComposite();
        graphics.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, transitionProgress));
        
        int yOffset = (int) (30 * (1f - transitionProgress));
        graphics.drawImage(incomingSnapshot, 0, yOffset, null);
        
        graphics.setComposite(oldComposite);
    }
}
