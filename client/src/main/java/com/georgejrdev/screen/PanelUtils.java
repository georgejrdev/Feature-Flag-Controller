package com.georgejrdev.screen;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicPanelUI;


public class PanelUtils {

    public static JPanel getInitialPanel(Color backgroundColor, GridBagConstraints constraints) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setUI(new BasicPanelUI());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        panel.setBackground(backgroundColor);

        return panel;
    }


    public static JPanel getListFlagsPanel(Color backgroundColor, GridBagConstraints constraints) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setUI(new BasicPanelUI());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        panel.setBackground(backgroundColor);

        return panel;
    }
}