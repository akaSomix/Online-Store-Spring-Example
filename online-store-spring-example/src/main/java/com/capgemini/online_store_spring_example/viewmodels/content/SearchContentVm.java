package com.capgemini.online_store_spring_example.viewmodels.content;

import javax.validation.constraints.NotNull;

import com.capgemini.online_store_spring_example.viewmodels.IViewModel;

import lombok.Data;

@Data
public class SearchContentVm implements IViewModel{
	
	@NotNull
	private String value;

	public SearchContentVm() {
		this.value = "";
	}
}
