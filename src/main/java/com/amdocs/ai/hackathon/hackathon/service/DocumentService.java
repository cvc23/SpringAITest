package com.amdocs.ai.hackathon.hackathon.service;

import com.amdocs.ai.hackathon.hackathon.model.PromtRequest;
import com.amdocs.ai.hackathon.hackathon.repository.GitRepository;
import com.amdocs.ai.hackathon.hackathon.repository.LocalRepository;
import com.amdocs.ai.hackathon.hackathon.repository.PromptRepository;
import com.amdocs.ai.hackathon.hackathon.utility.FileUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private GitRepository gitRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private PromptRepository promptRepository;

    @Autowired
    private FileUtility fileUtility;

    public void createDocument(PromtRequest promtRequest){
        //get repo from git
        String path = gitRepository.getRepositoryFromGit(promtRequest.getGitPath());
        //Work with local repo to get files
        localRepository.getFilesInPath(path);
        Collection<File> files = localRepository.getFilesRecursivelyInPath(path);
        //TODO: add logic to create message for OpenAI
        //TODO: Store the OpenIA response...
        files.forEach(file -> {
           List<String> content = FileUtility.getFileContentByPath(file.toPath());
            //TODO: Start calling chatGPT
            //Verify if it has to be done for each file or not...
            //promptRepository.callChatGPT(message);

        });
        //Create a word document with output
        try {
            fileUtility.createWordFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
