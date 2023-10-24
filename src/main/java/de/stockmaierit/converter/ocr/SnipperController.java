package de.stockmaierit.converter.ocr;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

class SnipperController extends MouseAdapter implements NativeKeyListener {

    private static int START_CAPTURE_KEY = NativeKeyEvent.VC_SCROLL_LOCK;
    private static int CLOSE_APP_KEY = NativeKeyEvent.VC_ESCAPE;
    private static final String ICON_PATH = "icon.png";

    private final CaptureRectangle captureRectangle;
    private final CaptureView captureView;
    private final ResultView resultView;
    private final OcrImageConverter imageConverter = new OcrImageConverter();

    SnipperController() {
        this.captureRectangle = new CaptureRectangle();

        var captureGlassPane = new CaptureGlassPane(captureRectangle);
        captureGlassPane.addMouseListener(this);
        captureGlassPane.addMouseMotionListener(this);

        this.captureView = new CaptureView(captureGlassPane);
        this.resultView = new ResultView();

        setIcon(captureView);
        setIcon(resultView);

        registerNativeHookListeners();
    }

    private void registerNativeHookListeners() {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeNativeHookListeners() {
        try {
            GlobalScreen.removeNativeKeyListener(this);
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        if (nativeEvent.getKeyCode() == START_CAPTURE_KEY) {
            if (!captureView.isCaptureActive()) {
                captureRectangle.clear();
                captureView.startCapture();
            } else {
                captureView.stopCapture();
            }
        }

        if (nativeEvent.getKeyCode() == CLOSE_APP_KEY) {
            removeNativeHookListeners();
            captureView.close();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        captureRectangle.setStartPoint(mouseEvent.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        captureRectangle.setEndPoint(mouseEvent.getPoint());
        captureView.getGlassPane().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        captureView.stopCapture();

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                var robot = new Robot();

                if (captureRectangle.width > 0 && captureRectangle.height > 0) {
                    var snippet = robot.createScreenCapture(captureRectangle);
                    var text = imageConverter.convertToString(snippet);
                    resultView.setText(text);
                    resultView.setVisible(true);
                }
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        });
    }

    void start() {
        captureView.setVisible(true);
    }

    private void setIcon(JFrame frame) {
        try {
            var icon = new ImageIcon(Paths.get(ICON_PATH).toUri().toURL());
            frame.setIconImage(icon.getImage());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}