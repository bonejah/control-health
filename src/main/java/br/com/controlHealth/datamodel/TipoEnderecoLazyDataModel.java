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

import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.model.TipoEndereco;

@Named
@ViewScoped
public class TipoEnderecoLazyDataModel extends LazyDataModel<TipoEndereco> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoEnderecoDAOImpl dao;

	@PostConstruct
	public void init() {
		this.setRowCount(this.dao.countAll());
	}

	@Override
	public List<TipoEndereco> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<TipoEndereco> listTipoEnderecos = null;

		listTipoEnderecos = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
				filters);

		if (filters != null && filters.size() > 0) {
			setRowCount(this.dao.countAllFiltered(filters));
		} else {
			this.setRowCount(this.dao.countAll());
			listTipoEnderecos = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
					new HashMap<String, Object>());
		}

		return listTipoEnderecos;
	}

	@Override
	public Object getRowKey(TipoEndereco TipoEndereco) {
		return TipoEndereco.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TipoEndereco getRowData(String rowKey) {
		List<TipoEndereco> lsTipoEnderecos = (List<TipoEndereco>) getWrappedData();

		for (TipoEndereco TipoEndereco : lsTipoEnderecos) {
			if (TipoEndereco.getId().toString().equals(rowKey)) {
				return TipoEndereco;
			}
		}

		return null;
	}

}
