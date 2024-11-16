package com.example.ADMS_praksa.controller;

import com.example.ADMS_praksa.dto.ProblemCompletedDTO;
import com.example.ADMS_praksa.dto.ProblemDTO;
import com.example.ADMS_praksa.dto.ProblemProcessedDTO;
import com.example.ADMS_praksa.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operator")
public class OperatorController {
    @Autowired
    private ProblemService problemService;
    @PutMapping("/addProcessed/{id}")
    public ResponseEntity<ProblemDTO> addProblemProcessed(@PathVariable long id, @RequestBody ProblemDTO problemDTO) {
        ProblemDTO updatedProblemDTO = problemService.saveProblem(id, problemDTO);
        return new ResponseEntity<>(updatedProblemDTO, HttpStatus.OK);
    }
    @PutMapping("/completeProblem/{id}")
    public ResponseEntity<Void> completeProblem(@PathVariable long id) {
        problemService.completeProblem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
