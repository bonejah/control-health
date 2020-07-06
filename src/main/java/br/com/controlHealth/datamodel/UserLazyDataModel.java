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

import br.com.controlHealth.dao.impl.UserDAOImpl;
import br.com.controlHealth.model.User;

@Named
@ViewScoped
public class UserLazyDataModel extends LazyDataModel<User> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserDAOImpl dao;
	
	@PostConstruct
	public void init() {
		this.setRowCount(this.dao.countAll());
	}

	@Override
	public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		List<User> listUsers = null;
		
		listUsers = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder),
					filters);
		
		if (filters != null && filters.size() > 0) {
			setRowCount(this.dao.countAllFiltered(filters));
		} else {
			this.setRowCount(this.dao.countAll());
			listUsers = this.dao.getListPaginate(first, pageSize, sortField, SortOrder.ASCENDING.equals(sortOrder), new HashMap<String, Object>());
		}
		
		return listUsers;
	}

	@Override
	public Object getRowKey(User User) {
		return User.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getRowData(String rowKey) {
		List<User> lsUsers = (List<User>) getWrappedData();

		for (User User : lsUsers) {
			if (User.getId().toString().equals(rowKey)) {
				return User;
			}
		}

		return null;
	}
	
}
