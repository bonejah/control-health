package br.com.controlHealth.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.controlHealth.dao.DAO;
import br.com.controlHealth.dao.EntityManagerControlHealthQualifier;
import br.com.controlHealth.dao.TransactionInterceptor;
import br.com.controlHealth.model.Consulta;

@Named
@RequestScoped
public class ConsultaDAOImpl implements DAO<Consulta> {

	private static final long serialVersionUID = -8030963203978196700L;
	
	@Inject
	@EntityManagerControlHealthQualifier
	private EntityManager em;

	private SimpleDateFormat sdfParser = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sdfFormatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000000");

	@Override
	@TransactionInterceptor
	public void save(Consulta consulta) {
		try {
			this.em.persist(consulta);
		} catch (Exception e) {
			System.err.println("Erro ao salvar o registro - Erro: " + e.getMessage());
		} 
	}

	@Override
	@TransactionInterceptor
	public void update(Consulta consulta) {
		try {
			this.em.merge(consulta);
		} catch (Exception e) {
			System.err.println("Erro ao atualizar o registro - Erro: " + e.getMessage());
		}
	}

	@Override
	@TransactionInterceptor
	public void delete(Consulta consulta) {
		try {
			this.em.remove(this.em.merge(consulta));
		} catch (Exception e) {
			System.err.println("Erro ao remover o registro - Erro: " + e.getMessage());
		}
	}

	@Override
	public List<Consulta> getAll() {
		List<Consulta> lista = null;

		try {
			CriteriaQuery<Consulta> query = this.em.getCriteriaBuilder().createQuery(Consulta.class);
			query.select(query.from(Consulta.class));
			lista = this.em.createQuery(query).getResultList();
		} catch (Exception e) {
			System.err.println("Erro ao listar todos os registros - Erro: " + e.getMessage());
		} 
		
		return lista;
	}

	@Override
	public Consulta getById(Integer id) {
		Consulta consulta = null;

		try {
			consulta = this.em.find(Consulta.class, id);
		} catch (Exception e) {
			System.err.println("Erro ao buscar por id - Erro: " + e.getMessage());
		} 
		
		return consulta;
	}
	
	public Consulta getDadosDaConsulta(Integer id) {
		Consulta consulta = null;
		
		try {
			String sql = "select c from Consulta c JOIN FETCH c.dadosDaConsulta d where c.dadosDaConsulta.id = d.id and c.id = :id";
			Query query = this.em.createQuery(sql);
			consulta = (Consulta) query.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			System.err.println("Erro ao contar todos - Erro: " + e.getMessage());
		} 

		return consulta;
	}

	@Override
	public List<Consulta> getListPaginate(int start, int size, String sortField, boolean asc,
			Map<String, Object> filters) {
		List<Consulta> list = null;

		try {
			CriteriaBuilder cb = this.em.getCriteriaBuilder();
			CriteriaQuery<Consulta> criteriaQuery = cb.createQuery(Consulta.class);
			Root<Consulta> root = criteriaQuery.from(Consulta.class);
			CriteriaQuery<Consulta> select = criteriaQuery.select(root);
			
			if (filters != null && filters.size() > 0) {
				List<Predicate> predicates = new ArrayList<>();

				for (Map.Entry<String, Object> entry : filters.entrySet()) {
					String field = entry.getKey();
					Object value = entry.getValue();

					if (value == null) {
						continue;
					}

					Expression<String> expr = root.get(field).as(String.class);
					Predicate p = null;

					if (field.equals("id")) {
						p = cb.equal(root.get(field), value.toString());
					} else if (field.equals("dataConsulta") || field.equals("dataRetorno")) {
						Date dateParsed = sdfParser.parse((String) value);
						String dateFormatted = sdfFormatter.format(dateParsed);
						p = cb.equal(expr, dateFormatted);
					} else {
						Expression<Integer> exprInteger = root.get(field).get("id").as(Integer.class); 
						p = cb.equal(exprInteger, value.toString());
					}

					predicates.add(p);

					if (asc) {
						criteriaQuery.orderBy(cb.asc(root.get(field)));
					} else {
						criteriaQuery.orderBy(cb.desc(root.get(field)));
					}
				}

				if (predicates.size() > 0) {
					criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				}
			} 
			
			if (sortField != null) {
				if (asc) {
					criteriaQuery.orderBy(cb.asc(root.get(sortField)));
				} else {
					criteriaQuery.orderBy(cb.desc(root.get(sortField)));
				}
			}

			TypedQuery<Consulta> query = this.em.createQuery(select);
			query.setFirstResult(start);
			query.setMaxResults(size);
			list = query.getResultList();
		} catch (Exception e) {
			System.err.println("Erro ao listar paginada - Erro: " + e.getMessage());
		} 
		
		return list;
	}
	
	@Override
	public int countAll() {
		long total = 0;

		try {
			String sql = "select count(*) from Consulta consulta";
			Query query = this.em.createQuery(sql);
			total = (long) query.getSingleResult();

		} catch (Exception e) {
			System.err.println("Erro ao contar todos - Erro: " + e.getMessage());
		}
		
		return (int) total;
	}

	@Override
	public int countAllFiltered(Map<String, Object> filters) {
		Long count = 0L;

		try {
			CriteriaBuilder cb = this.em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
			Root<Consulta> root = criteriaQuery.from(Consulta.class);
			CriteriaQuery<Long> select = criteriaQuery.select(cb.count(root));

			if (filters != null && filters.size() > 0) {
				List<Predicate> predicates = new ArrayList<>();

				for (Map.Entry<String, Object> entry : filters.entrySet()) {
					String field = entry.getKey();
					Object value = entry.getValue();

					if (value == null) {
						continue;
					}

					Expression<String> expr = root.get(field).as(String.class);
					Predicate p = null;

					if (field.equals("id")) {
						p = cb.equal(root.get(field), value.toString());
					} else if (field.equals("dataConsulta") || field.equals("dataRetorno")) {
						Date dateParsed = sdfParser.parse((String) value);
						String dateFormatted = sdfFormatter.format(dateParsed);
						p = cb.equal(expr, dateFormatted);
					} else {
						Expression<Integer> exprInteger = root.get(field).get("id").as(Integer.class); 
						p = cb.equal(exprInteger, value.toString());
					}
					
					
					predicates.add(p);
				}

				if (predicates.size() > 0) {
					criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				}
			}

			count = this.em.createQuery(select).getSingleResult();

		} catch (Exception e) {
			System.err.println("Erro ao contar todos filtrado - Erro: " + e.getMessage());
			e.printStackTrace();
		} 
		
		return count.intValue();
	}

}
