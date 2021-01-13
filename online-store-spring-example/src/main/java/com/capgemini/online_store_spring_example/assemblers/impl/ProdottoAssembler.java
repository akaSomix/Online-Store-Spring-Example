package com.capgemini.online_store_spring_example.assemblers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.online_store_spring_example.assemblers.IAssemblerFactory;
import com.capgemini.online_store_spring_example.commons.EntityConstants;
import com.capgemini.online_store_spring_example.entities.CategoriaEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.services.ICategoriaService;
import com.capgemini.online_store_spring_example.viewmodels.ProdottoVm;

@Component
public class ProdottoAssembler implements IAssemblerFactory<ProdottoVm, ProdottoEntity> {

	@Autowired
	private ICategoriaService categoriaService;
	
	
	@Override
	public ProdottoEntity toEntity(ProdottoVm viewModel) {
		
		if(viewModel == null)return null;
		
		ProdottoEntity entity = new ProdottoEntity();
		
		if(viewModel.getId() != null)entity.setProdottoId(viewModel.getId());
		entity.setCodiceProdotto(viewModel.getCodice());
		entity.setNome(viewModel.getNome());
		
		// Inserimento di una descrizione di Default se non presente
		if(viewModel.getDescrizione() != null) {
			entity.setDescrizione(EntityConstants.DEFAULT_PRODOTTO_ENTITY_DESCRIZIONE);
		} else {
			entity.setDescrizione(viewModel.getDescrizione());
		}
		
		entity.setPrezzo(viewModel.getPrezzo());
		
		CategoriaEntity categoria = categoriaService.findById(viewModel.getCategoriaId());
		entity.setCategoria(categoria);
		
		return entity;
	}

	@Override
	public ProdottoVm toViewModel(ProdottoEntity entity) {
		
		if(entity == null)return null;
		
		ProdottoVm viewModel = new ProdottoVm();
		viewModel.setId(entity.getProdottoId());
		viewModel.setCodice(entity.getCodiceProdotto());
		viewModel.setNome(entity.getNome());
		viewModel.setDescrizione(entity.getDescrizione());
		viewModel.setPrezzo(entity.getPrezzo());
		
		CategoriaEntity categoria = entity.getCategoria();
		viewModel.setCategoriaId(categoria.getCategoriaId());
		viewModel.setCategoriaNome(categoria.getNome());
		
		return viewModel;
	}
	
}
