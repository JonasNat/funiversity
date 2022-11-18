package com.example.funiversity.repositories;

import com.example.funiversity.domain.Course;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CourseRepository {
    private final Map<String, Course> courses = new HashMap<>();

    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courses.get(id));
    }

    public List<Course> findAll() {
        return courses.values().stream().toList();
    }

    public Course create(Course course) {
        courses.put(course.getId(), course);
        return course;
    }

    public void delete(String id) {
        courses.remove(id);
    }
}
