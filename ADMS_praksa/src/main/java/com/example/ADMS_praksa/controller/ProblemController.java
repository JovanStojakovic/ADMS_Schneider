package com.example.ADMS_praksa.controller;

import com.example.ADMS_praksa.dto.ProblemDTO;
import com.example.ADMS_praksa.dto.ProblemProcessedDTO;
import com.example.ADMS_praksa.entities.ProblemType;
import com.example.ADMS_praksa.entities.Status;
import com.example.ADMS_praksa.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;
    @PostMapping("/add")
    public ResponseEntity<ProblemDTO> addProblem(@RequestBody ProblemDTO problemDTO) {
        ProblemDTO saveProblemDTO = problemService.save(problemDTO);
        return new ResponseEntity<>(saveProblemDTO, HttpStatus.CREATED);
    }
    @GetMapping("/pending")
    public ResponseEntity<List<ProblemDTO>> getProblemPending() {
        List<ProblemDTO> problemPendingList = problemService.getProbPending();
        return new ResponseEntity<>(problemPendingList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProblemDTO> getProblemById(@PathVariable long id) {
        ProblemDTO problemById = problemService.getProblemById(id);
        return new ResponseEntity<>(problemById, HttpStatus.OK);
    }
    @GetMapping("/processed/{id}")
    public ResponseEntity<ProblemProcessedDTO> getProblemProcessedById(@PathVariable long id) {
        ProblemProcessedDTO problemById = problemService.getProblemProcessedById(id);
        return new ResponseEntity<>(problemById, HttpStatus.OK);
    }

    @GetMapping("/processed")
    public ResponseEntity<List<ProblemDTO>> getProblemProcessed() {
        List<ProblemDTO> problemProcessedList = problemService.getProbProcessed();
        return new ResponseEntity<>(problemProcessedList, HttpStatus.OK);
    }
    @GetMapping("/processed2")
    public ResponseEntity<List<ProblemProcessedDTO>> getProblemProcessed2() {
        List<ProblemProcessedDTO> problemProcessed2List = problemService.getProbProcessed2();
        return new ResponseEntity<>(problemProcessed2List, HttpStatus.OK);
    }
    @GetMapping("/completed")
    public ResponseEntity<List<ProblemDTO>> getProblemCompleted() {
        List<ProblemDTO> problemCompletedList = problemService.getProbCompleted();
        return new ResponseEntity<>(problemCompletedList, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProblemDTO>> getAllProblemsFilteredAndSorted(
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "place", required = false) String place,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "number", required = false) Integer number,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "problemType", required = false) ProblemType problemType){

        List<ProblemDTO> allProblemsList = problemService.getAllProblemsFilteredAndSorted(sortBy, place, street, number, status, problemType);

        return new ResponseEntity<>(allProblemsList, HttpStatus.OK);
    }
}
