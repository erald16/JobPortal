package com.ikubinfo.primefaces.model.response;

import java.util.List;

import com.ikubinfo.primefaces.model.Categories;

public class CategoryListResponse {

	private int totalRow;
	private List<Categories> categories;

	public CategoryListResponse(int totalRow, List<Categories> category) {
		super();
		this.totalRow = totalRow;
		this.categories = category;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}
	
}