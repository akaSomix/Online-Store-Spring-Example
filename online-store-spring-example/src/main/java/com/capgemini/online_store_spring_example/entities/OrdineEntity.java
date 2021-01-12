package com.capgemini.online_store_spring_example.entities;

import java.io.Serializable;
import java.sql.Date;

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

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ordine")
public class OrdineEntity implements Serializable, IEntity {
	
	private static final long serialVersionUID = -2879537628258625389L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "OrdineEntityIdGenerator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "OrdineEntityIdGenerator", sequenceName = "SQ_ID_ORDINE", initialValue = EntityConstants.SEQUENCE_INIT_VALUE, allocationSize = EntityConstants.SEQUENCE_ALLOCATION_SIZE)	
	@Column(name = "Ordine_Id", nullable = false, precision = EntityConstants.ORDINE_ENTITY_ID_PRECISION)
	private Long ordineId;
	
	@Getter
	@Setter
	@Column(name = "Data_Ordine", nullable = false)
	private Date dataOrdine;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = NegozioEntity.class)
	@JoinColumn(name = "Negozio_Fornitore_Id", referencedColumnName = "Negozio_Id", nullable = false)
	private NegozioEntity negozio;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ClienteEntity.class)
	@JoinColumn(name = "Acquirente_Id", referencedColumnName = "Cliente_Id", nullable = false)
	private ClienteEntity acquirente;
}
