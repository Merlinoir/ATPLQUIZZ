package com.atplquiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atplquiz.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
}