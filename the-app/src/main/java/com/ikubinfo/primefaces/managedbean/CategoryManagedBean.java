package com.ikubinfo.primefaces.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.ikubinfo.primefaces.managedbean.data.CategoryDataModel;
import com.ikubinfo.primefaces.model.Categories;
import com.ikubinfo.primefaces.service.CategoryService;
import com.ikubinfo.primefaces.util.Messages;

@ManagedBean
@ViewScoped
public class CategoryManagedBean implements Serializable {
	private static final long serialVersionUID = 3800933422824282320L;
	private Categories category;

	private List<Categories> categories;
	private String name;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;
	
	@ManagedProperty(value = "#{categoryDataModel}")
	private CategoryDataModel categoryDataModel;

	@ManagedProperty(value = "#{messages}")
	private Messages messages;

	@PostConstruct
	public void init() {
		categories = categoryDataModel.load(0, 10, null, null);
		categories = categoryService.getAll(null);
		category = new Categories();

	}
	
	public void getAll() {
		categories = categoryService.getAll(null);
	}
	
	public void insert() {
		Categories toAdd = new Categories();
		toAdd.setName(name);

		if (categoryService.create(toAdd)) {
			messages.showInfoMessage("Categories was added successfully");
			getAll();
		}

	}

	public void save() {
		if (categoryService.save(category)) {
			getAll();
			messages.showInfoMessage("Categories updated successfully");

		}
		category = new Categories();

	}
	
	public void delete() {
		categoryService.delete(category);
		categories = categoryService.getAll(null);
		messages.showInfoMessage("Deleted");

	}
	
	

	public CategoryDataModel getCategoryDataModel() {
		return categoryDataModel;
	}

	public void setCategoryDataModel(CategoryDataModel categoryDataModel) {
		this.categoryDataModel = categoryDataModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories Categories) {
		this.category = Categories;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

}
