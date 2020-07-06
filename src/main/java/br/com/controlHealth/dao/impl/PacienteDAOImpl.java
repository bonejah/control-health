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
import br.com.controlHealth.model.Paciente;

@Named
@RequestScoped
public class PacienteDAOImpl implements DAO<Paciente> {
	
	private static final long serialVersionUID = -5612627470587434327L;

	@Inject
	@EntityManagerControlHealthQualifier
	private EntityManager em;

	private SimpleDateFormat sdfParser = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sdfFormatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	@TransactionInterceptor
	public void save(Paciente paciente) {
		try {
			this.em.persist(paciente);
		} catch (Exception e) {
			System.err.println("Erro ao adicionar o registro - Erro: " + e.getMessage());
		}
	}

	@Override
	@TransactionInterceptor
	public void update(Paciente paciente) {
		try {
			this.em.merge(paciente);
		} catch (Exception e) {
			System.err.println("Erro ao atualizar o registro - Erro: " + e.getMessage());
		}
	}

	@Override
	@TransactionInterceptor
	public void delete(Paciente paciente) {
		try {
			this.em.remove(this.em.merge(paciente));
		} catch (Exception e) {
			System.err.println("Erro ao remover o registro - Erro: " + e.getMessage());
		}
	}

	@Override
	public List<Paciente> getAll() {
		List<Paciente> lista = null;

		try {
			CriteriaQuery<Paciente> query = this.em.getCriteriaBuilder().createQuery(Paciente.class);
			query.select(query.from(Paciente.class));
			lista = this.em.createQuery(query).getResultList();
		} catch (Exception e) {
			System.err.println("Erro ao listar todos os registros - Erro: " + e.getMessage());
		}

		return lista;
	}

	@Override
	public Paciente getById(Integer id) {
		Paciente paciente = null;

		try {
			paciente = this.em.find(Paciente.class, id);
		} catch (Exception e) {
			System.err.println("Erro ao buscar por id - Erro: " + e.getMessage());
		}
		
		return paciente;
	}

	@Override
	public List<Paciente> getListPaginate(int start, int size, String sortField, boolean asc,
			Map<String, Object> filters) {
		List<Paciente> list = null;

		try {
			CriteriaBuilder cb = this.em.getCriteriaBuilder();
			CriteriaQuery<Paciente> criteriaQuery = cb.createQuery(Paciente.class);
			Root<Paciente> root = criteriaQuery.from(Paciente.class);
			CriteriaQuery<Paciente> select = criteriaQuery.select(root);

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
						p = cb.equal(expr, value.toString().toLowerCase());
					} else if (field.equals("dataNascimento")) {
						Date dateParsed = sdfParser.parse((String) value);
						String dateFormatted = sdfFormatter.format(dateParsed);
						p = cb.equal(expr, dateFormatted);
					} else {
						p = cb.like(cb.lower(expr), "%" + value.toString().toLowerCase() + "%");
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

			TypedQuery<Paciente> query = this.em.createQuery(select);
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
			String sql = "select count(*) from Paciente paciente";
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
			Root<Paciente> root = criteriaQuery.from(Paciente.class);
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
						p = cb.equal(expr, value.toString().toLowerCase());
					} else if (field.equals("dataNascimento")) {
						Date dateParsed = sdfParser.parse((String) value);
						String dateFormatted = sdfFormatter.format(dateParsed);
						p = cb.equal(expr, dateFormatted);
					} else {
						p = cb.like(cb.lower(expr), "%" + value.toString().toLowerCase() + "%");
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
		}
		
		return count.intValue();
	}

}
