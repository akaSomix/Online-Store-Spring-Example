package com.capgemini.online_store_spring_example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;

@Repository
public interface NegozioRepository extends JpaRepository<NegozioEntity, Long> {

	@Query("FROM NegozioEntity n WHERE n.citta.cittaId = :citta_id")
	public List<NegozioEntity> findByCittaId(@Param("citta_id") final Long cittaId);
	
	@Query("FROM NegozioEntity n WHERE n.citta.nome = :nome OR n.citta.cap = :cap")
	public List<NegozioEntity> findByCittaNomeOrCap(@Param("nome") final String cittaNome, @Param("cap") final String cittaCap);

	public List<NegozioEntity> findByNome(final String nome);
	
	public List<NegozioEntity> findAllOrderByNome(final String nome);
	
	@Query("SELECT d.quantita FROM DisponibilitaEntity d WHERE d.negozio.negozioId = :negozio_id AND d.prodotto.prodottoId = :prodotto_id")
	public Integer quantityByProdottoId(@Param("prodotto_id") final Long prodottoId,@Param("negozio_id") final Long negozioId );

	@Query("SELECT d.prodotto FROM DisponibilitaEntity d WHERE d.negozio.negozioId = :negozio_id AND d.quantita > 0")
	public List<ProdottoEntity> findProdottiDisponibiliByNegozioId(@Param("negozio_id") final Long negozioId); 

	
}
