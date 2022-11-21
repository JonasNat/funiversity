package com.example.funiversity.controllers;

import com.example.funiversity.dto.CourseDto;
import com.example.funiversity.dto.CreateCourseDto;
import com.example.funiversity.dto.UpdateCourseDto;
import com.example.funiversity.dto.UpdateCourseNameDto;
import com.example.funiversity.exceptions.CourseAlreadyExistsException;
import com.example.funiversity.exceptions.CourseNotFoundException;
import com.example.funiversity.exceptions.ProfessorAlreadyExistsException;
import com.example.funiversity.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> findAllCourses() {
        return service.findAllCourses();
    }

    @GetMapping(params = "studyPoints", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> findAllCoursesByStudyPoints(@RequestParam(required = false) int studyPoints) {
        return service.findAllByStudyPoints(studyPoints);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDto findCourseById(@PathVariable String id) {
        return service.findCourseById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(@RequestBody CreateCourseDto courseToCreate) {
        return service.createCourse(courseToCreate);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDto updateCourse(@PathVariable String id, @RequestBody UpdateCourseDto courseToUpdate) {
        return service.updateCourse(id, courseToUpdate);
    }

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public CourseDto updateCourseName(@PathVariable String id, @RequestBody UpdateCourseNameDto courseToUpdate) {
            return service.updateCourseName(id, courseToUpdate);
    }


    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable String id) {
        service.deleteCourse(id);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    protected void courseNotFoundExistsException(CourseNotFoundException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    protected void courseAlreadyExistsException(CourseAlreadyExistsException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
    }
}
