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

import br.com.controlHealth.dao.impl.TipoEspecialidadeDAOImpl;
import br.com.controlHealth.model.TipoEspecialidade;

@Named
@ViewScoped
public class TipoEspecialidadeLazyDataModel extends LazyDataModel<TipoEspecialidade> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoEspecialidadeDAOImpl dao;

	@PostConstruct
	public void init() {
		this.setRowCount(this.dao.countAll());
	}

	@Override
	public List<TipoEspecialidade> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<TipoEspecialidade> listTipoEspecialidades = null;

		listTipoEspecialidades = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
				filters);

		if (filters != null && filters.size() > 0) {
			setRowCount(this.dao.countAllFiltered(filters));
		} else {
			this.setRowCount(this.dao.countAll());
			listTipoEspecialidades = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
					new HashMap<String, Object>());
		}

		return listTipoEspecialidades;
	}

	@Override
	public Object getRowKey(TipoEspecialidade TipoEspecialidade) {
		return TipoEspecialidade.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TipoEspecialidade getRowData(String rowKey) {
		List<TipoEspecialidade> lsTipoEspecialidades = (List<TipoEspecialidade>) getWrappedData();

		for (TipoEspecialidade tipoEspecialidade : lsTipoEspecialidades) {
			if (tipoEspecialidade.getId().toString().equals(rowKey)) {
				return tipoEspecialidade;
			}
		}

		return null;
	}

}
