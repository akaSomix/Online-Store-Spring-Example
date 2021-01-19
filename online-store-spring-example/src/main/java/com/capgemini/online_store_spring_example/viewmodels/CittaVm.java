package com.capgemini.online_store_spring_example.viewmodels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
	 @Pattern(regexp = "[0-9]{"+ EntityConstants.CITTA_ENTITY_CAP_LENGTH +"}")
	 private String cap;

	 @NotBlank
	 @Length(max = EntityConstants.CITTA_ENTITY_STATO_LENGTH)
	 private String stato;

	 private Long provinciaId;
}
