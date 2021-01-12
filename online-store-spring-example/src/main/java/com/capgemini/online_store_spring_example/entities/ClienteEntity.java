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
@Table(name = "Cliente")
public class ClienteEntity implements Serializable, IEntity {

	private static final long serialVersionUID = 2272249546239605910L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "ClienteEntityIdGenerator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "ClienteEntityIdGenerator", sequenceName = "SQ_ID_CLIENTE", initialValue = EntityConstants.SEQUENCE_INIT_VALUE, allocationSize = EntityConstants.SEQUENCE_ALLOCATION_SIZE)	
	@Column(name = "Cliente_Id", nullable = false, precision = EntityConstants.CLIENTE_ENTITY_ID_PRECISION)
	private Long clienteId;
	
	@Getter
	@Setter
	@Column(name="Nome", nullable = false, length = EntityConstants.CLIENTE_ENTITY_NOME_LENGTH)
	private String nome;
	
	@Getter
	@Setter
	@Column(name = "Cognome", nullable = false, length = EntityConstants.CLIENTE_ENTITY_COGNOME_LENGTH)
	private String cognome;
	
	@Getter
	@Setter
	@Column(name = "Data_Nascita", nullable = false)
	private Date dataNascita;
	
	@Getter
	@Setter
	@Column(name= "Data_Iscrizione", nullable = true)
	private Date dataIscrizione;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CittaEntity.class)
	@JoinColumn(name = "Luogo_Nascita_Id", referencedColumnName = "citta_id", nullable = false)
	private CittaEntity luogoNascita;
}
