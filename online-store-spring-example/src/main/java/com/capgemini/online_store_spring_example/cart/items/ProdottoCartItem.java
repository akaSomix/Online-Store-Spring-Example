package com.capgemini.online_store_spring_example.cart.items;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdottoCartItem {
	
	@EqualsAndHashCode.Include
	@Getter
	@Setter
	private Long prodottoId;
	
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private Double prezzo; 
	
	@Getter
	@Setter
	private Integer quantita;

	public ProdottoCartItem() {}
	
	public ProdottoCartItem(Long prodottoId, String nome, Double prezzo, Integer quantita) {
		this.prodottoId = prodottoId;
		this.nome = nome;
		this.prezzo = prezzo;
		this.quantita = quantita;
	}
	
	
	
}
