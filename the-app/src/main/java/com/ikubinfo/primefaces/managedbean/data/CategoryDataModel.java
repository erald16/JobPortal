package com.ikubinfo.primefaces.managedbean.data;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.ikubinfo.primefaces.model.Categories;
import com.ikubinfo.primefaces.model.request.CategoryListRequest;
import com.ikubinfo.primefaces.model.response.CategoryListResponse;
import com.ikubinfo.primefaces.service.CategoryService;

@ManagedBean
@ViewScoped
public class CategoryDataModel extends LazyDataModel<Categories> {

	private static final long serialVersionUID = -1880912850632185847L;

	private String name;
	private String description;
	private boolean active;
	private Date createdDate;
	private Date lastUpdate;
	private int first;
	private int pageSize;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	@Override
	public List<Categories> load(int first, int pageSize, Map<String, SortMeta> sortMeta,
			Map<String, FilterMeta> filterMeta) {
		this.first = first;
		this.pageSize = pageSize;

		CategoryListRequest request = new CategoryListRequest(name, description, active, createdDate, lastUpdate, first,
				pageSize);

		CategoryListResponse response = categoryService.getCategory(request);

		this.setRowCount(response.getTotalRow());

		return response.getCategories();
	}

	public void filter() {

		CategoryListRequest request = new CategoryListRequest(name, description, active, createdDate, lastUpdate, first,
				pageSize);

		CategoryListResponse response = categoryService.getCategory(request);

		this.setRowCount(response.getTotalRow());
		this.setWrappedData(response.getCategories());

	}

	public void refresh() {
		this.name = null;

		CategoryListResponse response = categoryService.getCategory(
				new CategoryListRequest(name, description, active, createdDate, lastUpdate, first, pageSize));

		this.setRowCount(response.getTotalRow());
		this.setWrappedData(response.getCategories());

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
