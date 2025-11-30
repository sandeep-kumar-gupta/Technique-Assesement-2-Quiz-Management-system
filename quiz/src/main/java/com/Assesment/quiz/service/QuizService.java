package com.Assesment.quiz.service;

import com.Assesment.quiz.Entity.Quiz;

public interface QuizService {
     Quiz createQuiz(Quiz quiz);
     Quiz getQuiz(long id);
}
