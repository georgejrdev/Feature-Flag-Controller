package com.georgejrdev.screen;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;


public class ButtonUtils {

    public static JButton getSelectFolderButton(Color buttonBackgroundColor) {
        JButton button = new JButton("explorer");
        button.setUI(new BasicButtonUI());
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setBackground(buttonBackgroundColor);
        return button;
    }
}