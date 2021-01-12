 package com.capgemini.online_store_spring_example.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.repositories.NegozioRepository;
import com.capgemini.online_store_spring_example.services.INegozioService;

import lombok.extern.slf4j.Slf4j;

@Service("negozioService")
@Slf4j
public class NegozioServiceImpl implements INegozioService {

	@Autowired
	private NegozioRepository negozioRepository;
	
	@Override
	public List<NegozioEntity> findAll() {
		List<NegozioEntity> entitiesFound = negozioRepository.findAll();
		
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + NegozioEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}
	
	@Override
	public NegozioEntity findById(Long id) {
		Optional<NegozioEntity> entityFound = negozioRepository.findById(id);
		
		if(!entityFound.isPresent()) {
			log.info(this.getClass().getName() + " -- " + NegozioEntity.class.getName() + " no record found");
			
			//TODO Check if it's correct 
			return null;
		}
		
		return entityFound.get();
	}

	@Override
	public List<NegozioEntity> findByCittaId(Long cittaId) {
		List<NegozioEntity> entitiesFound = negozioRepository.findByCittaId(cittaId);
		
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + NegozioEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}

	@Override
	public List<NegozioEntity> findByCittaNomeOrCap(String cittaNome, String cittaCap) {
		
		List<NegozioEntity> entitiesFound = negozioRepository.findByCittaNomeOrCap(cittaNome, cittaCap);
		
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + NegozioEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}

	@Override
	public List<ProdottoEntity> findProdottiDisponibiliByNegozioId(Long id) {
		
		List<ProdottoEntity> entitiesFound = negozioRepository.findProdottiDisponibiliByNegozioId(id);
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + NegozioEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}

	@Override
	@Transactional(rollbackOn = DataRelatedException.class)
	public NegozioEntity save(NegozioEntity entity) throws DataRelatedException {
		NegozioEntity saved;
		try {
			saved = this.negozioRepository.saveAndFlush(entity);
		}catch(final DataIntegrityViolationException e) {
			throw new DataRelatedException("Error saving entity with id " + entity.getNegozioId() + " and nome " + entity.getNome());
		}
		return saved;
	}
}