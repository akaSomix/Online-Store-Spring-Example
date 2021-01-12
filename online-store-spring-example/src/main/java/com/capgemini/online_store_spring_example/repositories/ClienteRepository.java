package com.capgemini.online_store_spring_example.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.online_store_spring_example.entities.ClienteEntity;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	public List<ClienteEntity> findByNomeAndCognome(final String nome, final String cognome);
	
	@Query("FROM ClienteEntity c JOIN c.luogoNascita ln WHERE ln.cittaId = :luogo_nascita_id")
	public List<ClienteEntity> findByLuogoNascitaId(@Param("luogo_nascita_id") final Long luogoNascitaId);
}
