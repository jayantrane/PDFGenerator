package org.example;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PDFParserService {

    private final static String FIND_NAME = "Virtual Mechanics tutorials";
    private final static String FIND_EMAIL = "boring typing";

    private String fileName = "src/main/resources/sample.pdf";
    private String outputFileName = "src/main/resources/sample-converted.pdf";

    public String findAndReplaceAllText(String fileName, String name, String email) {
        PdfReader reader = null;
        try {
            reader = new PdfReader(fileName);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                PdfDictionary dict = reader.getPageN(i);
                System.out.println("Current page: " + i);

                PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
                if (object instanceof PRStream) {
                    PRStream stream = (PRStream) object;
                    byte[] data = PdfReader.getStreamBytes(stream);
                    String dd = new String(data);
                    dd = dd.replace(FIND_NAME, name);
                    dd = dd.replace(FIND_EMAIL, email);
                    // Only good PDF can be converted, otherwise iText doesn't work properly, as only different size of chars are group together
                    System.out.print("Data from the current page is as follows: " + dd);
                    stream.setData(dd.getBytes());

                }

            }
            PdfStamper stamper = new PdfStamper(reader,
                    Files.newOutputStream(Paths.get(outputFileName)));
            stamper.close();
            reader.close();

        } catch (IOException | DocumentException e) {
//            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return outputFileName;
    }

}
