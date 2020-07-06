package br.com.controlHealth.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface DAO<T> extends Serializable {

	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	public List<T> getAll();
	
	public T getById(Integer id);
	
	public List<T> getListPaginate(int start, int size, String sortField, boolean asc, Map<String, Object> filters);
	
	public int countAll();
	
	public int countAllFiltered(Map<String, Object> filters);
		
}
