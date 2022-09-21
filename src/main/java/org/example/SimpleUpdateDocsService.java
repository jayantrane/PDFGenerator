package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SimpleUpdateDocsService {

    private final static String FIND_NAME = "AUTOFILL NAME HERE";
    private final static String FIND_EMAIL = "AUTOFILE EMAIL HERE";

    private String fileName = "src/main/resources/basic5pagedoc.docx";
    private String convertedFileName = "src/main/resources/basic5pagedoc-converted.docx";
    private String convertedNameFileName = "src/main/resources/basic5pagedoc-convertedname.docx";

    public String findAndReplaceAllText(String fileName, String name, String email) {
        findAndReplaceText(fileName, convertedNameFileName, FIND_NAME, name);
        findAndReplaceText(convertedNameFileName, convertedFileName, FIND_EMAIL, email);

        return convertedFileName;
    }
    private void findAndReplaceText(String inputFileName, String outputFileName, String findString, String replaceString) {
        try {
            XWPFDocument doc = new XWPFDocument(OPCPackage.open(inputFileName));
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains(findString)) {
                            text = text.replace(findString, replaceString);
                            r.setText(text, 0);
                        }
                    }
                }
            }

            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null && text.contains(findString)) {
                                    text = text.replace(findString, replaceString);
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }

            doc.write(Files.newOutputStream(Paths.get(outputFileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        } finally {

        }

    }
}
