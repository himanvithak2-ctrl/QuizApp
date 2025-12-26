package com.quiz.demo.dao;

import com.quiz.demo.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);

    @Query(value = "select * from questions q where q.category=:category ORDER BY RANDOM() lIMIT :numQ",nativeQuery = true)
    List<Questions> findRandomQuestionsByCategory(String category, int numQ);
}
