package com.synergy.utils;

import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Animator {
    private final Timer timer;
    private final int duration;
    private long startTime;
    private boolean isRunning = false;
    private float currentProgress = 0f;
    private float startValue = 0f;
    private float targetValue = 0f;
    private final AnimationCallback callback;

    public interface AnimationCallback {
        void onUpdate(float value);
    }

    public Animator(int duration, AnimationCallback callback) {
        this.duration = duration;
        this.callback = callback;
        
        timer = new Timer(16, e -> update());
    }

    public void animateTo(float target) {
        if (Math.abs(target - currentProgress) < 0.001f) return;
        
        this.startValue = currentProgress;
        this.targetValue = target;
        this.startTime = System.currentTimeMillis();
        
        if (!isRunning) {
            isRunning = true;
            timer.start();
        }
    }

    private void update() {
        long now = System.currentTimeMillis();
        long elapsed = now - startTime;
        
        if (elapsed >= duration) {
            currentProgress = targetValue;
            isRunning = false;
            timer.stop();
            callback.onUpdate(currentProgress);
            return;
        }

        float fraction = (float) elapsed / duration;
        fraction = 1f - (float) Math.pow(1 - fraction, 3);
        
        currentProgress = startValue + (targetValue - startValue) * fraction;
        callback.onUpdate(currentProgress);
    }
    
    public float getValue() {
        return currentProgress;
    }
}
