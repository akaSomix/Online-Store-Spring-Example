package com.capgemini.online_store_spring_example.cart;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.cart.items.CartRequest;
import com.capgemini.online_store_spring_example.cart.items.ProdottoCartItem;

@Service
public class CartService {

	private void updateTotale(CartEntity cart, Double priceToAdd) {
		cart.setTotale(cart.getTotale() + priceToAdd);
	}
	
	private void updateItemsCount(CartEntity cart, Integer quantita) {
		cart.setItemsCount(cart.getItemsCount() + quantita);
	}
		
	public void addToCart(CartEntity cart, CartRequest request) {
		// Modellazione parametri passati in request
		Long negozioId = request.getNegozioId();
		ProdottoCartItem prodotto = new ProdottoCartItem(request.getProdottoId(), request.getNome(),
															request.getPrezzo(), request.getQuantita());
		
		List<ProdottoCartItem> prodottoItems;
		
		if( cart.getNegozioItems().containsKey(negozioId)) {
			// Caso negozio gia esistente
			prodottoItems = cart.getNegozioItems().get(negozioId);
			
			if(prodottoItems.contains(prodotto)) {
				// Se il prodotto esiste nel carrello del negozio, aggiorna solo quantita
				for(ProdottoCartItem p : prodottoItems) 
					if(p.equals(prodotto)) {
						p.setQuantita(p.getQuantita() + prodotto.getQuantita());
						break;
					}
			}
			else {
				//Altrimenti aggiungi il prodotto intero
				prodottoItems.add(prodotto);
			}
		}
		else {
			// Creazione nuovo negozio
			prodottoItems = new LinkedList<>();	
			prodottoItems.add(prodotto);
			
			cart.getNegozioItems().put(negozioId, prodottoItems);
		}
		
		// Updates
		updateTotale(cart, request.getPrezzo());
		updateItemsCount(cart, request.getQuantita());
	}
	
	public void removeFromCart(CartEntity cart, CartRequest request) {
		// Ricava la lista di prodotti associata alla request
		List<ProdottoCartItem> prodotti = cart.getNegozioItems().get(request.getNegozioId());
		
		// Costruzione oggetto ProdottoCartItem ad hoc
		ProdottoCartItem prodotto = new ProdottoCartItem();
		prodotto.setProdottoId(request.getProdottoId());
		
		if(prodotti != null)prodotti.remove(prodotto);
	}
	
	public void emptyCart(CartEntity cart) {
		cart = new CartEntity();
	}
}
