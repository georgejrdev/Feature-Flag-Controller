package com.georgejrdev.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.border.EmptyBorder;


public class CustomScrollBarUI extends BasicScrollBarUI {

    private Color trackColor = new Color(50, 50, 50);
    private Color thumbColor = new Color(100, 100, 100);


    @Override
    protected void configureScrollBarColors() {}


    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }


    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }


    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        return button;
    }


    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(thumbColor);
        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
    }
}