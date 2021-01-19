package com.capgemini.online_store_spring_example.services;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.DisponibilitaEntity;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;


public interface IDisponibilitaService {

	public DisponibilitaEntity findByNegozioIdAndCodiceProdotto (final Long negozioId, final String codiceProdotto);
	
	public DisponibilitaEntity save(final DisponibilitaEntity entity)throws DataRelatedException;
	
	public void deleteByNegozio(final NegozioEntity negozio)throws DataRelatedException;
	
	public void deleteByProdotto(final ProdottoEntity prodotto)throws DataRelatedException;
	
	public void enableOrSave(DisponibilitaEntity entity) throws DataRelatedException;
	
	public void disable(final DisponibilitaEntity entity) throws DataRelatedException;
	
}