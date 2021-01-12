package com.capgemini.online_store_spring_example.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.servicefacades.CittaServiceFacade;
import com.capgemini.online_store_spring_example.servicefacades.NegozioServiceFacade;
import com.capgemini.online_store_spring_example.viewmodels.CittaVm;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	NegozioServiceFacade negozioService;
	
	@Autowired
	CittaServiceFacade cittaService;
	
	@GetMapping("/")
	public String getIndex(Model model) {
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
		return "create_negozio_page";
	}
	
	@RequestMapping(value="/negozi/create", method = RequestMethod.POST)
	public String addNegozio(@ModelAttribute(value="formNegozio") NegozioVm negozio) {
		try {
			negozioService.save(negozio);
		}catch(DataRelatedException e) {
			//TODO HANDLE Data Related Exception on saving a new negozio
			return "index";
		}
		return "redirect:/";
	}
}
