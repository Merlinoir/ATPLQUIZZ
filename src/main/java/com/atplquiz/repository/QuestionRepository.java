package com.atplquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atplquiz.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
}