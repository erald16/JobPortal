package com.ikubinfo.primefaces.model.response;

import java.util.List;

import com.ikubinfo.primefaces.model.User;

public class UserListResponse {

	private int totalRow;
	private List<User> users;

	public UserListResponse(int totalRow, List<User> users) {
		super();
		this.totalRow = totalRow;
		this.users = users;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "UserListResponse [totalRow=" + totalRow + ", users=" + users + "]";
	}

}