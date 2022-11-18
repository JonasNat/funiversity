package com.example.funiversity.dto;

public class CreateCourseDto {
    private String name;
    private int studyPoints;
    private String professorId;

    public String getName() {
        return name;
    }

    public int getStudyPoints() {
        return studyPoints;
    }

    public String getProfessorId() {
        return professorId;
    }
}
