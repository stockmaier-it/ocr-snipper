package de.stockmaierit.converter.ocr;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;
import javax.swing.JComponent;

class CaptureGlassPane extends JComponent {

    private final CaptureRectangle rectangle;

    CaptureGlassPane(CaptureRectangle rectangle) {
        this.rectangle = Objects.requireNonNull(rectangle);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var graphics2d = (Graphics2D) getGraphics();
        graphics2d.setColor(Color.green);
        graphics2d.setStroke(new BasicStroke(2));
        graphics2d.draw(rectangle);
    }
}