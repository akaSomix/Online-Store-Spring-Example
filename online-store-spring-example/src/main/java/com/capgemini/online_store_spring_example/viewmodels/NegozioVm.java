package com.capgemini.online_store_spring_example.viewmodels;

import lombok.Data;

@Data
public class NegozioVm implements IViewModel{

	private Long id;

	private String nome;

	private String indirizzo;

	private Long cittaId;
	
	private String cittaNome;
	
	private String cittaCap;
	
}
