package com.icbank.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class BaseService {

	@PersistenceContext
	private EntityManager em;

	protected EntityManager getEm() {
		return em;
	}

	protected void setEm(EntityManager em) {
		this.em = em;
	}
}
