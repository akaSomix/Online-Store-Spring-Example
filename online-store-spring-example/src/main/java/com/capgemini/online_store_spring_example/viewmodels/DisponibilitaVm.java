package com.capgemini.online_store_spring_example.viewmodels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DisponibilitaVm implements IViewModel{
	
	@NotNull
	private Long negozioId;
	
	@NotNull
	private Long prodottoId;
	
	@NotNull
	@Min(value = 0)
	private Integer quantita;
}
