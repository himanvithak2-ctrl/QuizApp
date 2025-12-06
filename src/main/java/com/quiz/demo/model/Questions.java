package com.quiz.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Questions {

    @Id
    private int id;
    private String category;
    private String difficulty_level;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String question_title;
    private String right_answer;
}
