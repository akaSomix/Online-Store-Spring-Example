package com.capgemini.online_store_spring_example.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.CittaEntity;
import com.capgemini.online_store_spring_example.repositories.CittaRepository;
import com.capgemini.online_store_spring_example.services.ICittaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("cittaService")
public class CittaServiceImpl implements ICittaService {

	@Autowired
	private CittaRepository cittaRepository;
	
	@Override
	public List<CittaEntity> findAll() {
		List<CittaEntity> entitiesFound = cittaRepository.findAll();
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + CittaEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}

	public CittaEntity findById(Long id) {
		
		Optional<CittaEntity> entityFound = cittaRepository.findById(id);
		
		if(!entityFound.isPresent()) {
			log.info(this.getClass().getName() + " -- " + CittaEntity.class.getName() + " no record found");
			return null;
		}
		return entityFound.get();
	}
	
	
	@Override
	public List<CittaEntity> findByNome(String nome) {
		List<CittaEntity> entitiesFound = cittaRepository.findByNome(nome);
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + CittaEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}

	@Override
	public CittaEntity findByNomeAndCap(String nome, String cap) {
		CittaEntity entityFound = cittaRepository.findByNomeAndCap(nome, cap);
		if(entityFound == null) {
			log.info(this.getClass().getName() + " -- " + CittaEntity.class.getName() + " no record found");
		}
		return entityFound;
	}
	
	@Override
	@Transactional(rollbackOn = DataRelatedException.class)
	public CittaEntity save(CittaEntity entity) throws DataRelatedException {
		CittaEntity entitySaved;
		try {
			entitySaved = cittaRepository.saveAndFlush(entity);
		}catch(final DataIntegrityViolationException e) {
			if(log.isErrorEnabled()) {
				log.error(this.getClass().getName() + " -- " + " Error saving " + CittaEntity.class.getName());
			}
			throw new DataRelatedException("Error saving entity with id " + entity.getCittaId() + " and name" + entity.getNome());
		}
		return entitySaved;
	}

}
