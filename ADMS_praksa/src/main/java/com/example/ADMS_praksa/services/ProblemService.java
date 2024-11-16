package com.example.ADMS_praksa.services;

import com.example.ADMS_praksa.dto.ProblemCompletedDTO;
import com.example.ADMS_praksa.dto.ProblemDTO;
import com.example.ADMS_praksa.dto.ProblemProcessedDTO;
import com.example.ADMS_praksa.entities.ProblemType;
import com.example.ADMS_praksa.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProblemService {
    ProblemDTO save(ProblemDTO problemDTO);
    ProblemDTO saveProblem(long id, ProblemDTO problemDTO);
    //ProblemProcessedDTO update (long id, ProblemProcessedDTO problemProcessedDTO);
    //ProblemCompletedDTO saveProblemCompleted(ProblemCompletedDTO problemCompletedDTO);
    List<ProblemDTO> getProbPending();
    List<ProblemDTO> getProbProcessed();
    List<ProblemDTO> getProbCompleted();
    List<ProblemProcessedDTO> getProbProcessed2();
    List<ProblemDTO> getAllProblemsFilteredAndSorted(String sortBy, String place, String street, Integer number, Status status, ProblemType problemType);
     ProblemDTO getProblemById (long id);
     ProblemProcessedDTO getProblemProcessedById (long id);
     void completeProblem (long id);

}
