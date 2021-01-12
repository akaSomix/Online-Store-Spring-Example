package com.capgemini.online_store_spring_example.entities.composite_keys;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class DisponibilitaCompositeKey implements Serializable{
	
	private static final long serialVersionUID = -5318909484327659726L;

	@Getter
	@Setter
	private Long negozio;
	
	@Getter
	@Setter
	private Long prodotto;
}
