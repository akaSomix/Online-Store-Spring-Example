package com.capgemini.online_store_spring_example.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.entities.CategoriaEntity;
import com.capgemini.online_store_spring_example.repositories.CategoriaRepository;
import com.capgemini.online_store_spring_example.services.ICategoriaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriaServiceImpl implements ICategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public List<CategoriaEntity> findall() {
		List<CategoriaEntity> entitiesFound = categoriaRepository.findAll();
		if(entitiesFound.isEmpty()) {
			log.info(this.getClass().getName() + " -- " + CategoriaEntity.class.getName() + " no record found");
		}
		
		return entitiesFound;
	}
	
	@Override
	public CategoriaEntity findById(Long id) {
		Optional<CategoriaEntity> entityFound = categoriaRepository.findById(id);
		
		if(!entityFound.isPresent()) {
			log.info(this.getClass().getName() + " -- " + CategoriaEntity.class.getName() + " no record found");
			return null;
		}
		
		return entityFound.get();
	}

	@Override
	public CategoriaEntity findByNome(String nome) {
		CategoriaEntity entityFound = categoriaRepository.findByNome(nome);
		
		if(entityFound == null) {
			log.info(this.getClass().getName() + " -- " + CategoriaEntity.class.getName() + " no record found");
		}
		
		return entityFound;
	}

}
