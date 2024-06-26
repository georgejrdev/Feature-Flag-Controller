package com.georgejrdev.screen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import java.io.File;
import java.util.List;

import com.georgejrdev.utils.LocalizeFlags;
import com.georgejrdev.utils.support.Flag;

public class Frame {

    protected LocalizeFlags localizeFlags;
    protected final String FONT = "Arial";
    protected final int FONT_SIZE = 14;
    protected final Color BACKGROUND_COLOR = new Color(25, 25, 25);
    protected final Color BUTTON_BACKGROUND_COLOR = new Color(11, 11, 11);
    protected final Color FONT_COLOR = new Color(255, 255, 255);
    protected final GridBagConstraints CONSTRAINTS = new GridBagConstraints();

    public Frame(LocalizeFlags localizeFlags) {
        this.localizeFlags = localizeFlags;
        start();
    }


    protected JFrame getInitialFrame() {
        JFrame frame = new JFrame("Feature Flag Controller");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        frame.getContentPane().setBackground(this.BACKGROUND_COLOR);

        JPanel panel = getInitialPanel();
        setInitialLabel(panel);
        JButton button = getSelectFolderButton(panel);

        setActionSelectFolderButton(frame, button);

        frame.add(panel);

        return frame;
    }


    protected JPanel getInitialPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        this.CONSTRAINTS.gridx = 0;
        this.CONSTRAINTS.gridy = 0;
        this.CONSTRAINTS.insets = new Insets(10, 10, 10, 10);

        panel.setBackground(this.BACKGROUND_COLOR);

        return panel;
    }


    protected void setInitialLabel(JPanel panel) {
        JLabel label = new JLabel("Select a folder");
        this.CONSTRAINTS.gridy = 0;
        panel.add(label, this.CONSTRAINTS);
    }


    protected JButton getSelectFolderButton(JPanel panel) {
        JButton button = new JButton("explorer");
        this.CONSTRAINTS.gridy = 1;

        button.setBackground(this.BUTTON_BACKGROUND_COLOR);

        panel.add(button, this.CONSTRAINTS);

        return button;
    }


    protected void setActionSelectFolderButton(JFrame frame, JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(frame);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFolder = fileChooser.getSelectedFile();
                    String folderPath = selectedFolder.getAbsolutePath();
                    System.out.println(folderPath);

                    try {
                        handleFolderSelection(frame, folderPath);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                } else {
                    System.out.println("NO FOLDER SELECTED");
                }
            }
        });
    }


    protected JPanel getListFlagsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        this.CONSTRAINTS.gridx = 0;
        this.CONSTRAINTS.gridy = 0;
        this.CONSTRAINTS.insets = new Insets(10, 10, 10, 10);

        panel.setBackground(this.BACKGROUND_COLOR);

        return panel;
    }

    
    protected void handleFolderSelection(JFrame frame, String path) throws Exception {
        List<Flag> flags = localizeFlags.searchAllFlags(path);
        frame.getContentPane().removeAll();
        frame.setLayout(new GridBagLayout());
        JPanel panel = createFlagListPanel(flags);
        frame.add(panel, this.CONSTRAINTS);
        frame.revalidate();
        frame.repaint();
    }


    protected JPanel createFlagListPanel(List<Flag> flags) {
        JPanel panel = getListFlagsPanel();
        DefaultListModel<Flag> listModel = new DefaultListModel<>();
        for (Flag flag : flags) {
            listModel.addElement(flag);
        }
        JList<Flag> flagList = createFlagList(listModel);
        JScrollPane scrollPane = new JScrollPane(flagList);
        scrollPane.setPreferredSize(panel.getSize());
        this.CONSTRAINTS.gridx = 0;
        this.CONSTRAINTS.gridy = 0;
        this.CONSTRAINTS.weightx = 1.0;
        this.CONSTRAINTS.weighty = 1.0;
        this.CONSTRAINTS.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, this.CONSTRAINTS);
        return panel;
    }


    protected JList<Flag> createFlagList(DefaultListModel<Flag> listModel) {
        JList<Flag> flagList = new JList<>(listModel);
        flagList.setCellRenderer(new FlagRenderer());
        return flagList;
    }


    protected void setUIFont(Font font, Container container) {
        for (Component component : container.getComponents()) {
            component.setFont(font);
            component.setForeground(FONT_COLOR);

            if (component instanceof Container) {
                setUIFont(font, (Container) component);
            }
        }
    }

    protected void start() {
        JFrame frame = getInitialFrame();
        Font newFont = new Font(this.FONT, Font.BOLD, this.FONT_SIZE);
        setUIFont(newFont, frame);
        frame.setVisible(true);
    }


    class FlagRenderer extends JLabel implements ListCellRenderer<Flag> {
        public FlagRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Flag> list, Flag value, int index, boolean isSelected, boolean cellHasFocus) {
            String html = "<html>"
            + "<div style='font-family: Arial, sans-serif; font-weight: bold; color: white; background-color: rgb(25, 25, 25); padding: 10px; margin-bottom: 10px;'>"
            + "<span style='color: rgb(28,108,0);'>File: </span>" + value.getFileName() + "<br>"
            + "<span style='color: rgb(28,108,0);'>Type: </span>" + value.getType() + "<br>"
            + "<span style='color: rgb(28,108,0);'>Description: </span>" + value.getDescription() + "<br>"
            + "<span style='color: rgb(28,108,0);'>Line: </span>" + value.getLine()
            + "</div></html>";

            setText(html);
            setBackground(isSelected ? list.getSelectionBackground() : BACKGROUND_COLOR);
            setForeground(isSelected ? list.getSelectionForeground() : FONT_COLOR);

            return this;
        }
    }

}