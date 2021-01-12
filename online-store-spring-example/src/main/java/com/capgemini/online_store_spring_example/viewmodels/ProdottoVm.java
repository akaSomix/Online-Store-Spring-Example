package com.capgemini.online_store_spring_example.viewmodels;

import lombok.Data;

@Data
public class ProdottoVm implements IViewModel{

	private Long id;

	private String codice;
	
	private String nome;
	
	private String descrizione;

	private Double prezzo;

	private Long categoriaId;
	
	private String categoriaNome;
	
}
