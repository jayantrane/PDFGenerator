package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocsReaderService {

    public Map<String, String> readDocFile() {
        List<XWPFParagraph> paragraphs;
        Map<String, String> userInfo = new HashMap<>();
        try {
            Path msWordPath = Paths.get("/Users/jayantrane/Documents/Intellij/Workspace1/PDFGenerator/src/main/resources/ashwinitest.docx");
            XWPFDocument document = null;

            document = new XWPFDocument(Files.newInputStream(msWordPath));

            paragraphs = document.getParagraphs();
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XWPFParagraph name = paragraphs.get(0);
        XWPFParagraph email = paragraphs.get(1);
        System.out.println(name.getText());
        System.out.println(email.getText());
        userInfo.put("name", name.getText());
        userInfo.put("email", email.getText());
        return userInfo;
    }
}
