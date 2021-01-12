package com.capgemini.online_store_spring_example.viewmodels;

import lombok.Data;

@Data
public class CittaVm implements IViewModel{

	 private Long id;
	
	 private String nome;
	 
	 private String cap;

	 private String stato;

	 private Long provinciaId;
}
