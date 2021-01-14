package com.capgemini.online_store_spring_example.services;

import java.util.List;
import java.util.SortedSet;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;

public interface IProdottoService {

	public List<ProdottoEntity> findAll();

	public SortedSet<String> findAllProdottoCodice();
	
	public List<NegozioEntity> findStoresWithProduct(final long prodottoId);
	
	public List<ProdottoEntity> findAllNotInNegozio(final long negozioId);

	public ProdottoEntity findById(final long id);
	
	public List<ProdottoEntity> findByNomeStartingWith(final String prefix);

	public ProdottoEntity findByCodice(final String codice);

	public ProdottoEntity save(final ProdottoEntity entity)throws DataRelatedException;
	
	public void delete(final ProdottoEntity entity)throws DataRelatedException;

	public ProdottoEntity update(ProdottoEntity entity, ProdottoEntity entityUpdated)throws DataRelatedException;

}
