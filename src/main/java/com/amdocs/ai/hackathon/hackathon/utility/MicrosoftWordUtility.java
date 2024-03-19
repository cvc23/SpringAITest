package com.amdocs.ai.hackathon.hackathon.utility;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class MicrosoftWordUtility {

    //Function to create a word File Sample
    public void createWordFile() throws IOException {

        File file = new File("./wordTemplate.docx");
        XWPFDocument document = new XWPFDocument();
        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(file);
            document = new  XWPFDocument(inputStream);
            inputStream.close();
        }
        //Start formating the file...
        XWPFParagraph par = document.createParagraph();
        XWPFRun run = par.createRun();
        run.setText("This is a new line");

        //set the path for the new file
        FileOutputStream out = new FileOutputStream("anotherFile.docx");
        //write file and close it.
        document.write(out);
        out.close();
        document.close();

    }

}
