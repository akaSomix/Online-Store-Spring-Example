package com.capgemini.online_store_spring_example.viewmodels;

import lombok.Data;

@Data
public class DisponibilitaVm implements IViewModel{

	private Long negozioId;
	
	private Long prodottoId;

	private Integer quantita;
}
