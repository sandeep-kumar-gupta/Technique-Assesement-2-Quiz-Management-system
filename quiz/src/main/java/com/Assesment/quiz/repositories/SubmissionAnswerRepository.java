package com.Assesment.quiz.repositories;

import com.Assesment.quiz.Entity.SubmissionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionAnswerRepository extends JpaRepository<SubmissionAnswer,Long> {
}
