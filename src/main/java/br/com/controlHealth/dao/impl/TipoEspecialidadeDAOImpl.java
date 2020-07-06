package br.com.controlHealth.dao.impl;

import java.util.ArrayList;
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
import br.com.controlHealth.dao.JPAFactoryControlHealth;
import br.com.controlHealth.dao.TransactionInterceptor;
import br.com.controlHealth.model.TipoEspecialidade;

@Named
@RequestScoped
public class TipoEspecialidadeDAOImpl implements DAO<TipoEspecialidade> {

	private static final long serialVersionUID = 3027562021595457461L;

	@Inject
	@EntityManagerControlHealthQualifier	
	private EntityManager em;
	
	@Override
	@TransactionInterceptor
	public void save(TipoEspecialidade tipoEspecialidade) {
		try {
			this.em.persist(tipoEspecialidade);
		} catch (Exception e) {
			System.err.println("Erro ao salvar o registro - Erro: " + e.getMessage());
		}
	}

	@Override
	@TransactionInterceptor
	public void update(TipoEspecialidade tipoEspecialidade) {
		try {
			this.em.merge(tipoEspecialidade);
		} catch (Exception e) {
			System.err.println("Erro ao atualizar o registro - Erro: " + e.getMessage());
		}
	}

	@Override
	@TransactionInterceptor
	public void delete(TipoEspecialidade tipoEspecialidade) {
		try {
			this.em.remove(this.em.merge(tipoEspecialidade));
		} catch (Exception e) {
			System.err.println("Erro ao remover o registro - Erro: " + e.getMessage());
		}
	}

	@Override
	public List<TipoEspecialidade> getAll() {
		List<TipoEspecialidade> lista = null;

		try {
			CriteriaQuery<TipoEspecialidade> query = this.em.getCriteriaBuilder().createQuery(TipoEspecialidade.class);
			query.select(query.from(TipoEspecialidade.class));
			lista = this.em.createQuery(query).getResultList();
		} catch (Exception e) {
			System.err.println("Erro ao listar todos os registros - Erro: " + e.getMessage());
		}

		return lista;
	}

	@Override
	public TipoEspecialidade getById(Integer id) {
		TipoEspecialidade tipoEspecialidade = null;

		try {
			tipoEspecialidade = this.em.find(TipoEspecialidade.class, id);
		} catch (Exception e) {
			System.err.println("Erro ao buscar por id - Erro: " + e.getMessage());
		}

		return tipoEspecialidade;
	}

	@Override
	public List<TipoEspecialidade> getListPaginate(int start, int size, String sortField, boolean asc,
			Map<String, Object> filters) {
		List<TipoEspecialidade> list = null;

		try {
			CriteriaBuilder cb = this.em.getCriteriaBuilder();
			CriteriaQuery<TipoEspecialidade> criteriaQuery = cb.createQuery(TipoEspecialidade.class);
			Root<TipoEspecialidade> root = criteriaQuery.from(TipoEspecialidade.class);
			CriteriaQuery<TipoEspecialidade> select = criteriaQuery.select(root);

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
						p = cb.equal(expr,  value.toString().toLowerCase());
					} else {
						p = cb.like(cb.lower(expr),  "%" + value.toString().toLowerCase() + "%");
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

			TypedQuery<TipoEspecialidade> query = this.em.createQuery(select);
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
			String sql = "select count(*) from TipoEspecialidade tipoEspecialidade";
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
			Root<TipoEspecialidade> root = criteriaQuery.from(TipoEspecialidade.class);
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
						p = cb.equal(expr,  value.toString().toLowerCase());
					} else {
						p = cb.like(cb.lower(expr),  "%" + value.toString().toLowerCase() + "%");
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
