package com.capgemini.online_store_spring_example.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.DisponibilitaEntity;
import com.capgemini.online_store_spring_example.repositories.DisponibilitaRepository;
import com.capgemini.online_store_spring_example.services.IDisponibilitaService;


@Service
public class DisponibilitaServiceImpl implements IDisponibilitaService {

	@Autowired
	private DisponibilitaRepository disponibilitaRepository;
	
	@Override
	@Transactional(rollbackOn = DataRelatedException.class)
	public DisponibilitaEntity save(DisponibilitaEntity entity) throws DataRelatedException {
		DisponibilitaEntity saved;
		
		try {
			saved = this.disponibilitaRepository.saveAndFlush(entity);
		}catch(final DataIntegrityViolationException e){
			throw new DataRelatedException("Error saving entity -- " + this.getClass().getName());
		}
		
		return saved;
	}

}
