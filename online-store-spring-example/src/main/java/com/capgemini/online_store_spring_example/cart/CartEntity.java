package com.capgemini.online_store_spring_example.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.online_store_spring_example.cart.items.ProdottoCartItem;

import lombok.Data;

@Data
public class CartEntity {

	private Map<Long, List<ProdottoCartItem>> negozioItems;
	
	private Double totale;
	
	private Integer itemsCount;
	
	public CartEntity() {
		this.negozioItems = new HashMap<>();
		this.totale = 0.;
		this.itemsCount = 0;
	}
}
