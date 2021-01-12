package com.capgemini.online_store_spring_example.assemblers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.online_store_spring_example.assemblers.IAssemblerFactory;
import com.capgemini.online_store_spring_example.entities.CittaEntity;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.services.ICittaService;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;

@Component
public class NegozioAssembler implements IAssemblerFactory<NegozioVm, NegozioEntity> {

	@Autowired
	private ICittaService cittaService;
	
	
	@Override
	public NegozioEntity toEntity(NegozioVm viewModel) {
		
		if(viewModel == null)return null;
		
		NegozioEntity entity = new NegozioEntity();
		
		if(viewModel.getId() != null)entity.setNegozioId(viewModel.getId());
		entity.setNome(viewModel.getNome());
		entity.setIndirizzo(viewModel.getIndirizzo());
		
		CittaEntity citta = cittaService.findById(viewModel.getCittaId());
		entity.setCitta(citta);
		
		return entity;
	}

	@Override
	public NegozioVm toViewModel(NegozioEntity entity) {
		
		if(entity == null)return null;

		NegozioVm viewModel = new NegozioVm();
		
		viewModel.setId(entity.getNegozioId());
		viewModel.setNome(entity.getNome());
		viewModel.setIndirizzo(entity.getIndirizzo());
		
		CittaEntity citta = entity.getCitta();
		viewModel.setCittaId(citta.getCittaId());
		viewModel.setCittaCap(citta.getCap());
		viewModel.setCittaNome(citta.getNome());
		
		return viewModel;
	}

}
