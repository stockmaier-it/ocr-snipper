package de.stockmaierit.converter.ocr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ResultView extends JFrame {

    private final JTextArea textArea;

    ResultView() throws HeadlessException {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(new Color(0, 0, 0, 1));
        setMinimumSize(new Dimension(400, 250));

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        var scrollPane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        var contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(scrollPane);

        add(contentPane);

        addComponentListener(new ResultViewComponentAdapter());
        addWindowFocusListener(new ResultViewWindowAdapter());

        pack();
        setLocationRelativeTo(null);
    }

    void setText(String text) {
        textArea.setText(text);
    }

    private class ResultViewComponentAdapter extends ComponentAdapter {
        @Override
        public void componentShown(ComponentEvent e) {
            textArea.selectAll();
        }
    }

    private class ResultViewWindowAdapter extends WindowAdapter {
        @Override
        public void windowLostFocus(WindowEvent e) {
            dispose();
        }
    }
}