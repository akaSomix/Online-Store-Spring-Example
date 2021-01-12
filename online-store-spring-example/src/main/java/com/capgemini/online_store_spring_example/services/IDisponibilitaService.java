package com.capgemini.online_store_spring_example.services;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.DisponibilitaEntity;


public interface IDisponibilitaService {

	public DisponibilitaEntity save(final DisponibilitaEntity entity)throws DataRelatedException;
}
