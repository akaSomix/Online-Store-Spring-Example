package com.capgemini.online_store_spring_example.viewmodels;

import lombok.Data;

@Data
public class CategoriaVm implements IViewModel{

	private Long id;
	
	private String nome;
	
	private String descrizione;
}
