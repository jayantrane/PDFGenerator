package org.example;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.Map;

public class PDFGenerationService {

    public void generatePDF(String inputFile, String outputFile) {
        System.out.println("inputFile:" + inputFile + ",outputFile:" + outputFile);
        FileInputStream in = null;
        try {
            in = new FileInputStream(inputFile);
            XWPFDocument document = new XWPFDocument(in);
            File outFile = new File(outputFile);
            OutputStream out = new FileOutputStream(outFile);
            PdfOptions options = null;
            PdfConverter.getInstance().convert(document, out, options);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
