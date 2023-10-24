package de.stockmaierit.converter.ocr;

import java.awt.Point;
import java.awt.Rectangle;

class CaptureRectangle extends Rectangle {

    private final Point startPoint = new Point(0, 0);
    private final Point endPoint = new Point(0, 0);

    CaptureRectangle() {
        createRectangle();
    }

    void setStartPoint(Point startPoint) {
        this.startPoint.setLocation(startPoint);
        createRectangle();
    }

    void setEndPoint(Point endPoint) {
        this.endPoint.setLocation(endPoint);
        createRectangle();
    }

    void clear() {
        setStartPoint(new Point(0, 0));
        setEndPoint(new Point(0, 0));
        createRectangle();
    }

    private void createRectangle() {
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        this.setBounds(x, y, width, height);
    }
}