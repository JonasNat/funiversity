package com.example.funiversity.domain;

import java.util.Objects;
import java.util.UUID;

public class Course {
    private final String id;
    private String name;
    private int studyPoints;
    private String professorId;
    private static final int MIN_STUDY_POINTS = 1;
    private static final int MAX_STUDY_POINTS = 6;

    public Course(String name, int studyPoints, String professorId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        setStudyPoints(studyPoints);
        this.professorId = professorId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStudyPoints() {
        return studyPoints;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudyPoints(int studyPoints) {
        this.studyPoints = Math.min(MAX_STUDY_POINTS, Math.max(MIN_STUDY_POINTS, studyPoints));
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", studyPoints=" + studyPoints +
                ", professorId='" + professorId + '\'' +
                '}';
    }
}
