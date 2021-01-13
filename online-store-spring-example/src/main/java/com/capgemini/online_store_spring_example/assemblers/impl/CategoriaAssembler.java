package com.capgemini.online_store_spring_example.assemblers.impl;

import org.springframework.stereotype.Component;

import com.capgemini.online_store_spring_example.assemblers.IAssemblerFactory;
import com.capgemini.online_store_spring_example.commons.EntityConstants;
import com.capgemini.online_store_spring_example.entities.CategoriaEntity;
import com.capgemini.online_store_spring_example.viewmodels.CategoriaVm;

@Component
public class CategoriaAssembler implements IAssemblerFactory<CategoriaVm, CategoriaEntity> {

	@Override
	public CategoriaEntity toEntity(CategoriaVm viewModel) {
		
		if(viewModel == null)return null;
		
		CategoriaEntity entity = new CategoriaEntity();
		
		if(viewModel.getId() != null)entity.setCategoriaId(viewModel.getId());
		entity.setNome(viewModel.getNome());

		if(viewModel.getDescrizione() != null) {
			entity.setDescrizione(EntityConstants.DEFAULT_CATEGORIA_ENTITY_DESCRIZIONE);
		} else {
			entity.setDescrizione(viewModel.getDescrizione());
		}
		
		return entity;
	}

	@Override
	public CategoriaVm toViewModel(CategoriaEntity entity) {
		if(entity == null)return null;
		
		CategoriaVm viewModel = new CategoriaVm();
		
		viewModel.setId(entity.getCategoriaId());
		viewModel.setNome(entity.getNome());
		viewModel.setDescrizione(entity.getDescrizione());
		
		return viewModel;
	}

	
}
