package com.capgemini.online_store_spring_example.controllers.thymeleaf;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.commons.EntityConstants;
import com.capgemini.online_store_spring_example.servicefacades.CategoriaServiceFacade;
import com.capgemini.online_store_spring_example.servicefacades.DisponibilitaServiceFacade;
import com.capgemini.online_store_spring_example.servicefacades.NegozioServiceFacade;
import com.capgemini.online_store_spring_example.servicefacades.ProdottoServiceFacade;
import com.capgemini.online_store_spring_example.viewmodels.CategoriaVm;
import com.capgemini.online_store_spring_example.viewmodels.DisponibilitaVm;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;
import com.capgemini.online_store_spring_example.viewmodels.ProdottoVm;
import com.capgemini.online_store_spring_example.viewmodels.SearchContentVm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Controller
public class NegoziPageController {

	@Autowired
	private NegozioServiceFacade negozioService;
	
	@Autowired
	private ProdottoServiceFacade prodottoService;
	
	@Autowired
	private DisponibilitaServiceFacade disponibilitaService;
	
	@Autowired
	private CategoriaServiceFacade categoriaService;
	
	
	@GetMapping("/negozi")
	public String showNegozi(Model model){
		// retrieve entities 
		List<NegozioVm> negozi = negozioService.findAll();
		
		// update model with attributes 
		model.addAttribute("negozi", negozi);
		
		//Aggiungi il prossimo elemento di ricerca
		model.addAttribute("searchContent", new SearchContentVm());
		
		return "lista_negozi_page";
	}
	
	@RequestMapping(value = "/negozi/da-citta", method = RequestMethod.GET)
	public String showNegoziByCitta(
			@ModelAttribute("citta_nome") String nome,
			@ModelAttribute("citta_cap") String cap,
			BindingResult bindingResult, Model  model ) {
		
		if(bindingResult.hasErrors()){
			//TODO Handle Error
			log.error("Binding Error [" + bindingResult.toString() + "] on class " + this.getClass().getName() + " using GET /negozi");
			return "index";
        }
				
		// retrieve VMs 
		List<NegozioVm> negozi = negozioService.findByCittaNomeOrCap(nome, cap);
		
		//Aggiungi il prossimo elemento di ricerca
		model.addAttribute("searchContent", new SearchContentVm());
		
		// update model and redirect
		model.addAttribute("negozi", negozi);
		return "lista_negozi_page";
	}
	
	@RequestMapping(value = "/negozi/{id}", method = RequestMethod.GET)
	public String getNegozio(@PathVariable("id") Long id,Model model) {
		return "redirect:" + id + "/prodotti";
	}
	
