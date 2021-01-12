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

import com.capgemini.online_store_spring_example.servicefacades.ProdottoServiceFacade;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;
import com.capgemini.online_store_spring_example.viewmodels.ProdottoVm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProdottiPageController {
	
	@Autowired
	public ProdottoServiceFacade prodottoService;

	@RequestMapping(value = "/prodotti", method = RequestMethod.GET)
	public String showProdottiByName(@ModelAttribute("prefix")String prefix,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()){
			//TODO Handle Error
			log.error("Binding Error [" + bindingResult.toString() + "] on class " + this.getClass().getName() + " using GET /prodotti");
			return "index";
        }
		
		// retrieve VMs
		List<ProdottoVm> prodotti = prodottoService.findByNomeStartingWith(prefix);
		
		//update model and redirect
		model.addAttribute("prodotti", prodotti);
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
		
		return "prodotto";
	}
	
	
	@RequestMapping(value = "/negozi/{id}/prodotti/{codice}", method = RequestMethod.GET)
	public String showProdottoByCodiceFromNegozio(@PathVariable("id") Long negozioId, 
			@PathVariable("codice") String codice, Model model) {
		
		ProdottoVm prodotto = prodottoService.findByCodice(codice);
		
		model.addAttribute("prodottoCurrent", prodotto);
		model.addAttribute("negozioId", negozioId);
		
		return "prodotto";
	}

}