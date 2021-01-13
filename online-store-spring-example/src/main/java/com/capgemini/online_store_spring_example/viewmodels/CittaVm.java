package com.capgemini.online_store_spring_example.viewmodels;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.Data;

@Data
public class CittaVm implements IViewModel{

	 private Long id;
	
	 @NotBlank
	 @Length(max = EntityConstants.CITTA_ENTITY_NOME_LENGTH)
	 private String nome;
	 
	 @NotBlank
	 @Length(min = EntityConstants.CITTA_ENTITY_CAP_LENGTH, max = EntityConstants.CITTA_ENTITY_CAP_LENGTH)
	 private String cap;

	 @NotBlank
	 @Length(max = EntityConstants.CITTA_ENTITY_STATO_LENGTH)
	 private String stato;

	 private Long provinciaId;
}