	@RequestMapping(value = "/negozi/{id}", method = RequestMethod.DELETE)
	public String deleteNegozio(@PathVariable("id") Long id, Model model) {
		// Negozio da eliminare 
		NegozioVm negozio = negozioService.findById(id);
		
		try {
			// Elimina le dipendenze in Disponibilita
			disponibilitaService.deleteByNegozio(negozio);
			
			// Elimina il Negozio
			negozioService.delete(negozio);
		} catch(DataRelatedException e) {
			//TODO HANDLE Data Related Exception on saving a new negozio
			return "redirect:/negozi";
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/negozi/{id}/prodotti", method = RequestMethod.GET)
	public String getAllProducts(@PathVariable("id") Long id,Model model) {
		
		//Retrieves all the products
		List<ProdottoVm> prodottiFound = negozioService.findProdottiDisponibiliByNegozioId(id);
				
		//Retrieves the store name 
		NegozioVm negozio = negozioService.findById(id);
		
		model.addAttribute("negozio", negozio);
		
		// Se non ci sono prodotti imposta flag
		if(prodottiFound.size() == 0) {
			model.addAttribute("noProductsFlag", true);
		} 
		else {
			// Altrimenti aggiungi la lista dei negozi
			model.addAttribute("prodottiInNegozio", prodottiFound);
			model.addAttribute("noProductsFlag", false);
		}
		
		//Aggiungi il prossimo elemento di ricerca
		model.addAttribute("searchContent", new SearchContentVm());
		
		return "negozio";
	}
	
	@RequestMapping(value = "/negozi/{id}/prodotti/add", method = RequestMethod.GET)
	public String showAddProdottiForm(@PathVariable("id") Long id, Model model) {
		
		// Oggetto Disponibilita Vuoto
		DisponibilitaVm formDisponibilita = new DisponibilitaVm();
		model.addAttribute("formDisponibilita", formDisponibilita);
		
		// Negozio di riferimento
		NegozioVm negozio = negozioService.findById(id);
		model.addAttribute("negozio", negozio);
		
		//Lista di prodotti disponibili per essere aggiunti
		List<ProdottoVm> prodottoList = prodottoService.findAllNotInNegozio(id);
		if(prodottoList.size() == 0) {
			log.info("PRODOTTO LIST IS EMPTY");
			model.addAttribute("noProductsFlag", true);
		}
		else {
			model.addAttribute("prodotti", prodottoList);
			model.addAttribute("noProductsFlag", false);
		}
		return "creation/add_prodotti_page";
	}
	
	@RequestMapping(value = "/negozi/{id}/prodotti/add-submit", method = RequestMethod.POST)
	public String submitAddProdottiForm(@PathVariable("id") Long id, 
			@Valid @ModelAttribute(value = "formDisponibilita") DisponibilitaVm formDisponibilita,
			BindingResult bindingResult, Model model) {
		
		// Controllo Validazione
		if(bindingResult.hasErrors()) {
			log.error(formDisponibilita.getClass().getName() + " -- with parameters " + formDisponibilita.toString() + " is NOT VALID");
			return "redirect:/negozi/" + id + "/prodotti";
		}
		
		// Salvataggio entities 
		try {
			disponibilitaService.enableOrSave(formDisponibilita);
		}catch(DataRelatedException e) {
			//TODO HANDLE Data Related Exception on saving a new negozio
			return "redirect:/negozi/" + id + "/prodotti";
		}
		
		return "redirect:add";
	}


	@RequestMapping(value = "/negozi/{id}/prodotti/create-new", method = RequestMethod.GET)
	public String showCreateProdottoFromNegozio(@PathVariable("id") Long id, Model model) {
		//Oggetto prodotto vuoto
		ProdottoVm prodottoForm = new ProdottoVm();
		model.addAttribute("prodottoForm", prodottoForm);
		
		// Massima lunghezza per prodotto.descrizione
		model.addAttribute("prodottoDescrizioneMaxLength", EntityConstants.PRODOTTO_ENTITY_DESCRIZIONE_LENGTH);
		
		// Lunghezza esatta per prodotto.codiceProdotto
		model.addAttribute("prodottoCodiceLength", EntityConstants.PRODOTTO_ENTITY_CODICE_LENGTH);
		
		// Codici di Prodotti gia esistenti

		model.addAttribute("codiciEsistenti", prodottoService.findAllProdottoCodice().toArray()); 
		
		//Lista di Categorie
		List<CategoriaVm> categoriaList = categoriaService.findAll();
		
		//TODO Change, intended for debug only
		if(categoriaList == null || categoriaList.size() == 0) {
			log.error("CATEGORIA LIST IS UNAVAILABLE");
			return "redirect:add";
		}
		model.addAttribute("categorie", categoriaList);
		
		//propaga negozio id
		model.addAttribute("negozioId", id);
		
		return "creation/create_prodotto_page";
	}
	
	@RequestMapping(value = "/negozi/{id}/prodotti/create-new", method = RequestMethod.POST)
	public String submitCreateProdottoFromNegozio(@PathVariable("id") Long id,
			@Valid @ModelAttribute(value = "prodottoForm") ProdottoVm prodottoForm,
			BindingResult bindingResult ,Model model) {
		
		// Controllo Validazione
		if(bindingResult.hasErrors()) {
			log.error(prodottoForm.getClass().getName() + " -- with parameters " + prodottoForm.toString() + " is NOT VALID");
			return "redirect:/negozi/" + id + "/prodotti";
		}
		
		// Salvataggio entities 
		try {
			prodottoService.save(prodottoForm);
		}catch(DataRelatedException e) {
			//TODO HANDLE Data Related Exception on saving a new negozio
			return "redirect:/negozi/" + id + "/prodotti";
		}
		
		return "redirect:add";
	}
}
