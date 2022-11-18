package com.example.funiversity.services;

import com.example.funiversity.domain.Course;
import com.example.funiversity.dto.CourseDto;
import com.example.funiversity.dto.CreateCourseDto;
import com.example.funiversity.dto.UpdateCourseDto;
import com.example.funiversity.exceptions.CourseAlreadyExistsException;
import com.example.funiversity.exceptions.CourseNotFoundException;
import com.example.funiversity.exceptions.ProfessorNotFoundException;
import com.example.funiversity.mappers.CourseMapper;
import com.example.funiversity.repositories.CourseRepository;
import com.example.funiversity.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final CourseMapper mapper;

    public CourseService(CourseRepository courseRepository, ProfessorRepository professorRepository, CourseMapper mapper) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.mapper = mapper;
    }

    public List<CourseDto> findAllCourses() {
        return mapper.toDto(courseRepository.findAll());
    }

    public List<CourseDto> findAllByStudyPoints(Integer studyPoints) {
        return mapper.toDto(courseRepository.findAll()
                .stream()
                .filter(course -> course.getStudyPoints() == studyPoints)
                .toList());
    }

    public CourseDto findCourseById(String id) {
        return mapper.toDto(courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Couldn't find course with id: " + id)));
    }

    public CourseDto createCourse(CreateCourseDto courseToCreate) {
        if (courseRepository.findAll().stream().anyMatch(course -> course.equals(mapper.toCourse(courseToCreate)))) {
            throw new CourseAlreadyExistsException("Course " + courseToCreate.getName() + " already exists");
        }
        professorRepository.findById(courseToCreate.getProfessorId())
                .orElseThrow(() -> new ProfessorNotFoundException("Couldn't find professor with id: " + courseToCreate.getProfessorId()));
        return mapper.toDto(courseRepository.create(mapper.toCourse(courseToCreate)));
    }

    public CourseDto updateCourse(String id, UpdateCourseDto courseToUpdate) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Couldn't find course with id: " + id));
        course.setName(courseToUpdate.getName());
        course.setStudyPoints(courseToUpdate.getStudyPoints());
        course.setProfessorId(courseToUpdate.getProfessorId());
        return mapper.toDto(course);
    }

    public void deleteCourse(String id) {
        courseRepository.delete(id);
    }
}
