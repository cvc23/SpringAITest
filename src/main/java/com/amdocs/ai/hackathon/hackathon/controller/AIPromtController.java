package com.amdocs.ai.hackathon.hackathon.controller;

import com.amdocs.ai.hackathon.hackathon.model.PromtRequest;
import com.amdocs.ai.hackathon.hackathon.model.PromtResponse;
import com.amdocs.ai.hackathon.hackathon.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIPromtController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("document")
    public ResponseEntity<PromtResponse> getPromtDocumentation(@RequestBody PromtRequest promtRequest){
        documentService.createDocument(promtRequest);
        return ResponseEntity.ok(new PromtResponse("path..."));
    }

}
