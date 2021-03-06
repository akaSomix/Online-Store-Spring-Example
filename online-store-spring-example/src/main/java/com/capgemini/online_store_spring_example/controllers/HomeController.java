package com.capgemini.online_store_spring_example.controllers;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.online_store_spring_example.cart.CartEntity;
import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.servicefacades.CittaServiceFacade;
import com.capgemini.online_store_spring_example.servicefacades.NegozioServiceFacade;
import com.capgemini.online_store_spring_example.viewmodels.CittaVm;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;
import com.capgemini.online_store_spring_example.viewmodels.SearchContentVm;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class HomeController {
	@Resource(name="cart")
	CartEntity cart;
	
	@Autowired
	NegozioServiceFacade negozioService;
	
	@Autowired
	CittaServiceFacade cittaService;
	
	@GetMapping("/")
	public String getIndex(Model model) {
		//Aggiungi il prossimo elemento di ricerca
		model.addAttribute("searchContent", new SearchContentVm());
		return "index";
	}
	
	@RequestMapping(value = "/negozi/create", method = RequestMethod.GET)
	public String addNegozioForm(Model model) {
		// Negozio da riempire nel form
		NegozioVm negozioForm = new NegozioVm();
		
		// Lista delle citta esistenti
		List<CittaVm> cittaList = cittaService.findAll();
		
		//TODO Change, intended for debug only
		if(cittaList == null || cittaList.size() == 0) {
			log.error("CITTA LIST IS UNAVAILABLE");
			return "index";
		}
		
		model.addAttribute("negozioForm", negozioForm);
		model.addAttribute("cittaList", cittaList);
		return "creation/create_negozio_page";
	}
	
	@RequestMapping(value="/negozi/create", method = RequestMethod.POST)
	public String addNegozio(@Valid @ModelAttribute(value="formNegozio") NegozioVm negozio,
			BindingResult bindingResult) {
		
		// Controllo Validazione
		if(bindingResult.hasErrors()) {
			log.error(negozio.getClass().getName() + " -- with parameters " + negozio.toString() + " is NOT VALID");
			return "redirect:/negozi";
		}
		
		// Salvataggio entities 
		try {
			negozioService.save(negozio);
		}catch(DataRelatedException e) {
			//TODO HANDLE Data Related Exception on saving a new negozio
			return "redirect:/";
		}
		return "redirect:/";
	}

	@GetMapping("/cart")
	public String showCartPage(Model model, HttpSession session) {
		model.addAttribute("cartItems", cart.getNegozioItems());
		model.addAttribute("cartItemsKeys", cart.getNegozioItems().keySet());
		return "cart_page";
	}
}
