package com.example.funiversity.dto;

public class UpdateCourseDto {
    private String name;
    private int StudyPoints;
    private String professorId;

    public String getName() {
        return name;
    }

    public int getStudyPoints() {
        return StudyPoints;
    }

    public String getProfessorId() {
        return professorId;
    }
}
