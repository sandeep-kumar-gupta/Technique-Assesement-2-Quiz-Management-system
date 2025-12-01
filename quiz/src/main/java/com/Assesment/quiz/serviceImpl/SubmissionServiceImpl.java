package com.Assesment.quiz.serviceImpl;

import com.Assesment.quiz.Entity.*;
import com.Assesment.quiz.repositories.QuizRepository;
import com.Assesment.quiz.repositories.SubmissionAnswerRepository;
import com.Assesment.quiz.repositories.SubmissionRepository;
import com.Assesment.quiz.service.SubmissionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {


    @Autowired
    private final QuizRepository quizRepository;
    @Autowired
    private final SubmissionRepository submissionRepository;
    @Autowired
    private final SubmissionAnswerRepository submissionAnswerRepository;

    @Transactional
    @Override
    public Submission submitQuiz(Long quizId, Map<Long, String> answers) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(()->new RuntimeException("quiz Not Found"));

        Submission submission = new Submission();
        submission.setQuiz(quiz);
        submission.setTotalQuestions(quiz.getQuestionList().size());
        submission.setAnswers(answers);

        submission = submissionRepository.save(submission);

        int score = 0;
        for(Question question : quiz.getQuestionList()){
            String givenAnswer = answers.get(question.getId());
            SubmissionAnswer sa = new SubmissionAnswer();
            sa.setSubmission(submission);
            sa.setQuestion(question);
            sa.setAnswerText(givenAnswer);
            submissionAnswerRepository.save(sa);

            if(question.getType()== QuestionType.MCQ || question.getType()==QuestionType.TRUE_FALSE){
                boolean correct = question.getOptions().stream().anyMatch(option ->option.isCorrect() && option.getText().equalsIgnoreCase(givenAnswer));
                if (correct){
                    score++;
                }
            }

        }
        submission.setScore(score);
        return submissionRepository.save(submission);
    }

    @Override
    public Submission getSubmission(Long id) {
        return submissionRepository.findById(id).orElseThrow(()->new RuntimeException("Submission not found"));
    }
}
