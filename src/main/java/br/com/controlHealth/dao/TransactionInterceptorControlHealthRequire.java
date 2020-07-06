package br.com.controlHealth.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@TransactionInterceptor
public class TransactionInterceptorControlHealthRequire implements Serializable {

	private static final long serialVersionUID = -4582488621226062946L;

	@Inject
	@EntityManagerControlHealthQualifier
	private EntityManager em;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction transaction = em.getTransaction();
		
		try {
			if (!transaction.isActive()) {
				transaction.begin();
			}
			
			return context.proceed();
		} catch (Exception e) {
			System.err.println("Call transaction on method: " + e);
			
			if(transaction != null) {
				transaction.rollback();
			}
			
			throw e;
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.commit();
			}
		}
	}
	
}
