package com.amdocs.ai.hackathon.hackathon.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PromtRequest {

    private String gitPath;
    private String projectName;

    //Optional
    //private boolean withIndexTable;
    //private boolean withTemplate;


}
