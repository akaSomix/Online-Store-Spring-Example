package com.capgemini.online_store_spring_example.services;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.CittaEntity;

import java.util.List;

public interface ICittaService {

	public List<CittaEntity> findAll();
	
	public CittaEntity findById(final Long id);
	
	public List<CittaEntity> findByNome(final String nome);
	
	public CittaEntity findByNomeAndCap(final String nome, final String cap);
	
	public CittaEntity save(final CittaEntity entity)throws DataRelatedException;

}