package com.amdocs.ai.hackathon.hackathon.repository;

import com.amdocs.ai.hackathon.hackathon.utility.CommandlineUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocalRepository {

    @Autowired
    private CommandlineUtility commandlineUtility;

    public void getFilesInPath(String path){
        String files = commandlineUtility.getFilesByPath(path);
        System.out.println(files);
    }

    public void getFilesRecursivelyInPath(String path){
        //TODO: get the string of the files to be used later in the message to AI
        commandlineUtility.getFilesRecursivelyByPath(path);
    }

}
