package com.learn.repository;

import com.learn.entity.Answer;
import com.learn.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerQuestionRepository extends JpaRepository<Answer,Long> {
}
