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

import br.com.controlHealth.dao.impl.EnderecoDAOImpl;
import br.com.controlHealth.model.Endereco;

@Named
@ViewScoped
public class EnderecoLazyDataModel extends LazyDataModel<Endereco> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoDAOImpl dao;

	@PostConstruct
	public void init() {
		this.setRowCount(this.dao.countAll());
	}

	@Override
	public List<Endereco> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<Endereco> listEnderecos = null;

		listEnderecos = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
				filters);

		if (filters != null && filters.size() > 0) {
			setRowCount(this.dao.countAllFiltered(filters));
		} else {
			this.setRowCount(this.dao.countAll());
			listEnderecos = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
					new HashMap<String, Object>());
		}

		return listEnderecos;
	}

	@Override
	public Object getRowKey(Endereco Endereco) {
		return Endereco.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Endereco getRowData(String rowKey) {
		List<Endereco> lsEnderecos = (List<Endereco>) getWrappedData();

		for (Endereco Endereco : lsEnderecos) {
			if (Endereco.getId().toString().equals(rowKey)) {
				return Endereco;
			}
		}

		return null;
	}

}
