package com.amdocs.ai.hackathon.hackathon.service;

import com.amdocs.ai.hackathon.hackathon.model.PromtRequest;
import com.amdocs.ai.hackathon.hackathon.repository.GitRepository;
import com.amdocs.ai.hackathon.hackathon.repository.LocalRepository;
import com.amdocs.ai.hackathon.hackathon.utility.MicrosoftWordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DocumentService {

    @Autowired
    private GitRepository gitRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private MicrosoftWordUtility microsoftWordUtility;

    public void createDocument(PromtRequest promtRequest){
        //get repo from git
        String path = gitRepository.getRepositoryFromGit(promtRequest.getGitPath());
        //Work with local repo to get files
        localRepository.getFilesInPath(path);
        localRepository.getFilesRecursivelyInPath(path);
        //Start calling chatGPT


        //Create a word document with output
        try {
            microsoftWordUtility.createWordFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
