package com.capgemini.online_store_spring_example.viewmodels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.Data;

@Data
public class ProdottoVm implements IViewModel{

	private Long id;
	
	@NotBlank
	@Length(min = EntityConstants.PRODOTTO_ENTITY_CODICE_LENGTH, 
	max = EntityConstants.PRODOTTO_ENTITY_CODICE_LENGTH)
	private String codice;
	
	@NotBlank
	@Length(max = EntityConstants.PRODOTTO_ENTITY_NOME_LENGTH)
	private String nome;
	
	@Length(max = EntityConstants.PRODOTTO_ENTITY_DESCRIZIONE_LENGTH)
	private String descrizione;

	@Min(value = 0)
	private Double prezzo;

	@NotNull
	private Long categoriaId;

	private String categoriaNome;
	
}
