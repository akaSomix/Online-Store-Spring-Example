package com.capgemini.online_store_spring_example.viewmodels;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.Data;

@Data
public class CategoriaVm implements IViewModel{

	private Long id;
	
	@NotBlank
	@Length(max = EntityConstants.CATEGORIA_ENTITY_NOME_LENGTH)
	private String nome;
	
	@Length(max = EntityConstants.CATEGORIA_ENTITY_DESCRIZIONE_LENGTH)
	private String descrizione;
}
