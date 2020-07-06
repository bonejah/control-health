package br.com.controlHealth.datamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.controlHealth.dao.impl.ConsultaDAOImpl;
import br.com.controlHealth.model.Consulta;

@Named
@ViewScoped
public class ConsultaLazyDataModel extends LazyDataModel<Consulta> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ConsultaDAOImpl dao;
	
	@PostConstruct
	public void init() {
		this.setRowCount(this.dao.countAll());
	}
	
	@Override
	public List<Consulta> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Consulta> listConsultas = null;
		
		listConsultas = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
					filters);
		
		if (filters != null && filters.size() > 0) {
			setRowCount(this.dao.countAllFiltered(filters));
		} else {
			this.setRowCount(this.dao.countAll());
			listConsultas = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder), new HashMap<String, Object>());
		}
		
		return listConsultas;
	}

	@Override
	public Object getRowKey(Consulta consulta) {
		return consulta.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Consulta getRowData(String rowKey) {
		List<Consulta> lsConsultas = (List<Consulta>) getWrappedData();

		for (Consulta consulta : lsConsultas) {
			if (consulta.getId().toString().equals(rowKey)) {
				return consulta;
			}
		}

		return null;
	}
	
}
