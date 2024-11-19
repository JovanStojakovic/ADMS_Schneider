package com.example.ADMS_praksa.services.Impl;

import com.example.ADMS_praksa.dto.ProblemCompletedDTO;
import com.example.ADMS_praksa.dto.ProblemDTO;
import com.example.ADMS_praksa.dto.ProblemProcessedDTO;
import com.example.ADMS_praksa.entities.*;
import com.example.ADMS_praksa.exception.NotFoundException;
import com.example.ADMS_praksa.repositories.ProblemCompletedRepository;
import com.example.ADMS_praksa.repositories.ProblemProcessedRepository;
import com.example.ADMS_praksa.repositories.ProblemRepository;
import com.example.ADMS_praksa.repositories.UserRepository;
import com.example.ADMS_praksa.services.ProblemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProblemProcessedRepository problemProcessedRepository;
    @Autowired
    private ProblemCompletedRepository problemCompletedRepository;

    @Override
    public ProblemDTO save(ProblemDTO problemDTO) {
        Problem problem = modelMapper.map(problemDTO, Problem.class);
        problem.setCreationDate(LocalDate.now());
        problem.setCreationTime(LocalTime.now());
        problem.setStatus(Status.PENDING);
        // Čuvamo prijavu u bazi
        Problem saveProblem = problemRepository.save(problem);
        return modelMapper.map(saveProblem, ProblemDTO.class);
    }
    @Override
    public ProblemDTO saveProblem(long id, ProblemDTO problemDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UsernameNotFoundException("Korisnik nije ulogovan");
        }
        // Pronalazimo problem koji želimo da ažuriramo na osnovu prosleđenog id-ja
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Problem nije pronađen"));

        // Pronalazimo sve probleme sa istim mestom, ulicom i brojem
        List<Problem> problemsWithSameAddress = problemRepository.findByPlaceAndStreetAndNumber(
                problem.getPlace(), problem.getStreet(), problem.getNumber());

        // Ažuriramo vrednosti za sva polja na svim pronađenim problemima
        for (Problem p : problemsWithSameAddress) {
            p.setBrWorkers(problemDTO.getBrWorkers());
            p.setPredictedTime(problemDTO.getPredictedTime());
            p.setPredictedDate(problemDTO.getPredictedDate());
            p.setStatus(Status.PROCESSED);
            // Čuvamo ažurirani problem u bazi
            problemRepository.save(p);
        }

        // Vraćamo DTO prvog ažuriranog problema kao odgovor
        return modelMapper.map(problemsWithSameAddress.get(0), ProblemDTO.class);
    }


    @Override
    public List<ProblemDTO> getProbPending() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UsernameNotFoundException("Korisnik nije ulogovan");
        }
        // Dobijamo sve probleme sa statusom PENDING
        List<Problem> pendingProblems = problemRepository.findByStatus(Status.PENDING);

        // Mapiramo entitete u DTO objekte
        List<ProblemDTO> pendingProblemsDTO = pendingProblems.stream()
                .map(problem -> modelMapper.map(problem, ProblemDTO.class))
                .collect(Collectors.toList());

        return pendingProblemsDTO;
    }

    @Override
    public List<ProblemDTO> getProbProcessed() {
        // Dobijamo sve probleme sa statusom Processed
        List<Problem> processedProblems = problemRepository.findByStatus(Status.PROCESSED);

        // Mapiramo entitete u DTO objekte
        List<ProblemDTO> processedProblemsDTO = processedProblems.stream()
                .map(problem -> modelMapper.map(problem, ProblemDTO.class))
                .collect(Collectors.toList());

        return processedProblemsDTO;
    }
    @Override
    public List<ProblemProcessedDTO> getProbProcessed2() {
        // Dobijamo sve probleme
        List<ProblemProcessed> processed2Problems = problemProcessedRepository.findAll();

        // Mapiramo entitete u DTO objekte
        List<ProblemProcessedDTO> processed2ProblemsDTO = processed2Problems.stream()
                .map(ProblemProcessed -> modelMapper.map(ProblemProcessed, ProblemProcessedDTO.class))
                .collect(Collectors.toList());

        return processed2ProblemsDTO;
    }
    @Override
    public List<ProblemDTO> getProbCompleted() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UsernameNotFoundException("Korisnik nije ulogovan");
        }
        // Dobijamo sve probleme sa statusom COMPLETED
        List<Problem> completedProblems = problemRepository.findByStatus(Status.COMPLETED);

        // Mapiramo entitete u DTO objekte
        List<ProblemDTO> completedProblemsDTO = completedProblems.stream()
                .map(problem -> modelMapper.map(problem, ProblemDTO.class))
                .collect(Collectors.toList());

        return completedProblemsDTO;
    }
    @Override
    public ProblemDTO getProblemById(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UsernameNotFoundException("Korisnik nije ulogovan");
        }
        Problem problem = problemRepository.findById(id).orElseGet(null);
        ProblemDTO problemDTO = modelMapper.map(problem, ProblemDTO.class);
        return problemDTO;
    }

    @Override
    public ProblemProcessedDTO getProblemProcessedById(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UsernameNotFoundException("Korisnik nije ulogovan");
        }
        ProblemProcessed problemProcessed = problemProcessedRepository.findById(id).orElseGet(null);
        ProblemProcessedDTO problemDTO = modelMapper.map(problemProcessed, ProblemProcessedDTO.class);
        return problemDTO;
    }

    @Transactional
    @Override
    public void completeProblem(long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new UsernameNotFoundException("Korisnik nije ulogovan");
        }
        String trenutnoUlogovanUsername = auth.getName();
        // Pronalazimo trenutno ulogovanog korisnika
        User trenutnoUlogovaniKorisnik = userRepository.findByUsername(trenutnoUlogovanUsername)
                .orElseThrow(() -> new NotFoundException("Trenutno ulogovani korisnik nije pronađen"));
        // Pronalazimo problem koji želimo da ažuriramo na osnovu prosleđenog id-ja
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Problem nije pronađen"));
        // Pronalazimo sve probleme sa istom adresom
        List<Problem> problemsWithSameAddress = problemRepository.findByPlaceAndStreetAndNumber(
                problem.getPlace(), problem.getStreet(), problem.getNumber());
        // Ažuriramo status svih problema sa istom adresom na COMPLETED
        for (Problem p : problemsWithSameAddress) {
            p.setStatus(Status.COMPLETED);
        }
        // Čuvamo ažurirane probleme u bazi
        problemRepository.saveAll(problemsWithSameAddress);
        // Kreiramo ProblemCompleted objekat za originalni problem
        ProblemCompleted problemCompleted = new ProblemCompleted();
        problemCompleted.setUserId(trenutnoUlogovaniKorisnik);
        problemCompleted.setProblemId(problem);
        problemCompleted.setCreationDate(LocalDate.now());
        problemCompleted.setCreationTime(LocalTime.now());
        // Čuvamo ProblemCompleted u bazi
        problemCompletedRepository.save(problemCompleted);
        // Brišemo sve povezane ProblemProcessed objekte za sve ažurirane probleme
        problemProcessedRepository.deleteByProblemIdIn(problemsWithSameAddress);
    }


    @Override
    public List<ProblemDTO> getAllProblemsFilteredAndSorted(String sortBy, String place, String street, Integer number, Status status, ProblemType problemType) {
        List<Problem> problems;
        Sort sort = getSort(sortBy);
        problems = problemRepository.findByFilters(place, street, number, status, problemType, sort);

        // Mapiramo listu problema u listu ProblemDTO objekata
        return problems.stream()
                .map(problem -> modelMapper.map(problem, ProblemDTO.class))
                .collect(Collectors.toList());
    }



    private Sort getSort(String sortBy) {
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "place":
                case "street":
                case "number":
                    return Sort.by(sortBy);
                default:
                    return Sort.by("id").ascending();
            }
        } else {
            // Podrazumevano sortiranje po ID-u
            return Sort.by("id").ascending();
        }
    }


}
