package com.capgemini.online_store_spring_example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Prodotto", uniqueConstraints = @UniqueConstraint(columnNames = {"Codice_Prodotto"}))
public class ProdottoEntity implements Serializable, IEntity{
	
	private static final long serialVersionUID = 7569268208335716567L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "ProdottoEntityIdGenerator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "ProdottoEntityIdGenerator", sequenceName = "SQ_ID_PRODOTTO", initialValue = EntityConstants.SEQUENCE_INIT_VALUE, allocationSize = EntityConstants.SEQUENCE_ALLOCATION_SIZE)	
	@Column(name = "Prodotto_Id", nullable = false, precision = EntityConstants.PRODOTTO_ENTITY_ID_PRECISION)
	private Long prodottoId;
	
	@Getter
	@Setter
	@Column(name = "Codice_Prodotto", nullable = false, unique = true, length = EntityConstants.PRODOTTO_ENTITY_CODICE_LENGTH)
	private String codiceProdotto;
	
	@Getter
	@Setter
	@Column(name = "Nome", nullable = false, length = EntityConstants.PRODOTTO_ENTITY_NOME_LENGTH)
	private String nome;
	
	@Getter
	@Setter
	@Column(name = "Descrizione", nullable = true, length = EntityConstants.PRODOTTO_ENTITY_DESCRIZIONE_LENGTH)
	private String descrizione;
	
	@Getter
	@Setter
	@Column(name = "Prezzo", nullable = false,
	precision = EntityConstants.PRODOTTO_ENTITY_PREZZO_PRECISION, scale = EntityConstants.PRODOTTO_ENTITY_PREZZO_SCALE)
	private Double prezzo;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CategoriaEntity.class)
	@JoinColumn(name = "Categoria_Id", referencedColumnName = "Categoria_Id", nullable = false)
	private CategoriaEntity categoria;
	
}
