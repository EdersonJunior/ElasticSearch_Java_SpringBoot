package com.candidates.controller;

import com.candidates.component.CandidatesImp;
import com.candidates.model.Candidate;
import com.candidates.model.CandidateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/candidates")
@CrossOrigin("*")
public class CandidatesController {

    @Qualifier("candidatesImp")
    @Autowired
    private CandidatesImp candidatesImp;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Candidate> searchById(@PathVariable String id) throws IOException {
        return new ResponseEntity<>(candidatesImp.searchById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Candidate>> searchAll() throws IOException, NoSuchFieldException {
        return new ResponseEntity<>(candidatesImp.searchAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateById(@RequestBody CandidateRequest candidateRequest, @PathVariable int id) throws IOException, URISyntaxException {
        candidateRequest.setId(id);
        candidatesImp.updateById(candidateRequest);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) throws IOException, NoSuchFieldException {
        candidatesImp.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

}
