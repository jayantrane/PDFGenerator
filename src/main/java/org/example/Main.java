package org.example;

import java.util.Map;

public class Main {

    static DocsReaderService docsReaderService;
    static PDFGenerationService pdfGenerationService;
    public static void main(String[] args) {

//        docsReaderService = new DocsReaderService();
//        Map<String, String> userInfo = docsReaderService.readDocFile();
//
//        pdfGenerationService = new PDFGenerationService();
//        pdfGenerationService.createPDF(userInfo);

        pdfGenerationService = new PDFGenerationService();
        pdfGenerationService.generatePDF();


    }
}