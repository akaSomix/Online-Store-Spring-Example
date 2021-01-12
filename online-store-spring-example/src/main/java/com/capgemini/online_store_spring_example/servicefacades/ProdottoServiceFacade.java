package com.capgemini.online_store_spring_example.servicefacades;

import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.assemblers.impl.NegozioAssembler;
import com.capgemini.online_store_spring_example.assemblers.impl.ProdottoAssembler;
import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.services.IProdottoService;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;
import com.capgemini.online_store_spring_example.viewmodels.ProdottoVm;

@Service
public class ProdottoServiceFacade {

	@Autowired
	private ProdottoAssembler prodottoAssembler;
	
	@Autowired
	private NegozioAssembler negozioAssembler;
	
	@Autowired
	private IProdottoService prodottoService;
	
	public List<ProdottoVm> findAll(){
		List<ProdottoEntity> entitiesFound = prodottoService.findAll();
		
		List<ProdottoVm> vms = prodottoAssembler.toViewModel(entitiesFound);
		
		return vms;
	}
	
	public List<NegozioVm> findStoresWithProduct(final Long prodottoId){
		List<NegozioEntity> entitiesFound = prodottoService.findStoresWithProduct(prodottoId);
	
		List<NegozioVm> vms = negozioAssembler.toViewModel(entitiesFound);
		
		return vms;
	}
	
	public List<ProdottoVm> findAllNotInNegozio(final Long negozioId){
		List<ProdottoEntity> entitiesFound = prodottoService.findAllNotInNegozio(negozioId);
		
		List<ProdottoVm> vms = prodottoAssembler.toViewModel(entitiesFound);
		
		return vms;
	}
	
	public List<ProdottoVm> findByNomeStartingWith(final String prefix){
		List<ProdottoEntity> entitiesFound = prodottoService.findByNomeStartingWith(prefix);
		
		List<ProdottoVm> vms = prodottoAssembler.toViewModel(entitiesFound);
		
		return vms;
	}
	
	public ProdottoVm findByCodice(final String codice) {
		ProdottoEntity entity = prodottoService.findByCodice(codice);
		
		ProdottoVm vm = prodottoAssembler.toViewModel(entity);
		
		return vm;
	}
	
	public void save(final ProdottoVm prodotto) throws DataRelatedException{
		ProdottoEntity entityToSave = prodottoAssembler.toEntity(prodotto);
		
		prodottoService.save(entityToSave);
	}
	
	public SortedSet<String> findAllProdottoCodice(){
		return prodottoService.findAllProdottoCodice();
	}
}
