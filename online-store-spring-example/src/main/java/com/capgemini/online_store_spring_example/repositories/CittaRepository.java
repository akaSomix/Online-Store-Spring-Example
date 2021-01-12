package com.capgemini.online_store_spring_example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.online_store_spring_example.entities.CittaEntity;

@Repository("cittaRepository")
public interface CittaRepository extends JpaRepository<CittaEntity, Long> {

	public List<CittaEntity> findByCap(final Integer cap);
	
	public List<CittaEntity> findByNome(final String nome);
	
	public CittaEntity findByNomeAndCap(final String nome,final String cap);
	
	public List<CittaEntity> findByStato(final String stato);
	
	@Query("FROM CittaEntity c JOIN c.provincia p WHERE p.cittaId = :provincia_id")
	public List<CittaEntity> findByProvinciaId(@Param("provincia_id") final Long provinciaId);

	
}
