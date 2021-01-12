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
@Table(name = "Negozio", uniqueConstraints = @UniqueConstraint(columnNames = {"Indirizzo", "citta_id"}))
public class NegozioEntity implements Serializable, IEntity {

	private static final long serialVersionUID = -417872146531511182L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "NegozioEntityIdGenerator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "NegozioEntityIdGenerator", sequenceName = "SQ_ID_NEGOZIO", initialValue = EntityConstants.SEQUENCE_INIT_VALUE, allocationSize = EntityConstants.SEQUENCE_ALLOCATION_SIZE)	
	@Column(name = "Negozio_Id", nullable = false, precision = EntityConstants.NEGOZIO_ENTITY_ID_PRECISION)
	private Long negozioId;
	
	@Getter
	@Setter
	@Column(name = "Nome", nullable = false, length = EntityConstants.NEGOZIO_ENTITY_NOME_LENGTH)
	private String nome;
	
	@Getter
	@Setter
	@Column(name = "Indirizzo", nullable = false, length = EntityConstants.NEGOZIO_ENTITY_INDIRIZZO_LENGTH)
	private String indirizzo;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = CittaEntity.class)
	@JoinColumn(name = "citta_id", referencedColumnName = "citta_id", nullable = false)
	private CittaEntity citta;
	
}