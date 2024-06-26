package com.georgejrdev.screen;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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

        JPanel panel = PanelUtils.getInitialPanel(this.BACKGROUND_COLOR, this.CONSTRAINTS);
        setInitialLabel(panel);
        JButton button = ButtonUtils.getSelectFolderButton(this.BUTTON_BACKGROUND_COLOR);

        setActionSelectFolderButton(frame, button);

        this.CONSTRAINTS.gridy = 1;  
        panel.add(button, this.CONSTRAINTS);

        frame.add(panel);

        return frame;
    }


    protected void setInitialLabel(JPanel panel) {
        JLabel label = new JLabel("Select a folder");
        this.CONSTRAINTS.gridy = 0;
        panel.add(label, this.CONSTRAINTS);
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

                    try {
                        handleFolderSelection(frame, folderPath);
                    } catch (Exception handleFolderException) {
                        handleFolderException.printStackTrace();
                    }

                }
            }
        });
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
        JPanel panel = PanelUtils.getListFlagsPanel(this.BACKGROUND_COLOR, this.CONSTRAINTS);
        DefaultListModel<Flag> listModel = new DefaultListModel<>();
        for (Flag flag : flags) {
            listModel.addElement(flag);
        }
        JList<Flag> flagList = new JList<>(listModel);
        flagList.setCellRenderer(new FlagRenderer(this.BACKGROUND_COLOR, this.FONT_COLOR));

        JScrollPane scrollPane = new JScrollPane(flagList);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setPreferredSize(panel.getSize());
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

        this.CONSTRAINTS.gridx = 0;
        this.CONSTRAINTS.gridy = 0;
        this.CONSTRAINTS.weightx = 1.0;
        this.CONSTRAINTS.weighty = 1.0;
        this.CONSTRAINTS.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, this.CONSTRAINTS);

        return panel;
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
}