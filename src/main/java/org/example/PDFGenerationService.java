package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
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
}
