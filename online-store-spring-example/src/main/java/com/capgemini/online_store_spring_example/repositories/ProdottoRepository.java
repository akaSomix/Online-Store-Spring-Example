package com.capgemini.online_store_spring_example.repositories;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;

@Repository("prodottoRepository")
public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Long> {

	@Query("FROM ProdottoEntity p WHERE p.codiceProdotto = :codice")
	public Optional<ProdottoEntity> findByCodice(@Param("codice") final String codice);
	
	public List<ProdottoEntity> findByNomeStartingWith(final String prefix);
	
//	public List<ProdottoEntity>  findAllOrderByPrezzo();
	
	@Query("FROM ProdottoEntity p WHERE p.categoria.categoriaId = :categoria_id")
	public List<ProdottoEntity> findByCategoriaOrderByPrezzo(@Param("categoria_id") final Long categoriaId);

	@Query("SELECT d.negozio FROM DisponibilitaEntity d JOIN d.prodotto p WHERE p.prodottoId = :prodotto_id")
	public List<NegozioEntity> findStoresWithProduct(@Param("prodotto_id") final Long prodottoId);

	@Query("FROM ProdottoEntity p WHERE p NOT IN"
			+ " (SELECT d.prodotto FROM DisponibilitaEntity d JOIN d.negozio n WHERE n.negozioId = :negozio_id)")
	public List<ProdottoEntity> findAllNotInNegozio(@Param("negozio_id")final Long negozioId);

	/* Query Su Attributi Specifici*/
	
	@Query("SELECT p.codiceProdotto FROM ProdottoEntity p")
	public SortedSet<String> findAllProdottoCodice();
}
