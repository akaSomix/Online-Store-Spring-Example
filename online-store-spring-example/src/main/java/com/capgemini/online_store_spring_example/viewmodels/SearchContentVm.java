package com.capgemini.online_store_spring_example.viewmodels;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SearchContentVm implements IViewModel{
	
	@NotNull
	private String value;

	public SearchContentVm() {
		this.value = "";
	}
}
