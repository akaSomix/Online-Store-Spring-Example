package com.capgemini.online_store_spring_example.servicefacades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.assemblers.impl.CittaAssembler;
import com.capgemini.online_store_spring_example.entities.CittaEntity;
import com.capgemini.online_store_spring_example.services.ICittaService;
import com.capgemini.online_store_spring_example.viewmodels.CittaVm;

@Service
public class CittaServiceFacade {
	
	@Autowired
	private CittaAssembler cittaAssembler;
	
	@Autowired
	private ICittaService cittaService;
	
	
	public List<CittaVm> findAll(){
		List<CittaEntity> entitiesFound = cittaService.findAll();
		
		List<CittaVm> vms = cittaAssembler.toViewModel(entitiesFound);
		
		return vms; 
	}
}
