package com.Assesment.quiz.serviceImpl;

import com.Assesment.quiz.Entity.Quiz;
import com.Assesment.quiz.repositories.QuizRepository;
import com.Assesment.quiz.service.QuizService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private final QuizRepository quizRepository;

    @Transactional
    @Override
    public Quiz createQuiz(Quiz quiz) {

        if(quiz.getQuestionList() != null){
            quiz.getQuestionList().forEach(q->{
                q.setQuiz(quiz);
                if (q.getOptions() != null){
                    q.getOptions().forEach(o->o.setQuestion(q));
                }
            });
        }
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuiz(long id) {
        return quizRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Quiz not Found"));

    }
}
