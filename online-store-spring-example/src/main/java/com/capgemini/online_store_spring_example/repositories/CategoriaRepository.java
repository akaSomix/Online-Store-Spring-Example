package com.capgemini.online_store_spring_example.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.online_store_spring_example.entities.CategoriaEntity;

@Repository("categoriaRepository")

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>  {

	public CategoriaEntity findByNome(final String nome);
	
}
