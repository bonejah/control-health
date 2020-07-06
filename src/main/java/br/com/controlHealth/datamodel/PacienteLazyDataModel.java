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

import br.com.controlHealth.dao.impl.PacienteDAOImpl;
import br.com.controlHealth.model.Paciente;

@Named
@ViewScoped
public class PacienteLazyDataModel extends LazyDataModel<Paciente> {

	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteDAOImpl dao;

	@PostConstruct
	public void init() {
		this.setRowCount(this.dao.countAll());
	}

	@Override
	public List<Paciente> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Paciente> listPacientes = null;

		listPacientes = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
				filters);

		if (filters != null && filters.size() > 0) {
			setRowCount(this.dao.countAllFiltered(filters));
		} else {
			this.setRowCount(this.dao.countAll());
			listPacientes = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
					new HashMap<String, Object>());
		}

		return listPacientes;
	}

	@Override
	public Object getRowKey(Paciente Paciente) {
		return Paciente.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Paciente getRowData(String rowKey) {
		List<Paciente> lsPacientes = (List<Paciente>) getWrappedData();

		for (Paciente paciente : lsPacientes) {
			if (paciente.getId().toString().equals(rowKey)) {
				return paciente;
			}
		}

		return null;
	}

}
