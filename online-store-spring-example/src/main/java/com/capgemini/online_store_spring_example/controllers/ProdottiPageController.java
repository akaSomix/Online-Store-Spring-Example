package com.capgemini.online_store_spring_example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.servicefacades.CategoriaServiceFacade;
import com.capgemini.online_store_spring_example.servicefacades.DisponibilitaServiceFacade;
import com.capgemini.online_store_spring_example.servicefacades.ProdottoServiceFacade;
import com.capgemini.online_store_spring_example.viewmodels.CategoriaVm;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;
import com.capgemini.online_store_spring_example.viewmodels.ProdottoVm;
import com.capgemini.online_store_spring_example.viewmodels.SearchContentVm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProdottiPageController {
	
	@Autowired
	public ProdottoServiceFacade prodottoService;
	
	@Autowired
	public DisponibilitaServiceFacade disponibilitaService;
	
	@Autowired
	public CategoriaServiceFacade categoriaService;

	@RequestMapping(value = "/prodotti", method = RequestMethod.GET)
	public String showProdottiByName(@ModelAttribute SearchContentVm searchContent,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()){
			//TODO Handle Error
			log.error("Binding Error [" + bindingResult.toString() + "] on class " + this.getClass().getName() + " using GET /prodotti");
			return "index";
        }
		
		// retrieve VMs
		List<ProdottoVm> prodotti = prodottoService.findByNomeStartingWith(searchContent.getValue());
		
		//update model and redirect
		model.addAttribute("prodotti", prodotti);
		
		//Aggiungi il prossimo elemento di ricerca
		model.addAttribute("searchContent", new SearchContentVm());
		
		return "lista_prodotti_page";
	}
	
	@RequestMapping(value = "/prodotti/{codice}", method = RequestMethod.GET)
	public String showProdottoByCodice(@PathVariable("codice") String codice, Model model) {
		
		// Prodotto Selezionato
		ProdottoVm prodotto = prodottoService.findByCodice(codice);
		model.addAttribute("prodottoCurrent", prodotto);
		
		// Aggiunge i negozi in cui il prodotto e' disponibile
		List<NegozioVm> negoziFornitori = prodottoService.findStoresWithProduct(prodotto.getId());
		model.addAttribute("negoziFornitori", negoziFornitori);
		
		//Aggiungi il prossimo elemento di ricerca
		model.addAttribute("searchContent", new SearchContentVm());
		
		return "prodotto";
	}
	
	@RequestMapping(value= "/prodotti/{codice}", method = RequestMethod.DELETE)
	public String deleteProdottoByCodice(@PathVariable("codice") String codice, Model model) {
		//Prodotto da Eliminare
		ProdottoVm prodotto = prodottoService.findByCodice(codice);
		
		try {
			// Elimina le dipendenze in Disponibilita
			disponibilitaService.deleteByProdotto(prodotto);
			
			// Elimina il Prodotto
			prodottoService.delete(prodotto);
		} catch(DataRelatedException e) {
			//TODO HANDLE Data Related Exception on saving a new negozio
			return "redirect:/prodotti";
		}
		
		return "redirect:/prodotti";
	}
	
	@RequestMapping(value="/prodotti/{codice}", method = RequestMethod.PUT)
	public String updateProdottoByCodice(@PathVariable("codice") String codice,
			@ModelAttribute(value = "prodottoUpdated") ProdottoVm prodottoUpdated ,Model model) {
		
		// Prodotto da Aggiornare
		ProdottoVm prodotto = prodottoService.findByCodice(codice);
				
		// Aggiornamento
		try {
			prodotto = prodottoService.update(prodotto, prodottoUpdated);
		} catch (DataRelatedException e) {
			//TODO HANDLE Data Related Exception on saving a new negozio
			return "redirect:/prodotti";
		}
		
		return "redirect:/prodotti/" + codice;
	}
	
	@RequestMapping(value ="/prodotti/{codice}/modifica", method = RequestMethod.GET)
	public String showModificaProdottoPage(@PathVariable("codice") String codice, Model model) {
		
		// Prodotto da modificare nel form
		ProdottoVm prodottoUpdated = new ProdottoVm();
		model.addAttribute("prodottoUpdated", prodottoUpdated);
		
		// Prodotto esistente
		ProdottoVm prodottoCurrent = prodottoService.findByCodice(codice);
		model.addAttribute("prodottoCurrent", prodottoCurrent);
		
		// Lista di Categorie
		List<CategoriaVm> categoriaList = categoriaService.findAll();
		model.addAttribute("categoriaList", categoriaList);
		
		return "modifica_prodotto_page";
	}
	
	@RequestMapping(value = "/negozi/{id}/prodotti/{codice}", method = RequestMethod.GET)
	public String showProdottoByCodiceFromNegozio(@PathVariable("id") Long negozioId, 
			@PathVariable("codice") String codice, Model model) {
		
		ProdottoVm prodotto = prodottoService.findByCodice(codice);
		model.addAttribute("prodottoCurrent", prodotto);
		
		model.addAttribute("negozioId", negozioId);
		
		//Aggiungi il prossimo elemento di ricerca
		model.addAttribute("searchContent", new SearchContentVm());
		
		return "prodotto";
	}
}