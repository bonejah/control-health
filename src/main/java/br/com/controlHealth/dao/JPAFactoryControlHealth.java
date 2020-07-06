package br.com.controlHealth.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAFactoryControlHealth implements Serializable {

	private static final long serialVersionUID = -3853004272365066412L;

	private static EntityManagerFactory emf;
	
	@PostConstruct
	public void loadEntityManagerFactory() {
		this.emf = Persistence.createEntityManagerFactory("controlHealth");
	}
	
	@PreDestroy
	public void preDestroy() {
		if (this.emf.isOpen()) {
			this.emf.close();
		}
	}
	
	@Produces
	@RequestScoped
	@EntityManagerControlHealthQualifier
	public EntityManager createEntityManager() {
		return this.emf.createEntityManager();
	}

	public void close(@Disposes @EntityManagerControlHealthQualifier EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}
	
}
