package com.capgemini.online_store_spring_example.services;

import java.util.List;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;

public interface INegozioService {

	public List<NegozioEntity> findAll();

	public NegozioEntity findById(final Long id);
	
	public List<NegozioEntity> findByCittaId(final Long cittaId);
	
	public List<NegozioEntity> findByCittaNomeOrCap(final String cittaNome, final String cittaCap);
	
	public List<ProdottoEntity> findProdottiDisponibiliByNegozioId(final Long id);
	
	public NegozioEntity save(final NegozioEntity entity) throws DataRelatedException;
}
