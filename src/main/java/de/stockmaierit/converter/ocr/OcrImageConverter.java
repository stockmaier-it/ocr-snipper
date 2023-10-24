package de.stockmaierit.converter.ocr;

import java.awt.image.BufferedImage;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

class OcrImageConverter {

    private final ITesseract tesseract;

    OcrImageConverter() {
        tesseract = new Tesseract();
        tesseract.setLanguage("deu");
    }

    String convertToString(BufferedImage image) {
        try {
            return tesseract.doOCR(image);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }
    }
}