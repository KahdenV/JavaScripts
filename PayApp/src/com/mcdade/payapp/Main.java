package com.mcdade.payapp;

import com.mcdade.payapp.gui.MainMenu_GUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ensures the GUI runs on the Event Dispatch Thread (required for thread-safe Swing UI)
        SwingUtilities.invokeLater(() -> new MainMenu_GUI());
    }
}
