package de.stockmaierit.converter.ocr;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

class SnipperApp {

    private static void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            initLookAndFeel();
            var snipperViewController = new SnipperController();
            snipperViewController.start();
        });
    }
}