package com.capgemini.online_store_spring_example.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.repositories.ProdottoRepository;
import com.capgemini.online_store_spring_example.services.IProdottoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdottoServiceImpl implements IProdottoService {

	@Autowired
	private ProdottoRepository prodottoRepository;
	
	@Override
	public List<ProdottoEntity> findAll() {
		List<ProdottoEntity> entitiesFound = prodottoRepository.findAll();
		
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + ProdottoEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}
	
	@Override
	public SortedSet<String> findAllProdottoCodice() {
		SortedSet<String> codici = prodottoRepository.findAllProdottoCodice();
		if(codici.isEmpty()) {
			log.debug(this.getClass().getName() + " -- " + ProdottoEntity.class.getName() + " no record found");
		}
		return codici;
	}

	@Override
	public List<NegozioEntity> findStoresWithProduct(long prodottoId) {
		List<NegozioEntity> entitiesFound = prodottoRepository.findStoresWithProduct(prodottoId);
		
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + ProdottoEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}
	
	@Override
	public List<ProdottoEntity> findAllNotInNegozio(long negozioId) {
		List<ProdottoEntity> entitiesFound = prodottoRepository.findAllNotInNegozio(negozioId);
		
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + ProdottoEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}

	@Override
	public ProdottoEntity findById(long id) {
		Optional<ProdottoEntity> entityFound = prodottoRepository.findById(id);
		if(!entityFound.isPresent()) {
			log.info(this.getClass().getName() + " -- " + ProdottoEntity.class.getName() + " no record found");
			return null;
		}
		return entityFound.get();
	}
	
	public ProdottoEntity findByCodice(String codice) {
		Optional<ProdottoEntity> entityFound = prodottoRepository.findByCodice(codice);
		if(!entityFound.isPresent()) {
			log.info(this.getClass().getName() + " -- " + ProdottoEntity.class.getName() + " no record found");
			return null;
		}
		return entityFound.get();
	}	
	
	@Override
	public List<ProdottoEntity> findByNomeStartingWith(String prefix) {
		List<ProdottoEntity> entitiesFound = prodottoRepository.findByNomeStartingWith(prefix);
		
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + ProdottoEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}

	@Override
	@Transactional(rollbackOn = DataRelatedException.class)
	public ProdottoEntity save(final ProdottoEntity entity)
			throws DataRelatedException{
		
		ProdottoEntity saved;
		try {
			saved = this.prodottoRepository.saveAndFlush(entity);
		}catch(final DataIntegrityViolationException e) {
			throw new DataRelatedException("Error saving entity with id " + entity.getProdottoId() + " and codice " + entity.getCodiceProdotto());
		}
		return saved;
	}
	
	@Override
	@Transactional(rollbackOn = DataRelatedException.class)
	public void delete(final ProdottoEntity entity)
			throws DataRelatedException{
		try {
			this.prodottoRepository.delete(entity);
		}catch(final DataIntegrityViolationException e) {
			throw new DataRelatedException("Error deleting entity with id " + entity.getProdottoId() + " and codice " + entity.getCodiceProdotto());
		}
	}

	@Override
	public ProdottoEntity update(ProdottoEntity entity, ProdottoEntity entityUpdated) throws DataRelatedException {
		
		// Update parametri modificati
		if (entityUpdated.getNome() != null && entityUpdated.getNome() != "") {
			entity.setNome(entityUpdated.getNome());
		}
		if (entityUpdated.getDescrizione() != null && entityUpdated.getDescrizione() != "") {
			entity.setDescrizione(entityUpdated.getDescrizione());
		}
		if (entityUpdated.getPrezzo() != null && entityUpdated.getPrezzo() > 0) {
			entity.setPrezzo(entityUpdated.getPrezzo());
		}
		if (entityUpdated.getCategoria() != null) {
			entity.setCategoria(entityUpdated.getCategoria());
		}
		
		// salva e  commit cambiamenti
		ProdottoEntity entitySavedAndUpdated = this.save(entity);
		
		return entitySavedAndUpdated;
	}
	
	

}
