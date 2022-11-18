package com.example.funiversity.repositories;

import com.example.funiversity.domain.Professor;
import com.example.funiversity.exceptions.ProfessorAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProfessorRepository {
    private final Map<String, Professor> professors = new HashMap<>();


    public Optional<Professor> findById(String id) {
        return Optional.ofNullable(professors.get(id));
    }

/*    public Optional<Professor> findByFirstNameLastName(String fullName) {
        professors.values().stream()
                .map(professor -> professor.getFirstName() + " " + professor.getLastName())
                .filter(name -> name.equals(fullName))

    }*/

    public List<Professor> findAll() {
        return professors.values().stream().toList();
    }


    public Professor create(Professor professor) {
        professors.put(professor.getId(), professor);
        return professor;
    }

    public void delete(String id) {
        professors.remove(id);
    }

}
