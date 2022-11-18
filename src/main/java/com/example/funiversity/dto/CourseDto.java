package com.example.funiversity.dto;

public class CourseDto {
    private String id;
    private String name;
    private int studyPoints;
    private String professorId;

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

    public CourseDto setId(String id) {
        this.id = id;
        return this;
    }

    public CourseDto setName(String name) {
        this.name = name;
        return this;
    }

    public CourseDto setStudyPoints(int studyPoints) {
        this.studyPoints = studyPoints;
        return this;
    }

    public CourseDto setProfessorId(String professorId) {
        this.professorId = professorId;
        return this;
    }


}
