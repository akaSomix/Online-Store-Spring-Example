package com.capgemini.online_store_spring_example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.online_store_spring_example.entities.DisponibilitaEntity;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.entities.composite_keys.DisponibilitaCompositeKey;

@Repository
public interface DisponibilitaRepository extends JpaRepository<DisponibilitaEntity, DisponibilitaCompositeKey>{
	
	@Query("FROM DisponibilitaEntity d"
			+ " JOIN d.negozio n JOIN d.prodotto p "
			+ "WHERE n.negozioId = :negozioId AND p.codiceProdotto = :codiceProdotto")
	public DisponibilitaEntity findByNegozioIdAndCodiceProdotto(@Param("negozioId") final Long negozioId,
			@Param("codiceProdotto") final String codiceProdotto);
	
	@Modifying
	@Query("DELETE FROM DisponibilitaEntity D WHERE D.negozio = :negozio")
	public void deleteByNegozio(@Param("negozio") final NegozioEntity negozio);
	
	@Modifying
	@Query("DELETE FROM DisponibilitaEntity D WHERE D.prodotto = :prodotto")
	public void deleteByProdotto(@Param("prodotto") final ProdottoEntity prodotto);
	
}