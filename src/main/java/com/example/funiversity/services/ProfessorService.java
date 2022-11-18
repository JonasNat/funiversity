package com.example.funiversity.services;

import com.example.funiversity.domain.Professor;
import com.example.funiversity.dto.CreateProfessorDto;
import com.example.funiversity.dto.ProfessorDto;
import com.example.funiversity.dto.UpdateProfessorDto;
import com.example.funiversity.exceptions.ProfessorAlreadyExistsException;
import com.example.funiversity.exceptions.ProfessorNotFoundException;
import com.example.funiversity.mappers.ProfessorMapper;
import com.example.funiversity.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository repository;
    private final ProfessorMapper mapper;

    public ProfessorService(ProfessorRepository repository, ProfessorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProfessorDto> findAllProfessors() {
        return mapper.toDto(repository.findAll());
    }

    public ProfessorDto findProfessorById(String id) {
        Professor professor = repository.findById(id).orElseThrow(() -> new ProfessorNotFoundException("Cant find Professor with id: " + id));
        return mapper.toDto(professor);
    }

    public ProfessorDto updateProfessor(String id, UpdateProfessorDto professorToUpdate) {
        Professor professor = repository.findById(id).orElseThrow(() -> new ProfessorNotFoundException("Couldn't find professor with id: " + id));
        professor.setFirstName(professorToUpdate.getFirstName());
        professor.setLastName(professorToUpdate.getLastName());
        return mapper.toDto(professor);
    }

    public ProfessorDto createProfessor(CreateProfessorDto professorToCreate) {
        if (repository.findAll().stream().anyMatch(professor -> professor.equals(mapper.toProfessor(professorToCreate)))) {
            throw new ProfessorAlreadyExistsException("Professor " + professorToCreate.getFirstName() + " " + professorToCreate.getLastName() + " already exists in DB");
        }
        return mapper.toDto(repository.create(mapper.toProfessor(professorToCreate)));
    }

    public void deleteProfessor(String id) {
        repository.delete(id);
    }
}
