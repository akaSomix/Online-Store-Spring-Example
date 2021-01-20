package com.capgemini.online_store_spring_example.cart.items;

import lombok.Data;

@Data
public class CartRequest {
	
	private Long negozioId;
	
	private Long prodottoId;
	
	private String nome;
	
	private Double prezzo; 
	
	private Integer quantita;

	public CartRequest() {}
	
	public CartRequest(Long negozioId, Long prodottoId, String nome, Double prezzo, Integer quantita) {
		this.negozioId = negozioId;
		this.prodottoId = prodottoId;
		this.nome = nome;
		this.prezzo = prezzo;
		this.quantita = quantita;
	}
	
	
}
