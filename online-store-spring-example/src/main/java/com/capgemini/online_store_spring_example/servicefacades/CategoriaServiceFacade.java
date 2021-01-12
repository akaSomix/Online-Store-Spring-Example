package com.capgemini.online_store_spring_example.servicefacades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.assemblers.impl.CategoriaAssembler;
import com.capgemini.online_store_spring_example.entities.CategoriaEntity;
import com.capgemini.online_store_spring_example.services.ICategoriaService;
import com.capgemini.online_store_spring_example.viewmodels.CategoriaVm;

@Service
public class CategoriaServiceFacade {

	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private CategoriaAssembler categoriaAssembler;
	
	public List<CategoriaVm> findAll(){
		List<CategoriaEntity> entities = categoriaService.findall();
		
		return categoriaAssembler.toViewModel(entities);
	}

}
