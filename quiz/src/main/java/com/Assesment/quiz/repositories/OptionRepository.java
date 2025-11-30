package com.Assesment.quiz.repositories;

import com.Assesment.quiz.Entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository <Option,Long> {
}
