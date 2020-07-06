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

import br.com.controlHealth.dao.impl.MedicoDAOImpl;
import br.com.controlHealth.model.Medico;

@Named
@ViewScoped
public class MedicoLazyDataModel extends LazyDataModel<Medico> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MedicoDAOImpl dao;

	@PostConstruct
	public void init() {
		this.setRowCount(this.dao.countAll());
	}

	@Override
	public List<Medico> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Medico> listMedicos = null;

		listMedicos = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
				filters);

		if (filters != null && filters.size() > 0) {
			setRowCount(this.dao.countAllFiltered(filters));
		} else {
			this.setRowCount(this.dao.countAll());
			listMedicos = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
					new HashMap<String, Object>());
		}

		return listMedicos;
	}

	@Override
	public Object getRowKey(Medico Medico) {
		return Medico.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Medico getRowData(String rowKey) {
		List<Medico> lsMedicos = (List<Medico>) getWrappedData();

		for (Medico medico : lsMedicos) {
			if (medico.getId().toString().equals(rowKey)) {
				return medico;
			}
		}

		return null;
	}

}
