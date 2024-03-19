package com.amdocs.ai.hackathon.hackathon.utility;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@Component
public class CommandlineUtility {

    @Value("${application.workdir}")
    private String workDir;

    @Value("${application.files.extensions}")
    private String extensions;

    public String runGitCommand(String repository){
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd " + workDir + " && git clone " + repository);
        String folderName = repository.split("/")[repository.split("/").length -1];
        folderName = folderName.split("\\.")[0];
        builder.redirectErrorStream(true);
        try {
            Process p = builder.start();
            //TODO: check if this can be improved... blocking the thread
            p.waitFor();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return workDir + "/" + folderName;
    }

    public String getFilesByPath(String path){
        String files = "";
        //Creating a File object for directory
        File directoryPath = new File(path);
        //List of all files and directories
        String contents[] = directoryPath.list();
        System.out.println("List of files and directories in the specified directory:");
        for(int i=0; i<contents.length; i++) {
            System.out.println(contents[i]);
            files = files + " "  + contents[i];
        }
        return files;
    }

    public void getFilesRecursivelyByPath(String path){
        Collection files = FileUtils.listFiles(
                new File(path),
                new RegexFileFilter("(.*( " + extensions + "))"),
                DirectoryFileFilter.DIRECTORY
        );
        //Creating a File object for directory
        files.stream().forEach(System.out::println);
    }
}
