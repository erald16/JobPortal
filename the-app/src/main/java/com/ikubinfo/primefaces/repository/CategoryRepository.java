package com.ikubinfo.primefaces.repository;

import java.util.List;

import com.ikubinfo.primefaces.model.Categories;
import com.ikubinfo.primefaces.model.request.CategoryListRequest;


public interface CategoryRepository {

	List<Categories> getAll(CategoryListRequest name);

	boolean save(Categories category);

	boolean create(Categories category);

	void delete(Categories category);
	
	public int getCategoryCount(CategoryListRequest request);
}