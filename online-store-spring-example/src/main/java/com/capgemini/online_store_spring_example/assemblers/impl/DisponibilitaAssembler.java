package com.capgemini.online_store_spring_example.assemblers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.online_store_spring_example.assemblers.IAssemblerFactory;
import com.capgemini.online_store_spring_example.entities.DisponibilitaEntity;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.services.INegozioService;
import com.capgemini.online_store_spring_example.services.IProdottoService;
import com.capgemini.online_store_spring_example.viewmodels.DisponibilitaVm;

@Component
public class DisponibilitaAssembler implements IAssemblerFactory<DisponibilitaVm, DisponibilitaEntity>{

	@Autowired
	private INegozioService negozioService;
	
	@Autowired
	private IProdottoService prodottoService;
	
	@Override
	public DisponibilitaEntity toEntity(DisponibilitaVm viewModel) {

		if(viewModel == null)return null;
		ProdottoEntity p = prodottoService.findById(viewModel.getProdottoId());
		NegozioEntity n = negozioService.findById(viewModel.getNegozioId());
		
		DisponibilitaEntity entity = new DisponibilitaEntity();
		entity.setProdotto(p);
		entity.setNegozio(n);
		
		return entity;
	}

	@Override
	public DisponibilitaVm toViewModel(DisponibilitaEntity entity) {
		if(entity == null)return null;
		
		DisponibilitaVm viewModel = new DisponibilitaVm();
		viewModel.setProdottoId(entity.getProdotto().getProdottoId());
		viewModel.setNegozioId(entity.getNegozio().getNegozioId());
		
		return viewModel;
	}

}
