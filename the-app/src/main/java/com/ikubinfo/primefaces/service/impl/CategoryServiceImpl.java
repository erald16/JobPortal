package com.ikubinfo.primefaces.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikubinfo.primefaces.model.Categories;
import com.ikubinfo.primefaces.model.request.CategoryListRequest;
import com.ikubinfo.primefaces.model.response.CategoryListResponse;
import com.ikubinfo.primefaces.repository.CategoryRepository;
import com.ikubinfo.primefaces.service.CategoryService;

@Service("categoryService")
class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Categories> getAll(CategoryListRequest name) {
		return categoryRepository.getAll(name);
	}

	@Override
	public boolean save(Categories category) {
		return categoryRepository.save(category);
	}

	@Override
	public boolean create(Categories category) {
		return categoryRepository.create(category);
	}

	@Override
	public void delete(Categories category) {
		categoryRepository.delete(category);
	}
	
	@Override
	public CategoryListResponse getCategory(CategoryListRequest request) {
		int totalCount = categoryRepository.getCategoryCount(request);
		List<Categories> category = categoryRepository.getAll(request);

		return new CategoryListResponse(totalCount, category);

	}

}
