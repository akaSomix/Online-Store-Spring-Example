package com.capgemini.online_store_spring_example.services;

import java.util.List;

import com.capgemini.online_store_spring_example.entities.CategoriaEntity;

public interface ICategoriaService {

	public List<CategoriaEntity> findall();
	
	public CategoriaEntity findById(final Long id);
	
	public CategoriaEntity findByNome(final String nome);
	
}
