package com.example.funiversity.controllers;

import com.example.funiversity.dto.CreateProfessorDto;
import com.example.funiversity.dto.ProfessorDto;
import com.example.funiversity.dto.UpdateProfessorDto;
import com.example.funiversity.exceptions.ProfessorAlreadyExistsException;
import com.example.funiversity.exceptions.ProfessorNotFoundException;
import com.example.funiversity.services.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfessorDto> findAllProfessors() {
        return service.findAllProfessors();
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDto findProfessorById(@PathVariable String id) {
        return service.findProfessorById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorDto createProfessor(@RequestBody CreateProfessorDto professorToCreate) {
        return service.createProfessor(professorToCreate);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfessorDto updateProfessor(@PathVariable String id, @RequestBody UpdateProfessorDto professorToUpdate) {
        return service.updateProfessor(id, professorToUpdate);
    }

    @DeleteMapping("{id}")
    public void deleteProfessor(@PathVariable String id) {
        service.deleteProfessor(id);
    }

    @ExceptionHandler(ProfessorNotFoundException.class)
    protected void professorNotFoundException(ProfessorNotFoundException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ProfessorAlreadyExistsException.class)
    protected void professorAlreadyExistsException(ProfessorAlreadyExistsException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }

}
