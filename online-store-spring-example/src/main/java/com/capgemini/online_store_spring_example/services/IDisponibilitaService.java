package com.capgemini.online_store_spring_example.services;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.DisponibilitaEntity;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;


public interface IDisponibilitaService {

	public DisponibilitaEntity save(final DisponibilitaEntity entity)throws DataRelatedException;
	
	public void deleteByNegozio(final NegozioEntity negozio)throws DataRelatedException;
	
}
