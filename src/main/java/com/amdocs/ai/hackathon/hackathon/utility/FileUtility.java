package com.amdocs.ai.hackathon.hackathon.utility;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtility {

    @Value("${application.workdir}")
    private String workDir;

    //Function to create a word File Sample
    public void createWordFile() throws IOException {

        File file = new File(workDir +"/wordTemplate.docx");
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
        FileOutputStream out = new FileOutputStream(workDir + "outputFile.docx");
        //write file and close it.
        document.write(out);
        out.close();
        document.close();

    }

    public static List<String> getFileContentByPath(Path path){
        List<String> content;
        try {
            content = Files.readAllLines(path);
            content.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

}
