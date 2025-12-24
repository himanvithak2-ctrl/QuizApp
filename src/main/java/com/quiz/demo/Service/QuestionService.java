package com.quiz.demo.Service;

import com.quiz.demo.dao.QuestionDao;
import com.quiz.demo.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Questions question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question failed to add", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> deleteQuestionById(Integer id) {
        try {
            if (!questionDao.existsById(id)) {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }

            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted Successfully", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to delete the question", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Questions> updateQuestion(Integer id, Questions updatedQuestion) {
        try {
            Questions existing = questionDao.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Question not found with id " + id));

            existing.setCategory(updatedQuestion.getCategory());
            existing.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            existing.setQuestionTitle(updatedQuestion.getQuestionTitle());
            existing.setOption1(updatedQuestion.getOption1());
            existing.setOption2(updatedQuestion.getOption2());
            existing.setOption3(updatedQuestion.getOption3());
            existing.setOption4(updatedQuestion.getOption4());
            existing.setRightAnswer(updatedQuestion.getRightAnswer());

            return new ResponseEntity<>(questionDao.save(existing), HttpStatus.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Questions(),HttpStatus.BAD_REQUEST);
    }
}


