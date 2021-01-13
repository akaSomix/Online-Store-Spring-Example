package com.capgemini.online_store_spring_example.viewmodels;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.Data;

@Data
public class NegozioVm implements IViewModel{

	private Long id;
	
	@NotBlank
	@Length(max = EntityConstants.NEGOZIO_ENTITY_NOME_LENGTH)
	private String nome;

	@NotBlank
	@Length(max = EntityConstants.NEGOZIO_ENTITY_INDIRIZZO_LENGTH)
	private String indirizzo;

	@NotNull
	private Long cittaId;
	
	private String cittaNome;
	
	private String cittaCap;
	
}
