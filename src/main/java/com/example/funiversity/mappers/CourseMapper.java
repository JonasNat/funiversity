package com.example.funiversity.mappers;

import com.example.funiversity.domain.Course;
import com.example.funiversity.dto.CourseDto;
import com.example.funiversity.dto.CreateCourseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {
    public CourseDto toDto(Course course) {
        return new CourseDto()
                .setId(course.getId())
                .setName(course.getName())
                .setStudyPoints(course.getStudyPoints())
                .setProfessorId(course.getProfessorId());
    }

    public List<CourseDto> toDto(List<Course> courses) {
        return courses.stream().map(this::toDto).toList();
    }

    public Course toCourse(CreateCourseDto courseToCreate) {
        return new Course(courseToCreate.getName(), courseToCreate.getStudyPoints(), courseToCreate.getProfessorId());
    }
}
