package com.amdocs.ai.hackathon.hackathon.repository;

import com.amdocs.ai.hackathon.hackathon.utility.CommandlineUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GitRepository {

    @Autowired
    private CommandlineUtility comandlineUtility;

    public String getRepositoryFromGit(String gitUrl){
        return comandlineUtility.runGitCommand(gitUrl);
    }
}
