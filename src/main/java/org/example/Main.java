package org.example;

import java.util.Map;

public class Main {

    static DocsReaderService docsReaderService;
    static PDFGenerationService pdfGenerationService;
    static SimpleUpdateDocsService simpleUpdateDocsService;
    public static void main(String[] args) {

//        docsReaderService = new DocsReaderService();
//        Map<String, String> userInfo = docsReaderService.readDocFile();
//
//        pdfGenerationService = new PDFGenerationService();
//        pdfGenerationService.createPDF(userInfo);
//        pdfGenerationService.updatePDF("Ashwini", "test@gmail.com");

//        String inputFileName = "src/main/resources/basic5pagedoc.docx";
//        String outputFileName = "src/main/resources/basic5pagedoc.pdf";
//        String nameToBeUpdated = "Name Replaced";
//        String emailToBeUpdated = "Email got Updated";
//        simpleUpdateDocsService = new SimpleUpdateDocsService();
//        String generatedDocFile = simpleUpdateDocsService.findAndReplaceAllText(inputFileName, nameToBeUpdated, emailToBeUpdated);
//        pdfGenerationService = new PDFGenerationService();
//        pdfGenerationService.generatePDF(generatedDocFile, outputFileName);

        String inputFileName = "src/main/resources/sample.pdf";
        String outputFileName = "src/main/resources/sample-converted.pdf";
        String nameToBeUpdated = "Name Replaced";
        String emailToBeUpdated = "Email got Updated";
        PDFParserService pdfParserService = new PDFParserService();
        pdfParserService.findAndReplaceAllText(inputFileName, nameToBeUpdated, emailToBeUpdated);

    }
}