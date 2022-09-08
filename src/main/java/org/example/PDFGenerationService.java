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

    public void createPDF(Map<String, String> userInfo) {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(25, 600);

            String text = "Name: " + userInfo.get("name") + " Email Address: " + userInfo.get("email");
            contentStream.showText(text);
            contentStream.endText();
            System.out.println("Content added");

            contentStream.close();
            document.addPage(page);
            document.save("/Users/jayantrane/Documents/Intellij/Workspace1/PDFGenerator/src/main/resources/ashwinitestpdf.pdf");
            System.out.println("PDF created");
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void generatePDF() {
        String inputFile = "/Users/jayantrane/Documents/Intellij/Workspace1/PDFGenerator/src/main/resources/RandomTest.docx";
        String outputFile = "/Users/jayantrane/Documents/Intellij/Workspace1/PDFGenerator/src/main/resources/ashwinitestgenpdf.pdf";
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

    public void updatePDF(String name, String email) {

        try {
            File file = new File("/Users/jayantrane/Documents/Intellij/Workspace1/PDFGenerator/src/main/resources/ashwinitestgenpdf.pdf");
            PDDocument document = PDDocument.load(file);
            PDPage page = document.getPage(0);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(0, 0);

            String text = "Name: " + name + " Email Address: " + email;
            contentStream.showText(text);
            contentStream.endText();
            System.out.println("Content added");

            contentStream.close();
            document.addPage(page);
            document.save("/Users/jayantrane/Documents/Intellij/Workspace1/PDFGenerator/src/main/resources/ashwinitestpdf.pdf");
            System.out.println("PDF created");
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
