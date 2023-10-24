package de.stockmaierit.converter.ocr;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

class CaptureView extends JFrame {

    CaptureView(CaptureGlassPane captureGlassPane) throws HeadlessException {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));
        setGlassPane(captureGlassPane);
    }

    boolean isCaptureActive() {
        return this.getCursor().getType() == Cursor.CROSSHAIR_CURSOR;
    }

    private void moveMouseOnePixelToTriggerEvent() {
        try {
            var currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            var robot = new Robot();
            robot.mouseMove((int) currentMouseLocation.getX() + 1, (int) currentMouseLocation.getY());
            robot.mouseMove((int) currentMouseLocation.getX(), (int) currentMouseLocation.getY());
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    void startCapture() {
        if (!isCaptureActive()) {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            setBackground(new Color(0, 0, 0, 1));
            getGlassPane().setVisible(true);
            moveMouseOnePixelToTriggerEvent();
        }
    }

    void stopCapture() {
        setCursor(Cursor.getDefaultCursor());
        setBackground(new Color(0, 0, 0, 0));
        getGlassPane().setVisible(false);
    }

    void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}