package src.gui;

import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MainWindow_GUI {
    
    public MainWindow_GUI() {
        //Create instance of JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        int wood = 0;

        //Labels
        Label l1 = new Label("Wood:" + wood);

        l1.setBounds(210,100,120,80);

        frame.add(l1);

        //Buttons
        Button b1 = new Button("Wood");


        // 400 width and 500 height
        frame.setSize(500,600);

        // Center Frame
        frame.setLocationRelativeTo(null);

        // make the frame visible
        frame.setVisible(true);
    }
}
