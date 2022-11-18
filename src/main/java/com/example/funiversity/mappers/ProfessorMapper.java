package com.example.funiversity.mappers;

import com.example.funiversity.domain.Professor;
import com.example.funiversity.dto.CreateProfessorDto;
import com.example.funiversity.dto.ProfessorDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfessorMapper {
    public ProfessorDto toDto(Professor professor) {
        return new ProfessorDto()
                .setId(professor.getId())
                .setFirstName(professor.getFirstName())
                .setLastName(professor.getLastName());
    }

    public List<ProfessorDto> toDto(List<Professor> professors) {
        return professors.stream().map(this::toDto).toList();
    }

    public Professor toProfessor(CreateProfessorDto professorToCreate) {
        return new Professor(professorToCreate.getFirstName(), professorToCreate.getLastName());
    }
}
