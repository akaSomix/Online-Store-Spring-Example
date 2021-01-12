package com.capgemini.online_store_spring_example.assemblers.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.online_store_spring_example.assemblers.IAssemblerFactory;
import com.capgemini.online_store_spring_example.entities.CittaEntity;
import com.capgemini.online_store_spring_example.services.ICittaService;
import com.capgemini.online_store_spring_example.viewmodels.CittaVm;

@Component
public class CittaAssembler implements IAssemblerFactory<CittaVm, CittaEntity> {
	
	@Autowired
	private ICittaService cittaService;
	
	@Override
	public CittaEntity toEntity(CittaVm viewModel) {
		
		if(viewModel == null)return null;
		
		CittaEntity entity = new CittaEntity();
		
		if ( viewModel.getId() != null )entity.setCittaId(viewModel.getId());
		entity.setNome(viewModel.getNome());
		entity.setCap(viewModel.getCap());
		entity.setStato(viewModel.getStato());
		
		CittaEntity provincia = cittaService.findById(viewModel.getProvinciaId());
		entity.setProvincia(provincia);
		
		return entity;
	}
	
	public CittaVm toViewModel(CittaEntity entity) {
		
		if(entity == null)return null;
		
		CittaVm viewModel = new CittaVm();

		viewModel.setId(entity.getCittaId());
		viewModel.setNome(entity.getNome());
		viewModel.setCap(entity.getCap());
		viewModel.setStato(entity.getStato());
		
		CittaEntity provincia = entity.getProvincia();
		if(provincia != null)viewModel.setProvinciaId(provincia.getCittaId());
		
		return viewModel;
	};
	
}
