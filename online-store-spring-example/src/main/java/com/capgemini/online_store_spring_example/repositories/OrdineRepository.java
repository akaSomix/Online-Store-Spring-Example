package com.capgemini.online_store_spring_example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.online_store_spring_example.entities.OrdineEntity;

@Repository("ordineRepository")
public interface OrdineRepository extends JpaRepository<OrdineEntity, Long> {
	
//	public List<OrdineEntity> findAllOrderByDataOrdine();
	
	@Query("FROM OrdineEntity o JOIN o.acquirente c WHERE c.clienteId = :cliente_id")
	public List<OrdineEntity> findByAcquirenteId(@Param("cliente_id") final Long clienteId);
}
 