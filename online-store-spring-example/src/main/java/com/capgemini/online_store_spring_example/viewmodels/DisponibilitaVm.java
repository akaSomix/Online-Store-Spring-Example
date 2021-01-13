package com.capgemini.online_store_spring_example.viewmodels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DisponibilitaVm implements IViewModel{

	private Long negozioId;
	
	private Long prodottoId;
	
	@NotBlank
	@Min(value = 0)
	private Integer quantita;
}
