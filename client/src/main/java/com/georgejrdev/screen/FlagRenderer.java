package com.georgejrdev.screen;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.georgejrdev.utils.support.Flag;


public class FlagRenderer extends JLabel implements ListCellRenderer<Flag> {

    private Color backgroundColor;
    private Color fontColor;

    public FlagRenderer(Color backgroundColor, Color fontColor) {
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
        setOpaque(true);
    }


    @Override
    public Component getListCellRendererComponent(JList<? extends Flag> list, Flag value, int index, boolean isSelected, boolean cellHasFocus) {
        String html = "<html>"
            + "<div style='font-family: Arial, sans-serif; font-weight: bold; color: white; background-color: rgb(25, 25, 25); padding: 10px; margin-bottom: 10px;'>"
            + "<span style='color: rgb(199,199,0);'>File: </span>" + value.getFileName() + "<br>"
            + "<span style='color: rgb(50,186,0);'>Type: </span>" + value.getType() + "<br>"
            + "<span style='color: rgb(1,199,169);'>Description: </span>" + value.getDescription() + "<br>"
            + "<span style='color: rgb(199,0,196);'>Line: </span>" + value.getLine()
            + "</div></html>";

        setText(html);
        setBackground(isSelected ? list.getSelectionBackground() : backgroundColor);
        setForeground(isSelected ? list.getSelectionForeground() : fontColor);

        return this;
    }
}