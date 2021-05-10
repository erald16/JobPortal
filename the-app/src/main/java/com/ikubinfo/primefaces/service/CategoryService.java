package com.ikubinfo.primefaces.service;

import java.util.List;

import com.ikubinfo.primefaces.model.Categories;
import com.ikubinfo.primefaces.model.request.CategoryListRequest;
import com.ikubinfo.primefaces.model.response.CategoryListResponse;

public interface CategoryService {

	List<Categories> getAll(CategoryListRequest name);

	boolean save(Categories category);

	boolean create(Categories category);

	void delete(Categories category);
	
	CategoryListResponse getCategory (CategoryListRequest request);

}