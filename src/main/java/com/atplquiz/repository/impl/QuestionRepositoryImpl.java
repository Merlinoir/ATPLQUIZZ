package com.atplquiz.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class QuestionRepositoryImpl {
	
	@PersistenceContext
	protected EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
}